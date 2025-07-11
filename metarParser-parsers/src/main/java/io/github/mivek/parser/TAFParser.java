package io.github.mivek.parser;

import io.github.mivek.command.AirportSupplier;
import io.github.mivek.command.common.CommonCommandSupplier;
import io.github.mivek.command.taf.Command;
import io.github.mivek.command.taf.TAFCommandSupplier;
import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.factory.FactoryProvider;
import io.github.mivek.model.Airport;
import io.github.mivek.model.TAF;
import io.github.mivek.model.TemperatureDated;
import io.github.mivek.model.trend.AbstractTafTrend;
import io.github.mivek.model.trend.validity.AbstractValidity;
import io.github.mivek.utils.Converter;
import java.util.Arrays;
import java.util.List;
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
    /** Instance of the TAFParser. */
    private static final TAFParser INSTANCE = new TAFParser();
    /** TAF command supplier. */
    private final TAFCommandSupplier supplier;

    /**
     * Default constructor.
     */
    private TAFParser() {
        this(new CommonCommandSupplier(), RemarkParser.getInstance(), new AirportSupplier(), new TAFCommandSupplier());
    }

    /**
     * Dependency injection constructor.
     *
     * @param commonCommandSupplier the common command supplier
     * @param remarkParser          the remark parser.
     * @param airportSupplier       the airport supplier.
     * @param tafCommandSupplier    the taf command supplier.
     */
    private TAFParser(final CommonCommandSupplier commonCommandSupplier, final RemarkParser remarkParser, final AirportSupplier airportSupplier, final TAFCommandSupplier tafCommandSupplier) {
        super(commonCommandSupplier, remarkParser, airportSupplier);
        supplier = tafCommandSupplier;
    }

    /**
     * @return the instance.
     */
    public static TAFParser getInstance() {
        return INSTANCE;
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
        if(parseDeliveryTime(taf, line1parts[i])) {
            i++;
        }
        // Validity Time
        taf.setValidity(parseValidity(line1parts[i]));

        // Handle rest of second line.
        for (int j = i + 1; j < line1parts.length; j++) {
            String part = line1parts[j];
            if (RMK.equals(part)) {
                parseRMK(taf, line1parts, j);
                break;
            } else if (part.startsWith(TX)) {
                taf.setMaxTemperature(parseTemperature(part));
            } else if (part.startsWith(TN)) {
                taf.setMinTemperature(parseTemperature(part));
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
    private void processLines(final TAF taf, final String[] parts) throws ParseException {
        AbstractTAFTrendParser<? extends AbstractValidity> trendParser = FactoryProvider.getTrendParser().create(parts[0].substring(0, 2));
        AbstractTafTrend<? extends AbstractValidity> trend = trendParser.parse(parts);
        taf.addTrend(trend);
    }

    /**
     * Parse the temperature.
     *
     * @param tempPart the string to parse.
     * @return a temperature with its date.
     */
    TemperatureDated parseTemperature(final String tempPart) {
        TemperatureDated temperature = new TemperatureDated();
        String[] parts = tempPart.split("/");
        temperature.setTemperature(Converter.convertTemperature(parts[0].substring(2)));
        temperature.setDay(Integer.parseInt(parts[1].substring(0, 2)));
        temperature.setHour(Integer.parseInt(parts[1].substring(2, 4)));
        return temperature;
    }

}
