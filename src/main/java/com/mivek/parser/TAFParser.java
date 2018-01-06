/**
 * 
 */
package com.mivek.parser;

import com.mivek.model.AbstractWeatherChange;
import com.mivek.model.Airport;
import com.mivek.model.BECMGChange;
import com.mivek.model.BeginningValidity;
import com.mivek.model.FMChange;
import com.mivek.model.TAF;
import com.mivek.model.TEMPOChange;
import com.mivek.model.Time;
import com.mivek.model.Validity;
import com.mivek.model.Visibility;
import com.mivek.utils.Converter;
import com.mivek.utils.Regex;

/**
 * @author mivek
 *
 */
public class TAFParser extends AbstractParser<TAF> {
	private static final String FM = "FM";

	private static final String TEMPO = "TEMPO";

	private static final String BECMG = "BECMG";

	private static final String REGEX_VALIDITY = "^\\d{4}/\\d{4}$";

	/**
	 * Instance of the TAFParser.
	 */
	private static TAFParser instance = null;

	/**
	 * 
	 */
	private TAFParser() {
		super();
	}

	/**
	 * 
	 * @return
	 */
	public static TAFParser getInstance() {
		if (instance == null) {
			instance = new TAFParser();
		}
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
		Time t = new Time();
		t.setHours(Integer.parseInt(parts[2].substring(2, 4)));
		t.setMinutes(Integer.parseInt(parts[2].substring(4, 6)));
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
					i++;
					while (i < parts.length && !parts[i].equals(BECMG) && !parts[i].equals(TEMPO)
							&& !parts[i].startsWith(FM)) {
						processChanges(change, parts[i]);
						i++;
					}
					taf.addBECMG(change);
				} else if (parts[i].equals(TEMPO)) {
					TEMPOChange change = new TEMPOChange();
					i++;
					while (i < parts.length && !parts[i].equals(BECMG) && !parts[i].equals(TEMPO)
							&& !parts[i].startsWith(FM)) {
						processChanges(change, parts[i]);
						i++;
					}
					taf.addTempo(change);
				} else if (parts[i].startsWith(FM)) {
					FMChange change = new FMChange();
					change.setValidity(parseBasicValidity(parts[i]));
					i++;
					while (i < parts.length && !parts[i].equals(BECMG) && !parts[i].equals(TEMPO)
							&& !parts[i].startsWith(FM)) {
						processChanges(change, parts[i]);
						i++;
					}
					taf.addFM(change);
				}
				i--;
			} else if (Regex.match(CLOUD_REGEX, parts[i])) {
				taf.addCloud(parseCloud(parts[i]));
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
	 * 
	 * @param change
	 * @param pPart
	 */
	private void processChanges(final AbstractWeatherChange<Validity> change, String pPart) {
		if (pPart.startsWith("PROB")) {
			change.setProbability(Integer.parseInt(pPart.substring(4)));
		} else if (Regex.match(REGEX_VALIDITY, pPart)) {
			change.setValidity(parseValidity(pPart));
		} else if (Regex.match(CLOUD_REGEX, pPart)) {
			change.addCloud(parseCloud(pPart));
		} else if (Regex.match(MAIN_VISIBILITY_REGEX, pPart)) {
			Visibility changeVisibility = new Visibility();
			changeVisibility.setMainVisibility(Converter.convertVisibility(pPart));
			change.setVisibility(changeVisibility);
		} else if (Regex.match(WIND_REGEX, pPart)) {
			change.setWind(parseWind(pPart));
		} else {
			change.addWeatherCondition(parseWeatherCondition(pPart));
		}
	}

	private void processChanges(final FMChange pChange, String pPart) {
		if (pPart.startsWith("PROB")) {
			pChange.setProbability(Integer.parseInt(pPart.substring(4)));
		} else if (Regex.match(CLOUD_REGEX, pPart)) {
			pChange.addCloud(parseCloud(pPart));
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
}
