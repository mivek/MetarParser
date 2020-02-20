package io.github.mivek.parser;

import io.github.mivek.command.AirportSupplier;
import io.github.mivek.command.common.CommonCommandSupplier;
import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.Airport;
import io.github.mivek.model.TAF;
import io.github.mivek.model.TemperatureDated;
import io.github.mivek.model.trend.AbstractTafTrend;
import io.github.mivek.model.trend.BECMGTafTrend;
import io.github.mivek.model.trend.FMTafTrend;
import io.github.mivek.model.trend.PROBTafTrend;
import io.github.mivek.model.trend.TEMPOTafTrend;
import io.github.mivek.model.trend.validity.BeginningValidity;
import io.github.mivek.model.trend.validity.Validity;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mivek
 */
public final class TAFParser extends AbstractParser<TAF> {
    /** String constant for TAF. */
    public static final String TAF = "TAF";
    /** Probability string constant. */
    private static final String PROB = "PROB";
    /** Temperature Maximum Constant. */
    private static final String TX = "TX";
    /** Temperature Minimum Constant. */
    private static final String TN = "TN";
    /** Regex for the validity. */
    private static final Pattern REGEX_VALIDITY = Pattern.compile("^\\d{4}/\\d{4}$");
    /** Instance of the TAFParser. */
    private static TAFParser instance = new TAFParser();

    /**
     * Default constructor.
     */
    private TAFParser() {
        this(new CommonCommandSupplier(), RemarkParser.getInstance(), new AirportSupplier());
    }

    /**
     * Dependency injection constructor.
     *
     * @param pCommonCommandSupplier the common command supplier
     * @param pRemarkParser          the remark parser.
     * @param pAirportSupplier       the airport supplier.
     */
    protected TAFParser(final CommonCommandSupplier pCommonCommandSupplier, final RemarkParser pRemarkParser, final AirportSupplier pAirportSupplier) {
        super(pCommonCommandSupplier, pRemarkParser, pAirportSupplier);
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
        String[][] lines = extractLineTokens(pTAFCode);
        if (!TAF.equals(lines[0][0])) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_MESSAGE);
        }
        TAF taf = new TAF();

        // Handle the 1st line.
        String[] line1parts = lines[0];
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
        Airport airport = getAirportSupplier().get(line1parts[i]);
        taf.setStation(line1parts[i]);
        i++;
        taf.setAirport(airport);
        taf.setMessage(pTAFCode);
        // Day and time
        parseDeliveryTime(taf, line1parts[i]);
        // Validity Time
        i++;
        taf.setValidity(parseValidity(line1parts[i]));

        // Handle rest of second line.
        for (int j = i; j < line1parts.length; j++) {
            String part = line1parts[j];
            if (RMK.equals(part)) {
                parseRMK(taf, line1parts, j);
            } else if (part.startsWith(TX)) {
                taf.setMaxTemperature(parseTemperature(part));
            } else if (part.startsWith(TN)) {
                taf.setMinTemperature(parseTemperature(part));
            } else {
                generalParse(taf, part);
            }
        }
        // Process other lines.
        for (int j = 1; j < lines.length; j++) {
            // Split the line.
            String[] parts = lines[j];
            processLines(taf, parts);
        }
        return taf;
    }

    /**
     * Extracts all lines and tokenize them.
     * @param pTAFCode raw TAF which may already contains some linebreaks
     * @return 2d jagged array containing lines and their tokens
     */
    private String[][] extractLineTokens(final String pTAFCode) {
        String cleanedInput = pTAFCode
                .replace("\n", " ")   // remove all linebreaks
                .replaceAll("\\s{2,}", " ");  // remove unnecessary whitespaces

        String[] lines = cleanedInput
                .replaceAll("\\s(PROB\\d{2}\\sTEMPO|TEMPO|BECMG|FM|PROB)", "\n$1")
                .split("\n");
        String[][] lineTokens = Arrays.stream(lines).map(this::tokenize).toArray(String[][]::new);
        if (lineTokens.length > 1) {
            // often temperatures are set in the end of the TAF report
            String[] last = lineTokens[lines.length - 1];
            List<String> temperatures = Arrays.stream(last).filter(code -> code.startsWith(TX) || code.startsWith(TN)).collect(Collectors.toList());
            if (!temperatures.isEmpty()) {
                lineTokens[0] = Stream.concat(Arrays.stream(lineTokens[0]), temperatures.stream()).toArray(String[]::new);
                lineTokens[lines.length - 1] = Arrays.stream(last).filter(code -> !code.startsWith(TX) && !code.startsWith(TN)).toArray(String[]::new);
            }
        }
        return lineTokens;
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
            int probability = parseProbability(pParts[0]);
            if (pParts.length > 1 && pParts[1].equals(TEMPO)) {
                TEMPOTafTrend change = new TEMPOTafTrend();
                iterChanges(2, pParts, change);
                change.setProbability(probability);
                pTaf.addTempo(change);
            } else {
                PROBTafTrend change = new PROBTafTrend();
                change.setProbability(probability);
                iterChanges(1, pParts, change);
                pTaf.addProb(change);
            }
        }
    }

    /**
     * Updates the change object according to the string.
     *
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
     *
     * @param pChange the change object to update.
     * @param pPart String containing the information.
     */
    protected void processGeneralChanges(final AbstractTafTrend<?> pChange, final String pPart) {
        generalParse(pChange, pPart);
    }

    /**
     * parses the probability out of PROB??
     *
     * @param pPart the string to parse.
     * @return probability of the trend.
     */
    protected int parseProbability(final String pPart) {
        return Integer.parseInt(pPart.substring(4));
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
