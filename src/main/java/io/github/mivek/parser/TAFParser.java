package io.github.mivek.parser;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.Airport;
import io.github.mivek.model.TAF;
import io.github.mivek.model.TemperatureDated;
import io.github.mivek.model.trend.*;
import io.github.mivek.model.trend.validity.BeginningValidity;
import io.github.mivek.model.trend.validity.Validity;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class TAFParser extends AbstractParser<TAF> {
    /** String constant for TAF. */
    public static final String TAF = "TAF";
    /** Probability string constant. */
    private static final String PROB = "PROB";
    /** Regex for the validity. */
    private static final Pattern REGEX_VALIDITY = Pattern.compile("^\\d{4}/\\d{4}$");

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
     * @see io.github.mivek.parser.AbstractParser#parse(java.lang.String)
     */
    @Override
    public TAF parse(final String pTAFCode) throws ParseException {
        String[] lines = pTAFCode.split("\n");
        if (!TAF.equals(lines[0].substring(0, 3))) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_MESSAGE);
        }
        TAF taf = new TAF();

        // Handle the 1st line.
        String[] line1parts = tokenize(lines[0]);
        int i = 1;
        if (TAF.equals(line1parts[1])) {
            i = 2;
        }
        // Handle case the taf event is AMD.
        if ("AMD".equals(line1parts[i])) {
            taf.setAmendment(true);
            i++;
        }
        // Airport
        Airport airport = getAirports().get(line1parts[i]);
        i++;
        if (airport == null) {
            throw new ParseException(ErrorCodes.ERROR_CODE_AIRPORT_NOT_FOUND);
        }
        taf.setAirport(airport);
        taf.setMessage(pTAFCode);
        // Day and time
        parseDeliveryTime(taf, line1parts[i]);
        // Validity Time
        i++;
        taf.setValidity(parseValidity(line1parts[i]));

        // Handle rest of second line.
        for (int j = i; j < line1parts.length; j++) {
            if (line1parts[j].startsWith(PROB)) {
                taf.setProbability(Integer.valueOf(line1parts[j].substring(4)));
            } else if (RMK.equals(line1parts[j])) {
                parseRMK(taf, line1parts, j);
            } else {
                generalParse(taf, line1parts[j]);
            }
        }
        // Process other lines.
        for (int j = 1; j < lines.length; j++) {
            // Split the line.
            String[] parts = tokenize(lines[j]);
            processLines(taf, parts);
        }
        return taf;
    }

    /**
     * Handles the parsing of a line.
     *
     * @param pTaf   the TAF object to build
     * @param pParts the token of the line
     */
    private void processLines(final TAF pTaf, final String[] pParts) {
        if (pParts[0].equals(BECMG)) {
            BECMGTafTrend change = new BECMGTafTrend();
            iterChanges(1, pParts, change);
            pTaf.addBECMG(change);
        } else if (pParts[0].equals(TEMPO)) {
            TEMPOTafTrend change = new TEMPOTafTrend();
            iterChanges(1, pParts, change);
            pTaf.addTempo(change);
        } else if (pParts[0].startsWith(FM)) {
            FMTafTrend change = new FMTafTrend();
            change.setValidity(parseBasicValidity(pParts[0]));
            for (int k = 1; k < pParts.length; k++) {
                processGeneralChanges(change, pParts[k]);
            }
            pTaf.addFM(change);
        } else if (pParts[0].startsWith(PROB)) {
            PROBTafTrend change = new PROBTafTrend();
            iterChanges(0, pParts, change);
            pTaf.addProb(change);
        }
    }

    /**
     * Updates the change object according to the string.
     * @param change the change object to update.
     * @param pPart the string to parse.
     */
    private void processChanges(final AbstractTafTrend<Validity> change, final String pPart) {
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
    protected void processGeneralChanges(final AbstractTafTrend<?> pChange, final String pPart) {
        if (pPart.startsWith(PROB)) {
            pChange.setProbability(Integer.parseInt(pPart.substring(4)));
        } else if (pPart.startsWith("TX")) {
            pChange.setMaxTemperature(parseTemperature(pPart));
        } else if (pPart.startsWith("TN")) {
            pChange.setMinTemperature(parseTemperature(pPart));
        } else {
            generalParse(pChange, pPart);
        }
    }

    /**
     * Parse the validity part of a {@link TAFParser} or an
     * {@link AbstractTafTrend}.
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
     * Parses the validity of a {@link FMTafTrend} object.
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
    private void iterChanges(final int pIndex, final String[] pParts, final AbstractTafTrend<Validity> pChange) {
        for (int i = pIndex; i < pParts.length; i++) {
            if (RMK.equals(pParts[i])) {
                parseRMK(pChange, pParts, i);
            } else {
                processChanges(pChange, pParts[i]);
            }
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
        temperature.setTemperature(Converter.convertTemperature(parts[0].substring(2)));
        temperature.setDay(Integer.parseInt(parts[1].substring(0, 2)));
        temperature.setHour(Integer.parseInt(parts[1].substring(2, 4)));
        return temperature;
    }

}
