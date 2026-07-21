package io.github.mivek.parser;

import io.github.mivek.command.AirportSupplier;
import io.github.mivek.command.common.CommonCommandSupplier;
import io.github.mivek.command.taf.Command;
import io.github.mivek.command.taf.TAFCommandSupplier;
import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseErrorType;
import io.github.mivek.exception.ParseException;
import io.github.mivek.factory.FactoryProvider;
import io.github.mivek.model.Airport;
import io.github.mivek.model.TAF;
import io.github.mivek.model.TemperatureDated;
import io.github.mivek.model.trend.AbstractTafTrend;
import io.github.mivek.model.trend.validity.AbstractValidity;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author mivek
 */
public final class TAFParser extends AbstractWeatherCodeParser<TAF> {
    /** String constant for TAF. */
    public static final String TAF = "TAF";
    /** Temperature Maximum Constant. */
    private static final String TX = "TX";
    /** Temperature Minimum Constant. */
    private static final String TN = "TN";
    /** Temperature regex pattern. */
    private static final Pattern TEMPERATURE_REGEX = Pattern.compile("^(TX|TN)M?\\d{2}/\\d{4}Z$");
    /** TAF command supplier. */
    private final TAFCommandSupplier supplier;

    /**
     * Default constructor.
     */
    public TAFParser() {
        this(new CommonCommandSupplier(), new RemarkParser(), new AirportSupplier(), new TAFCommandSupplier());
    }

    /**
     * Dependency injection constructor.
     *
     * @param commonCommandSupplier the common command supplier
     * @param remarkParser          the remark parser.
     * @param airportSupplier       the airport supplier.
     * @param tafCommandSupplier    the taf command supplier.
     */
    public TAFParser(final CommonCommandSupplier commonCommandSupplier, final RemarkParser remarkParser, final AirportSupplier airportSupplier, final TAFCommandSupplier tafCommandSupplier) {
        super(commonCommandSupplier, remarkParser, airportSupplier);
        supplier = tafCommandSupplier;
    }

    @Override
    public TAF parse(final String code) throws ParseException {
        String[][] lines = extractLineTokens(code);
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
        if (parseFlags(taf, line1parts[i])) {
            i++;
        }
        // Airport
        Airport airport = getAirportSupplier().get(line1parts[i]);
        taf.setStation(line1parts[i]);
        i++;
        taf.setAirport(airport);
        taf.setMessage(code);
        // Day and time
        if(parseDeliveryTime(taf, line1parts[i], i)) {
            i++;
        }
        // Validity Time
        String validityToken = line1parts[i];
        if (!Regex.match(VALIDITY_REGEX, validityToken)) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_MESSAGE, ParseErrorType.VALIDITY, validityToken, i);
        }
        taf.setValidity(parseValidity(validityToken));

        // Handle rest of second line.
        for (int j = i + 1; j < line1parts.length; j++) {
            String part = line1parts[j];
            if (RMK.equals(part)) {
                parseRMK(taf, line1parts, j);
                break;
            } else if (part.startsWith(TX)) {
                taf.setMaxTemperature(parseTemperature(part, j));
            } else if (part.startsWith(TN)) {
                taf.setMinTemperature(parseTemperature(part, j));
            } else {
                executeCommand(taf, part);
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
     * Execute the command from the taf spplier or the common supplier.
     * @param taf The taf to update
     * @param input The string to test.
     */
    void executeCommand(final TAF taf, final String input) {
        Command command = supplier.get(input);
        if (command != null) {
            command.execute(taf, input);
        } else {
            parseFlags(taf, input);
            generalParse(taf, input);
        }
    }

    /**
     * Extracts all lines and tokenize them.
     *
     * @param tafCode raw TAF which may already contains some linebreaks
     * @return 2d jagged array containing lines and their tokens
     */
    private String[][] extractLineTokens(final String tafCode) {
        String cleanedInput = tafCode.replace("\n", " ")   // remove all linebreaks
                .replaceAll("\\s{2,}", " ");  // remove unnecessary whitespaces

        String[] lines = cleanedInput.replaceAll("\\s(PROB\\d{2}\\sTEMPO|TEMPO|INTER|BECMG|FM(?![A-Z]{2}\\s)|PROB)", "\n$1").split("\n");
        String[][] lineTokens = Arrays.stream(lines).map(this::tokenize).toArray(String[][]::new);
        if (lineTokens.length > 1) {
            // often temperatures are set in the end of the TAF report
            String[] last = lineTokens[lines.length - 1];
            List<String> temperatures = Arrays.stream(last).filter(code -> code.startsWith(TX) || code.startsWith(TN)).toList();
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
     * @param taf   the TAF object to build
     * @param parts the token of the line
     */
    void processLines(final TAF taf, final String[] parts) throws ParseException {
        if (parts[0].length() < 2) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_MESSAGE, ParseErrorType.TREND, parts[0], 0);
        }
        AbstractTAFTrendParser<? extends AbstractValidity> trendParser = FactoryProvider.getTrendParser().create(parts[0].substring(0, 2));
        if (trendParser == null) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_MESSAGE, ParseErrorType.TREND, parts[0], 0);
        }
        AbstractTafTrend<? extends AbstractValidity> trend = trendParser.parse(parts);
        taf.addTrend(trend);
    }

    /**
     * Parse the temperature.
     *
     * @param tempPart the string to parse.
     * @param position the token index.
     * @return a temperature with its date.
     * @throws ParseException if the temperature token is malformed.
     */
    TemperatureDated parseTemperature(final String tempPart, final int position) throws ParseException {
        if (!Regex.match(TEMPERATURE_REGEX, tempPart)) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_MESSAGE, ParseErrorType.TEMPERATURE, tempPart, position);
        }
        try {
            TemperatureDated temperature = new TemperatureDated();
            String[] parts = tempPart.split("/");
            temperature.setTemperature(Converter.convertTemperature(parts[0].substring(2)));
            temperature.setDay(Integer.parseInt(parts[1].substring(0, 2)));
            temperature.setHour(Integer.parseInt(parts[1].substring(2, 4)));
            return temperature;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_MESSAGE, ParseErrorType.TEMPERATURE, tempPart, position);
        }
    }

}
