package com.mivek.controller;

import com.mivek.enums.CloudQuantity;
import com.mivek.enums.CloudType;
import com.mivek.enums.Descriptive;
import com.mivek.enums.Intensity;
import com.mivek.enums.Phenomenon;
import com.mivek.main.Main;
import com.mivek.model.Airport;
import com.mivek.model.Cloud;
import com.mivek.model.Metar;
import com.mivek.model.RunwayInfo;
import com.mivek.model.Time;
import com.mivek.model.Visibility;
import com.mivek.model.WeatherCondition;
import com.mivek.model.Wind;
import com.mivek.utils.Converter;
import com.mivek.utils.Regex;

/**
 * This controller contains methods that parse the metar code
 * 
 * @author mivek
 *
 */
public class ParseController {
	private static ParseController INSTANCE = null;

	private ParseController() {
	}

	public static ParseController getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ParseController();
		}
		return INSTANCE;
	}

	/**
	 * This is the main method of the parser. This method checks if the airport
	 * exists. If it does then the metar code is decoded.
	 * 
	 * @param metarCode.
	 *            String representing the metar.
	 * @return a decoded metar object.
	 */
	public Metar parseMetarAction(final String metarCode) {
		Metar m = new Metar();
		String[] metarTab = metarCode.split(" ");
		Airport airport = Main.getAirports().get(metarTab[0]);
		if (airport != null) {
			m.setAirport(airport);
			m.setMessage(metarCode);
			m.setDay(Integer.parseInt(metarTab[1].substring(0, 2)));
			Time t = new Time();
			t.setHours(Integer.parseInt(metarTab[1].substring(2, 4)));
			t.setMinutes(Integer.parseInt(metarTab[1].substring(4, 6)));
			m.setTime(t);
			Visibility visibility = new Visibility();
			m.setVisibility(visibility);
			int metarTabLength = metarTab.length;
			for (int i = 2; i < metarTabLength; i++) {
				String[] matches;
				if ((matches = Regex.preg_match("(\\d{3})(\\d{2})G?(\\d{2})?(KT|MPS|KM\\/H)", metarTab[i])) != null) {
					Wind wind = parseWind(matches);
					m.setWind(wind);
				} else if ((matches = Regex.preg_match("^(\\d{3})V(\\d{3})", metarTab[i])) != null) {
					m.getWind().setExtreme1(Integer.parseInt(matches[1]));
					m.getWind().setExtreme2(Integer.parseInt(matches[2]));
				} else if ((matches = Regex.preg_match("^(\\d\\d\\d\\d)$", metarTab[i])) != null) {
					visibility.setMainVisibility(Converter.convertVisibility(matches[1]));
				} else if ((matches = Regex.preg_match("^(\\d\\d\\d\\d\\w)$", metarTab[i])) != null) {
					visibility.setMinVisibility(Integer.parseInt(matches[1].substring(0, 3)));
					visibility.setMinDirection(matches[1].substring(4));
				} else if ("NOSIG".equals(metarTab[i])) {
					m.setNosig(true);
				} else if ("AUTO".equals(metarTab[i])) {
					m.setAuto(true);
				} else if (Regex.find("^(R\\d{2}\\w?\\/)", metarTab[i])) {
					RunwayInfo ri = parseRunWayAction(metarTab[i]);
					m.addRunwayInfo(ri);
				} else if ((matches = Regex.preg_match("^(M?\\d\\d)\\/(M?\\d\\d)$", metarTab[i])) != null) {
					m.setTemperature(Converter.convertTemperature(matches[1]));
					m.setDewPoint(Converter.convertTemperature(matches[2]));
				} else if ((matches = Regex.preg_match("^Q(\\d{4})$", metarTab[i])) != null) {
					m.setAltimeter(Integer.parseInt(matches[1]));
				} else if ((matches = Regex.preg_match("^(\\w{3})(\\d{3})?(\\w{2,3})?", metarTab[i])) != null) {
					m.addCloud(parseCloud(matches));
				} else if ((matches = Regex.preg_match("^VV(\\d{3})$", metarTab[i])) != null) {
					m.setVerticalVisibility(Integer.parseInt(matches[1]));
				} else {
					m.addWeatherCondition(parseWeatherCondition(metarTab[i]));
				}
			}
			return m;
		}
		return null;
	}

	/**
	 * This method parses the string and returns an object.
	 * 
	 * @param runWayPart.
	 *            String containing the runway info
	 * @return a object with parsed informations.
	 */
	protected RunwayInfo parseRunWayAction(final String runWayPart) {
		String[] matches;
		RunwayInfo ri = new RunwayInfo();
		if ((matches = Regex.preg_match("^R(\\d{2}\\w?)\\/(\\w)?(\\d{4})(\\w{0,2})$", runWayPart)) != null) {
			ri.setName(matches[1]);
			// ri.setIndicator(matches[2]);
			ri.setMinRange(Integer.parseInt(matches[3]));
			ri.setTrend(Converter.convertTrend(matches[4]));
			return ri;
		}
		if ((matches = Regex.preg_match("^R(\\d{2}\\w?)\\/(\\d{4})V(\\d{3})(\\w{0,2})", runWayPart)) != null) {
			ri.setName(matches[1]);
			ri.setMinRange(Integer.parseInt(matches[2]));
			ri.setMaxRange(Integer.parseInt(matches[3]));
			ri.setTrend(Converter.convertTrend(matches[4]));
			return ri;
		}
		return null;
	}

	/**
	 * This method parses the wind part of the metar code. It parses the generic
	 * part. Variable winds are not parsed by this method.
	 * 
	 * @param windPart
	 * @return
	 */
	protected Wind parseWind(final String[] windPart) {
		Wind wind = new Wind();
		wind.setDirection(Converter.degreesToDirection(windPart[1]));
		wind.setSpeed(Integer.parseInt(windPart[2]));
		if (windPart[3] != null) {
			wind.setGust(Integer.parseInt(windPart[3]));
		}
		wind.setUnit(windPart[4]);
		return wind;
	}

	/**
	 * This method parses the cloud part of the metar.
	 * 
	 * @param cloudPart
	 * @return a decoded cloud with its quantity, its altitude and its type.
	 */
	protected Cloud parseCloud(final String[] cloudPart) {
		Cloud cloud = new Cloud();
		try {
			CloudQuantity cq = CloudQuantity.valueOf(cloudPart[1]);

			cloud.setQuantity(cq);
			if (cloudPart[2] != null) {
				cloud.setAltitude(30 * Integer.parseInt(cloudPart[2]));
				if (cloudPart[3] != null) {
					CloudType ct = CloudType.valueOf(cloudPart[3]);
					cloud.setType(ct);
				}
			}
			return cloud;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * This method parses the weather conditions of the metar.
	 * 
	 * @param weatherPart
	 * @return a weather condition with its intensity, its descriptor and the
	 *         phenomenon.
	 */
	protected WeatherCondition parseWeatherCondition(final String weatherPart) {
		WeatherCondition wc = new WeatherCondition();
		String match = null;
		if ((match = Regex.findString("^(-|\\+|VC)", weatherPart)) != null) {
			Intensity i = Intensity.getEnum(match);
			wc.setIntensity(i);
		}
		for (Descriptive des : Descriptive.values()) {
			if ((match = Regex.findString("(" + des.getShortcut() + ")", weatherPart)) != null) {
				wc.setDescriptive(des);
			}
		}
		for (Phenomenon phe : Phenomenon.values()) {
			if ((match = Regex.findString("(" + phe.getShortcut() + ")", weatherPart)) != null) {
				wc.addPhenomenon(phe);
			}
		}
		if (wc.isValid()) {
			return wc;
		}
		return null;
	}

}
