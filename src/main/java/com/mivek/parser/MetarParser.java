package com.mivek.parser;

import java.time.LocalTime;

import org.apache.commons.lang3.ArrayUtils;

import com.mivek.model.Airport;
import com.mivek.model.Cloud;
import com.mivek.model.Metar;
import com.mivek.model.RunwayInfo;
import com.mivek.model.Visibility;
import com.mivek.model.WeatherCondition;
import com.mivek.model.Wind;
import com.mivek.model.trend.ATTime;
import com.mivek.model.trend.AbstractMetarTrend;
import com.mivek.model.trend.BECMGMetarTrend;
import com.mivek.model.trend.FMTime;
import com.mivek.model.trend.TEMPOMetarTrend;
import com.mivek.model.trend.TLTime;
import com.mivek.utils.Converter;
import com.mivek.utils.Regex;

/**
 * This controller contains methods that parse the metar code. This class is a
 * singleton.
 *
 * @author mivek
 *
 */
public final class MetarParser extends AbstractParser<Metar> {
	/**
	 * Instance of the class.
	 */
	private static MetarParser instance = new MetarParser();
	/**
	 * Pattern regex for runway with min and max range visibility.
	 */
	private static final String RUNWAY_MAX_RANGE_REGEX = "^R(\\d{2}\\w?)\\/(\\d{4})V(\\d{3})(\\w{0,2})";
	/**
	 * Pattern regex for runway visibility.
	 */
	private static final String RUNWAY_REGEX = "^R(\\d{2}\\w?)\\/(\\w)?(\\d{4})(\\w{0,2})$";
	/**
	 * Pattern to recognize a runway.
	 */
	private static final String GENERIC_RUNWAY_REGEX = "^(R\\d{2}\\w?\\/)";
	/**
	 * Pattern of the temperature block.
	 */
	private static final String TEMPERATURE_REGEX = "^(M?\\d\\d)\\/(M?\\d\\d)$";
	/**
	 * Pattern of the altimeter (Pascals).
	 */
	private static final String ALTIMETER_REGEX = "^Q(\\d{4})$";
	/**
	 * Constant string for TL.
	 */
	private static final String TILL = "TL";
	/**
	 * Constant string for AT.
	 */
	private static final String AT = "AT";
	/**
	 * Private constructor.
	 */
	private MetarParser() {
		super();
	}

	/**
	 * Get instance method.
	 *
	 * @return the instance of MetarParser.
	 */
	public static MetarParser getInstance() {
		return instance;
	}

	/**
	 * This is the main method of the parser. This method checks if the airport
	 * exists. If it does then the metar code is decoded.
	 *
	 * @param metarCode
	 *            String representing the metar.
	 * @return a decoded metar object.
	 */
	@Override
	public Metar parse(final String metarCode) {
		Metar m = new Metar();
		String[] metarTab = metarCode.split(" ");
		Airport airport = getAirports().get(metarTab[0]);
		if (airport == null) {
			return null;
		}

		m.setAirport(airport);
		m.setMessage(metarCode);
		m.setDay(Integer.parseInt(metarTab[1].substring(0, 2)));
		int hours = Integer.parseInt(metarTab[1].substring(2, 4));
		int minutes = Integer.parseInt(metarTab[1].substring(4, 6));
		LocalTime t = LocalTime.of(hours, minutes);
		m.setTime(t);
		Visibility visibility = new Visibility();
		m.setVisibility(visibility);
		int metarTabLength = metarTab.length;
		for (int i = 2; i < metarTabLength; i++) {
			String[] matches;
			if (Regex.find(WIND_REGEX, metarTab[i])) {
				Wind wind = parseWind(metarTab[i]);
				m.setWind(wind);
			} else if (Regex.find(WIND_EXTREME_REGEX, metarTab[i])) {
				matches = Regex.pregMatch(WIND_EXTREME_REGEX, metarTab[i]);
				m.getWind().setExtreme1(Integer.parseInt(matches[1]));
				m.getWind().setExtreme2(Integer.parseInt(matches[2]));
			} else if (Regex.find(MAIN_VISIBILITY_REGEX, metarTab[i])) {
				matches = Regex.pregMatch(MAIN_VISIBILITY_REGEX, metarTab[i]);
				visibility.setMainVisibility(Converter.convertVisibility(matches[1]));
			} else if (Regex.find(MIN_VISIBILITY_REGEX, metarTab[i])) {
				matches = Regex.pregMatch(MIN_VISIBILITY_REGEX, metarTab[i]);
				visibility.setMinVisibility(Integer.parseInt(matches[1].substring(0, 3)));
				visibility.setMinDirection(matches[1].substring(4));
			} else if ("NOSIG".equals(metarTab[i])) {
				m.setNosig(true);
			} else if ("AUTO".equals(metarTab[i])) {
				m.setAuto(true);
			} else if (Regex.find(GENERIC_RUNWAY_REGEX, metarTab[i])) {
				RunwayInfo ri = parseRunWayAction(metarTab[i]);
				m.addRunwayInfo(ri);
			} else if (Regex.find(TEMPERATURE_REGEX, metarTab[i])) {
				matches = Regex.pregMatch(TEMPERATURE_REGEX, metarTab[i]);
				m.setTemperature(Converter.convertTemperature(matches[1]));
				m.setDewPoint(Converter.convertTemperature(matches[2]));
			} else if (Regex.find(ALTIMETER_REGEX, metarTab[i])) {
				matches = Regex.pregMatch(ALTIMETER_REGEX, metarTab[i]);
				m.setAltimeter(Integer.parseInt(matches[1]));
			} else if (metarTab[i].equals(TEMPO) || metarTab[i].equals(BECMG)) {
				AbstractMetarTrend trend;
				if (metarTab[i].equals(TEMPO)) {
					trend = new TEMPOMetarTrend();
				} else {
					trend = new BECMGMetarTrend();
				}
				i = iterTrend(i, trend, metarTab);
				m.addTrend(trend);
			} else if (Regex.find(CLOUD_REGEX, metarTab[i])) {
				Cloud c = parseCloud(metarTab[i]);
				if (c != null) {
					m.addCloud(c);
				}
			} else if (Regex.find(VERTICAL_VISIBILITY, metarTab[i])) {
				matches = Regex.pregMatch(VERTICAL_VISIBILITY, metarTab[i]);
				m.setVerticalVisibility(Integer.parseInt(matches[1]));
			} else {
				WeatherCondition wc = parseWeatherCondition(metarTab[i]);
				if (wc != null) {
					m.addWeatherCondition(wc);
				}
			}
		}
		return m;
	}

	/**
	 * This method parses the string and returns an object.
	 *
	 * @param runWayPart String containing the runway info
	 * @return a object with parsed informations.
	 */
	protected RunwayInfo parseRunWayAction(final String runWayPart) {
		String[] matches;
		RunwayInfo ri = new RunwayInfo();
		if (ArrayUtils.isNotEmpty((matches = Regex.pregMatch(RUNWAY_REGEX, runWayPart)))) {
			ri.setName(matches[1]);
			ri.setMinRange(Integer.parseInt(matches[3]));
			ri.setTrend(Converter.convertTrend(matches[4]));
			return ri;
		} else if (ArrayUtils.isNotEmpty((matches = Regex.pregMatch(RUNWAY_MAX_RANGE_REGEX, runWayPart)))) {
			ri.setName(matches[1]);
			ri.setMinRange(Integer.parseInt(matches[2]));
			ri.setMaxRange(Integer.parseInt(matches[3]));
			ri.setTrend(Converter.convertTrend(matches[4]));
			return ri;
		}
		return null;
	}

	/**
	 * 
	 * @param pIndex
	 * @param pTrend
	 * @param pParts
	 * @return
	 */
	protected int iterTrend(final int pIndex, final AbstractMetarTrend pTrend, final String[] pParts) {
		int i = pIndex + 1;
		while (i < pParts.length && !pParts[i].equals(TEMPO) && !pParts[i].equals(BECMG)) {
			processChange(pTrend, pParts[i]);
			i++;
		}
		return i - 1;
	}

	/**
	 * 
	 * @param pTrend
	 * @param pPart
	 */
	protected void processChange(final AbstractMetarTrend pTrend, final String pPart) {
		if (pPart.startsWith(AT)) {
			ATTime at = new ATTime();
			at.setTime(Converter.stringToTime(pPart.substring(2)));
			pTrend.addTime(at);
		} else if (pPart.startsWith(FM)) {
			FMTime fm = new FMTime();
			fm.setTime(Converter.stringToTime(pPart.substring(2)));
			pTrend.addTime(fm);
		} else if (pPart.startsWith(TILL)) {
			TLTime tl = new TLTime();
			tl.setTime(Converter.stringToTime(pPart.substring(2)));
			pTrend.addTime(tl);
		} else if (Regex.find(WIND_REGEX, pPart)) {
			Wind wind = parseWind(pPart);
			pTrend.setWind(wind);
		} else if (Regex.find(WIND_EXTREME_REGEX, pPart)) {
			String[] matches = Regex.pregMatch(WIND_EXTREME_REGEX, pPart);
			pTrend.getWind().setExtreme1(Integer.parseInt(matches[1]));
			pTrend.getWind().setExtreme2(Integer.parseInt(matches[2]));
		} else if (Regex.find(MAIN_VISIBILITY_REGEX, pPart)) {
			Visibility visibility = new Visibility();
			pTrend.setVisibility(visibility);
			String[] matches = Regex.pregMatch(MAIN_VISIBILITY_REGEX, pPart);
			visibility.setMainVisibility(Converter.convertVisibility(matches[1]));
		} else if (Regex.find(MIN_VISIBILITY_REGEX, pPart)) {
			String[] matches = Regex.pregMatch(MIN_VISIBILITY_REGEX, pPart);
			pTrend.getVisibility().setMinVisibility(Integer.parseInt(matches[1].substring(0, 3)));
			pTrend.getVisibility().setMinDirection(matches[1].substring(4));
		} else if (Regex.find(CLOUD_REGEX, pPart)) {
			Cloud c = parseCloud(pPart);
			if (c != null) {
				pTrend.addCloud(c);
			}
		} else if (Regex.find(VERTICAL_VISIBILITY, pPart)) {
			String[] matches = Regex.pregMatch(VERTICAL_VISIBILITY, pPart);
			pTrend.setVerticalVisibility(Integer.parseInt(matches[1]));
		} else {
			WeatherCondition wc = parseWeatherCondition(pPart);
			if (wc != null) {
				pTrend.addWeatherCondition(wc);
			}
		}
	}
}
