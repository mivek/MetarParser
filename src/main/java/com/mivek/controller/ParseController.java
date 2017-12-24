package com.mivek.controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.mivek.enums.CloudQuantity;
import com.mivek.enums.CloudType;
import com.mivek.enums.Descriptive;
import com.mivek.enums.Intensity;
import com.mivek.enums.Phenomenon;
import com.mivek.model.Airport;
import com.mivek.model.Cloud;
import com.mivek.model.Country;
import com.mivek.model.Metar;
import com.mivek.model.RunwayInfo;
import com.mivek.model.Time;
import com.mivek.model.Visibility;
import com.mivek.model.WeatherCondition;
import com.mivek.model.Wind;
import com.mivek.utils.Converter;
import com.mivek.utils.Regex;
import com.opencsv.CSVReader;

/**
 * This controller contains methods that parse the metar code. This class is a
 * singleton.
 *
 * @author mivek
 *
 */
public final class ParseController {
	/**
	 * Path of airport file.
	 */
	private final InputStream AIRPORT_FILE = getClass().getClassLoader().getResourceAsStream("data/airports.dat");
	/**
	 * Path of countries file.
	 */
	private final InputStream COUNTRIES_FILE = getClass().getClassLoader().getResourceAsStream("data/countries.dat");
	/**
	 * Map of airports.
	 */
	private Map<String, Airport> airports;
	/**
	 * Map of countries.
	 */
	private Map<String, Country> countries;
	/**
	 * Instance of the class.
	 */
	private static ParseController INSTANCE = null;
	/**
	 * Pattern regex for runway with min and max range visibility.
	 */
	private static final String RUNWAY_MAX_RANGE_REGEX = "^R(\\d{2}\\w?)\\/(\\d{4})V(\\d{3})(\\w{0,2})";
	/**
	 * Pattern regex for runway visibility.
	 */
	private static final String RUNWAY_REGEX = "^R(\\d{2}\\w?)\\/(\\w)?(\\d{4})(\\w{0,2})$";
	/**
	 * Pattern regex for wind.
	 */
	private final String WIND_REGEX = "(\\d{3})(\\d{2})G?(\\d{2})?(KT|MPS|KM\\/H)";
	/**
	 * Pattern regex for extreme winds.
	 */
	private final String WIND_EXTREME_REGEX = "^(\\d{3})V(\\d{3})";
	/**
	 * Pattern for the main visibility.
	 */
	private final String MAIN_VISIBILITY_REGEX = "^(\\d\\d\\d\\d)$";
	/**
	 * Pattern for the minimum visibility.
	 */
	private final String MIN_VISIBILITY_REGEX = "^(\\d\\d\\d\\d\\w)$";
	/**
	 * Pattern to recognize a runway.
	 */
	private final String GENERIC_RUNWAY_REGEX = "^(R\\d{2}\\w?\\/)";
	/**
	 * Pattern of the temperature block.
	 */
	private final String TEMPERATURE_REGEX = "^(M?\\d\\d)\\/(M?\\d\\d)$";
	/**
	 * Pattern of the altimeter (Pascals).
	 */
	private final String ALTIMETER_REGEX = "^Q(\\d{4})$";
	/**
	 * Pattern to recognize clouds.
	 */
	private final String CLOUD_REGEX = "^(\\w{3})(\\d{3})?(\\w{2,3})?";
	/**
	 * Pattern for the vertical visibility.
	 */
	private final String VERTICAL_VISIBILITY = "^VV(\\d{3})$";

	/**
	 * Private constructor.
	 */
	private ParseController() {
		initCountries();
		initAirports();
	}

	/**
	 * Get instance method.
	 *
	 * @return the instance of ParseController.
	 */
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
	 * @param metarCode
	 *            String representing the metar.
	 * @return a decoded metar object.
	 */
	public Metar parseMetarAction(final String metarCode) {
		Metar m = new Metar();
		String[] metarTab = metarCode.split(" ");
		Airport airport = airports.get(metarTab[0]);
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
				if ((matches = Regex.pregMatch(WIND_REGEX, metarTab[i])) != null) {
					Wind wind = parseWind(matches);
					m.setWind(wind);
				} else if ((matches = Regex.pregMatch(WIND_EXTREME_REGEX, metarTab[i])) != null) {
					m.getWind().setExtreme1(Integer.parseInt(matches[1]));
					m.getWind().setExtreme2(Integer.parseInt(matches[2]));
				} else if ((matches = Regex.pregMatch(MAIN_VISIBILITY_REGEX, metarTab[i])) != null) {
					visibility.setMainVisibility(Converter.convertVisibility(matches[1]));
				} else if ((matches = Regex.pregMatch(MIN_VISIBILITY_REGEX, metarTab[i])) != null) {
					visibility.setMinVisibility(Integer.parseInt(matches[1].substring(0, 3)));
					visibility.setMinDirection(matches[1].substring(4));
				} else if ("NOSIG".equals(metarTab[i])) {
					m.setNosig(true);
				} else if ("AUTO".equals(metarTab[i])) {
					m.setAuto(true);
				} else if (Regex.find(GENERIC_RUNWAY_REGEX, metarTab[i])) {
					RunwayInfo ri = parseRunWayAction(metarTab[i]);
					m.addRunwayInfo(ri);
				} else if ((matches = Regex.pregMatch(TEMPERATURE_REGEX, metarTab[i])) != null) {
					m.setTemperature(Converter.convertTemperature(matches[1]));
					m.setDewPoint(Converter.convertTemperature(matches[2]));
				} else if ((matches = Regex.pregMatch(ALTIMETER_REGEX, metarTab[i])) != null) {
					m.setAltimeter(Integer.parseInt(matches[1]));
				} else if ((matches = Regex.pregMatch(CLOUD_REGEX, metarTab[i])) != null) {
					m.addCloud(parseCloud(matches));
				} else if ((matches = Regex.pregMatch(VERTICAL_VISIBILITY, metarTab[i])) != null) {
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
	 * @param runWayPart
	 *            String containing the runway info
	 * @return a object with parsed informations.
	 */
	protected RunwayInfo parseRunWayAction(final String runWayPart) {
		String[] matches;
		RunwayInfo ri = new RunwayInfo();
		if ((matches = Regex.pregMatch(RUNWAY_REGEX, runWayPart)) != null) {
			ri.setName(matches[1]);
			// ri.setIndicator(matches[2]);
			ri.setMinRange(Integer.parseInt(matches[3]));
			ri.setTrend(Converter.convertTrend(matches[4]));
			return ri;
		}
		if ((matches = Regex.pregMatch(RUNWAY_MAX_RANGE_REGEX, runWayPart)) != null) {
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
	 *            An array of strings with wind elements.
	 * @return a Wind element with the informations.
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
	 *            Table of strings with clouds elements.
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
	 *            String representing the weather.
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

	/**
	 * Initiate airports map.
	 */
	private void initAirports() {
		airports = new HashMap<>();
		CSVReader reader;
		try {
			reader = new CSVReader(new InputStreamReader(AIRPORT_FILE));
			String[] line;
			while ((line = reader.readNext()) != null) {
				Airport airport = new Airport();
				airport.setName(line[1]);
				airport.setCity(line[2]);
				airport.setCountry(countries.get(line[3]));
				airport.setIata(line[4]);
				airport.setIcao(line[5]);
				airport.setLatitude(Double.parseDouble(line[6]));
				airport.setLongitude(Double.parseDouble(line[7]));
				airport.setAltitude(Integer.parseInt(line[8]));
				airport.setTimezone(line[9]);
				airport.setDst(line[10]);
				airports.put(airport.getIcao(), airport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initiate countries map.
	 */
	private void initCountries() {
		countries = new HashMap<>();
		CSVReader reader;
		try {
			reader = new CSVReader(new InputStreamReader(COUNTRIES_FILE));
			String[] line;
			while ((line = reader.readNext()) != null) {
				Country country = new Country();
				country.setName(line[0]);
				countries.put(country.getName(), country);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the airports
	 */
	public Map<String, Airport> getAirports() {
		return airports;
	}

	/**
	 * @return the countries
	 */
	public Map<String, Country> getCountries() {
		return countries;
	}
}
