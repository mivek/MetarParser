package com.mivek.parser;

import java.time.LocalTime;

import com.mivek.model.Airport;
import com.mivek.model.BeginningValidity;
import com.mivek.model.Cloud;
import com.mivek.model.PROBChange;
import com.mivek.model.TAF;
import com.mivek.model.TemperatureDated;
import com.mivek.model.Validity;
import com.mivek.model.Visibility;
import com.mivek.model.WeatherCondition;
import com.mivek.model.trend.AbstractWeatherChange;
import com.mivek.model.trend.BECMGChange;
import com.mivek.model.trend.FMChange;
import com.mivek.model.trend.TEMPOChange;
import com.mivek.utils.Converter;
import com.mivek.utils.Regex;

/**
 * @author mivek
 */
public final class TAFParser extends AbstractParser<TAF> {
    /**
     * From string constant.
     */
    private static final String FM = "FM";
    /**
     * Tempo string constant.
     */
    private static final String TEMPO = "TEMPO";
    /**
     * Becoming string constant.
     */
    private static final String BECMG = "BECMG";
    /**
     * Probability string constant.
     */
    private static final String PROB = "PROB";
    /**
     * Regex for the validity.
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
     * @return the instance.
     */
    public static TAFParser getInstance() {
        return instance;
    }

    /*
     * (non-Javadoc)
     * @see com.mivek.parser.AbstractParser#parse(java.lang.String)
     */
    @Override
    public TAF parse(final String pTAFCode) {
        String[] lines = pTAFCode.split("\n");
        String[] matches = null;
        if (!lines[0].substring(0, 3).equals("TAF")) {
            return null;
        }
        TAF taf = new TAF();

        // Handle the 1st line.
        String[] lines1parts = lines[0].split(" ");
        int i = 1;
        if (lines1parts[1].equals("TAF")) {
            i = 2;
        }

        // Airport
        Airport airport = getAirports().get(lines1parts[i]);
        i++;
        if (airport == null) {
            return null;
        }
        taf.setAirport(airport);
        taf.setMessage(pTAFCode);
        // Day and time
        taf.setDay(Integer.parseInt(lines1parts[i].substring(0, 2)));
        int hours = Integer.parseInt(lines1parts[i].substring(2, 4));
        int minutes = Integer.parseInt(lines1parts[i].substring(4, 6));
        LocalTime t = LocalTime.of(hours, minutes);
        taf.setTime(t);

        Visibility visibility = new Visibility();
        taf.setVisibility(visibility);
        // Validity Time
        i++;
        taf.setValidity(parseValidity(lines1parts[i]));

        // Wind
        i++;
        taf.setWind(parseWind(lines1parts[i]));
        // Handle rest of second line.
        for (int j = i; j < lines1parts.length; j++) {
            if (Regex.find(WIND_EXTREME_REGEX, lines1parts[j])) {
                matches = Regex.pregMatch(WIND_EXTREME_REGEX, lines1parts[j]);
                taf.getWind().setExtreme1(Integer.parseInt(matches[1]));
                taf.getWind().setExtreme2(Integer.parseInt(matches[2]));
            } else if (Regex.find(MAIN_VISIBILITY_REGEX, lines1parts[j])) {
                matches = Regex.pregMatch(MAIN_VISIBILITY_REGEX, lines1parts[j]);
                visibility.setMainVisibility(Converter.convertVisibility(matches[1]));
            } else if (Regex.find(MIN_VISIBILITY_REGEX, lines1parts[j])) {
                matches = Regex.pregMatch(MIN_VISIBILITY_REGEX, lines1parts[j]);
                visibility.setMinVisibility(Integer.parseInt(matches[1].substring(0, 3)));
                visibility.setMinDirection(matches[1].substring(4));
            } else if (Regex.find(CLOUD_REGEX, lines1parts[j])) {
                Cloud c = parseCloud(lines1parts[j]);
                if (c != null) {
                    taf.addCloud(c);
                }
            } else if (lines1parts[j].startsWith(PROB)) {
                taf.setProbability(Integer.valueOf(lines1parts[j].substring(4)));
            } else if (Regex.match(VERTICAL_VISIBILITY, lines1parts[j])) {
                matches = Regex.pregMatch(VERTICAL_VISIBILITY, lines1parts[j]);
                taf.setVerticalVisibility(Integer.parseInt(matches[1]));
            } else {
                WeatherCondition wc = parseWeatherCondition(lines1parts[j]);
                if (wc != null) {
                    taf.addWeatherCondition(wc);
                }
            }
        }
        // Process other lines.
        for (int j = 1; j < lines.length; j++) {
            // Split the line.
            String[] parts = lines[j].split(" ");
            if (parts[0].equals(BECMG)) {
                BECMGChange change = new BECMGChange();
                iterChanges(1, parts, change);
                taf.addBECMG(change);
            } else if (parts[0].equals(TEMPO)) {
                TEMPOChange change = new TEMPOChange();
                iterChanges(1, parts, change);
                taf.addTempo(change);
            } else if (parts[0].startsWith(FM)) {
                FMChange change = new FMChange();
                change.setValidity(parseBasicValidity(parts[0]));
                for (int k = 1; k < parts.length; k++) {
                    processGeneralChanges(change, parts[k]);
                }
                taf.addFM(change);
            } else if (parts[0].startsWith(PROB)) {
                PROBChange change = new PROBChange();
                iterChanges(0, parts, change);
                taf.addProb(change);
            }
        }
        return taf;
    }

    /**
     * Updates the change object according to the string.
     * @param change the change object to update.
     * @param pPart the string to parse.
     */
    private void processChanges(final AbstractWeatherChange<Validity> change, final String pPart) {
        if (Regex.match(REGEX_VALIDITY, pPart)) {
            change.setValidity(parseValidity(pPart));
        } else {
            processGeneralChanges(change, pPart);
        }
    }

    /**
     * Updates the change object according to the string.
     * @param pChange the change object to update.
     * @param pPart String containing the information.
     */
    protected void processGeneralChanges(final AbstractWeatherChange<?> pChange, final String pPart) {
        if (pPart.startsWith(PROB)) {
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
            WeatherCondition wc = parseWeatherCondition(pPart);
            if (wc != null) {
                pChange.addWeatherCondition(parseWeatherCondition(pPart));
            }
        }
    }

    /**
     * Parse the validity part of a {@link TAFParser} or an
     * {@link AbstractWeatherChange}.
     * @param pValidity the string representing the validity.
     * @return a {@link Validity} object.
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

    /**
     * Parses the validity of a {@link FMChange} object.
     * @param pValidity the string to parse
     * @return a {@link BeginningValidity} object.
     */
    protected BeginningValidity parseBasicValidity(final String pValidity) {
        BeginningValidity validity = new BeginningValidity();
        validity.setStartDay(Integer.parseInt(pValidity.substring(2, 4)));
        validity.setStartHour(Integer.parseInt(pValidity.substring(4, 6)));
        validity.setStartMinutes(Integer.parseInt(pValidity.substring(6, 8)));
        return validity;
    }

    /**
     * Iterates over the string array and build the abstractWeather change.
     * @param pIndex the starting index of the array.
     * @param pParts the array of string.
     * @param pChange the abstractWeatherChange to update.
     */
    protected void iterChanges(final int pIndex, final String[] pParts, final AbstractWeatherChange<Validity> pChange) {
        for (int i = pIndex; i < pParts.length; i++) {
            processChanges(pChange, pParts[i]);
        }
    }

    /**
     * Parse the temperature.
     * @param pTempPart the string to parse.
     * @return a temperature with its date.
     */
    protected TemperatureDated parseTemperature(final String pTempPart) {
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
