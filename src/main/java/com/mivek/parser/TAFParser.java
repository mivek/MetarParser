/**
 * 
 */
package com.mivek.parser;

import java.time.LocalTime;

import com.mivek.model.*;
import com.mivek.utils.Converter;
import com.mivek.utils.Regex;

/**
 * @author mivek
 *
 */
public final class TAFParser extends AbstractParser<TAF> {
	/**
	 * Validity regex.
	 */
	private static final String REGEX_VALIDITY = "^\\d{4}/\\d{4}$";

	/**
	 * Instance of the TAFParser.
	 */
	private static TAFParser instance = new TAFParser();

	/**
	 * Constructor.
	 */
	private TAFParser() {
		super();
	}

	/**
	 * 
	 * @return
	 */
	public static TAFParser getInstance() {
		return instance;
	}

	/* (non-Javadoc)
	 * @see com.mivek.parser.AbstractParser#parse(java.lang.String)
	 */
	@Override
	public TAF parse(String pTAFCode) {
		String[] parts = pTAFCode.split(" ");
		String[] matches = null;
		if (!parts[0].equals("TAF")) {
			return null;
		}
		TAF taf = new TAF();
		// Airport
		Airport airport = getAirports().get(parts[1]);
		if (airport == null) {
			return null;
		}
		taf.setAirport(airport);
		taf.setMessage(pTAFCode);
		// Day and time
		taf.setDay(Integer.parseInt(parts[2].substring(0, 2)));
		int hours = Integer.parseInt(parts[2].substring(2, 4));
		int minutes = Integer.parseInt(parts[2].substring(4, 6));
		LocalTime t = LocalTime.of(hours, minutes);
		taf.setTime(t);
		
		// Validity Time
		taf.setValidity(parseValidity(parts[3]));
		// Wind
		taf.setWind(parseWind(parts[4]));
		// Visibility
		Visibility visibility = new Visibility();
		visibility.setMainVisibility(Converter.convertVisibility(parts[5]));
		taf.setVisibility(visibility);

		for (int i = 6; i < parts.length; i++) {
			if (parts[i].equals(BECMG) || parts[i].equals(TEMPO) || parts[i].startsWith(FM)) {

				if (parts[i].equals(BECMG)) {
					BECMGChange change = new BECMGChange();
					i = iterChanges(i, parts, change);
					taf.addBECMG(change);
				} else if (parts[i].equals(TEMPO)) {
					TEMPOChange change = new TEMPOChange();
					i = iterChanges(i, parts, change);
					taf.addTempo(change);
				} else if (parts[i].startsWith(FM)) {
					FMChange change = new FMChange();
					change.setValidity(parseBasicValidity(parts[i]));
					i++;
					while (i < parts.length && !parts[i].equals(BECMG) && !parts[i].equals(TEMPO)
							&& !parts[i].startsWith(FM)) {
						processGeneralChanges(change, parts[i]);
						i++;
					}
					taf.addFM(change);
				}
				i--;
			} else if (Regex.match(CLOUD_REGEX, parts[i])) {
				Cloud c = parseCloud(parts[i]);
				if (c != null) {
					taf.addCloud(c);
				}
			} else if (Regex.match(VERTICAL_VISIBILITY, parts[i])) {
				matches = Regex.pregMatch(VERTICAL_VISIBILITY, parts[i]);
				taf.setVerticalVisibility(Integer.parseInt(matches[1]));
			} else {
				taf.addWeatherCondition(parseWeatherCondition(parts[i]));
			}
		}

		return taf;
	}

	/**
	 * TODO Add tests
	 * @param change
	 * @param pPart
	 */
	private void processChanges(final AbstractWeatherChange<Validity> change, String pPart) {
		if (Regex.match(REGEX_VALIDITY, pPart)) {
			change.setValidity(parseValidity(pPart));
		} else {
			processGeneralChanges(change, pPart);
		}
	}

	/**
	 * 
	 * @param pChange
	 * @param pPart
	 */
	protected void processGeneralChanges(AbstractWeatherChange<?> pChange, String pPart) {
		if (pPart.startsWith("PROB")) {
			pChange.setProbability(Integer.parseInt(pPart.substring(4)));
		} else if (pPart.startsWith("TX")) {
			pChange.setMaxTemperature(parseTemperature(pPart));
		} else if (pPart.startsWith("TN")) {
			pChange.setMinTemperature(parseTemperature(pPart));
		} else if (Regex.match(CLOUD_REGEX, pPart)) {
			Cloud c = parseCloud(pPart);
			if (c != null) {
				pChange.addCloud(c);
			}
		} else if (Regex.match(MAIN_VISIBILITY_REGEX, pPart)) {
			Visibility changeVisibility = new Visibility();
			changeVisibility.setMainVisibility(Converter.convertVisibility(pPart));
			pChange.setVisibility(changeVisibility);
		} else if (Regex.match(WIND_REGEX, pPart)) {
			pChange.setWind(parseWind(pPart));
		} else {
			pChange.addWeatherCondition(parseWeatherCondition(pPart));
		}
	}
	/**
	 * 
	 * @param pValidity
	 * @return
	 */
	protected Validity parseValidity(final String pValidity) {
		Validity validity = new Validity();
		String[] validityPart = pValidity.split("/");
		validity.setStartDay(Integer.parseInt(validityPart[0].substring(0, 2)));
		validity.setStartHour(Integer.parseInt(validityPart[0].substring(2)));
		validity.setEndDay(Integer.parseInt(validityPart[1].substring(0, 2)));
		validity.setEndHour(Integer.parseInt(validityPart[1].substring(2)));
		return validity;
	}

	protected BeginningValidity parseBasicValidity(final String pValidity) {
		BeginningValidity validity = new BeginningValidity();
		validity.setStartDay(Integer.parseInt(pValidity.substring(2, 4)));
		validity.setStartHour(Integer.parseInt(pValidity.substring(4, 6)));
		validity.setStartMinutes(Integer.parseInt(pValidity.substring(6, 8)));

		return validity;
	}

	protected int iterChanges(final int pIndex, final String[] pParts, final AbstractWeatherChange<Validity> pChange) {
		int i = pIndex;
		i++;
		while (i < pParts.length && !pParts[i].equals(BECMG) && !pParts[i].equals(TEMPO) && !pParts[i].startsWith(FM)) {
			processChanges(pChange, pParts[i]);
			i++;
		}
		return i;
	}


	protected TemperatureDated parseTemperature(String pTempPart) {
		TemperatureDated temperature = new TemperatureDated();
		String[] parts = pTempPart.split("/");
		if (parts[0].charAt(2) == 'M') {
			temperature.setTemperature(-Integer.parseInt(parts[0].substring(3)));
		} else {
			temperature.setTemperature(Integer.parseInt(parts[0].substring(2)));
		}

		temperature.setDay(Integer.parseInt(parts[1].substring(0, 2)));
		temperature.setHour(Integer.parseInt(parts[1].substring(2, 4)));
		return temperature;
	}
}
