package io.github.mivek.parser;

import io.github.mivek.command.AirportSupplier;
import io.github.mivek.command.common.CommonCommandSupplier;
import io.github.mivek.command.metar.Command;
import io.github.mivek.command.metar.MetarCommandSupplier;
import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseErrorType;
import io.github.mivek.exception.ParseException;
import io.github.mivek.factory.FactoryProvider;
import io.github.mivek.model.Metar;
import io.github.mivek.model.trend.MetarTrend;
import io.github.mivek.model.trend.validity.AbstractMetarTrendTime;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * This controller contains methods that parse the metar code. This class is a
 * singleton.
 *
 * @author mivek
 */
public final class MetarParser extends AbstractWeatherCodeParser<Metar> {
    /** Station ICAO pattern. */
    private static final Pattern STATION_PATTERN = Pattern.compile("^[A-Z]{4}$");
    /** The command supplier. */
    private final MetarCommandSupplier supplier;

    /**
     * Public default constructor.
     */
    public MetarParser() {
        this(new CommonCommandSupplier(), new RemarkParser(), new AirportSupplier(), new MetarCommandSupplier());
    }

    /**
     * Dependency injection constructor.
     *
     * @param commonCommandSupplier      the common command supplier
     * @param remarkParser               the remark parser
     * @param metarCommandSupplier the metar command supplier.
     * @param airportSupplier            the airport supplier
     */
    public MetarParser(final CommonCommandSupplier commonCommandSupplier, final RemarkParser remarkParser, final AirportSupplier airportSupplier,
            final MetarCommandSupplier metarCommandSupplier) {
        super(commonCommandSupplier, remarkParser, airportSupplier);
        supplier = Objects.requireNonNull(metarCommandSupplier);
    }

    /**
     * This is the main method of the parser. This method checks if the airport
     * exists. If it does then the metar code is decoded.
     *
     * @param code String representing the metar.
     * @return a decoded metar object.
     */
    @Override
    public Metar parse(final String code) throws ParseException {
        Metar m = new Metar();
        String[] metarTab = tokenize(code);
        int index = parseIdentification(m, metarTab, code);
        while (index < metarTab.length) {
            if (!generalParse(m, metarTab[index]) && !parseFlags(m, metarTab[index])) {
                if ("NOSIG".equals(metarTab[index])) {
                    m.setNosig(true);
                } else if (RMK.equals(metarTab[index])) {
                    parseRMK(m, metarTab, index);
                    break;
                } else if (metarTab[index].equals(TEMPO) || metarTab[index].equals(BECMG)) {
                    MetarTrend trend = new MetarTrend(WeatherChangeType.valueOf(metarTab[index]));
                    index = parseTrend(index, trend, metarTab);
                    m.addTrend(trend);
                } else {
                    executeCommand(m, metarTab[index]);
                }
            }
            index++;
        }
        return m;
    }

    /**
     * Parses the report-type prefix, station and delivery time.
     *
     * @param m        the metar being built.
     * @param metarTab the tokenized message.
     * @param code     the raw message.
     * @return the index of the first body token.
     * @throws ParseException if the station or delivery time is missing or malformed.
     */
    private int parseIdentification(final Metar m, final String[] metarTab, final String code) throws ParseException {
        int startIndex = 0;
        if (metarTab.length > 0) {
            if ("METAR".equals(metarTab[0])) {
                m.setReportType(io.github.mivek.enums.ReportType.METAR);
                startIndex = 1;
            } else if ("SPECI".equals(metarTab[0])) {
                m.setReportType(io.github.mivek.enums.ReportType.SPECI);
                startIndex = 1;
            }
        }
        if (startIndex >= metarTab.length) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_MESSAGE, ParseErrorType.STATION, null, startIndex);
        }
        String stationToken = metarTab[startIndex];
        if (!Regex.match(STATION_PATTERN, stationToken)) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_MESSAGE, ParseErrorType.STATION, stationToken, startIndex);
        }
        m.setStation(stationToken);
        m.setAirport(getAirportSupplier().get(stationToken));
        m.setMessage(code);
        if (startIndex + 1 >= metarTab.length) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_MESSAGE, ParseErrorType.DELIVERY_TIME, null, startIndex + 1);
        }
        parseDeliveryTime(m, metarTab[startIndex + 1], startIndex + 1);
        return startIndex + 2;
    }

    /**
     * Execute the command given by the supplier.
     *
     * @param metar the metar
     * @param input the string to parse.
     */
    private void executeCommand(final Metar metar, final String input) throws ParseException {
        Command command = supplier.get(input);
        if (command != null) {
            command.execute(metar, input);
        }
    }

    /**
     * Iterates over an array and parses the trends.
     *
     * @param index the starting index.
     * @param trend the trend to update
     * @param parts an array of strings
     * @return the next index to parse.
     */
    private int parseTrend(final int index, final MetarTrend trend, final String[] parts) {
        int i = index + 1;
        while (i < parts.length && !parts[i].equals(TEMPO) && !parts[i].equals(BECMG)) {
            updateTrend(trend, parts[i]);
            i++;
        }
        return i - 1;
    }

    /**
     * Parses a string and updates the trend.
     *
     * @param trend the abstractMetarTrend object to update.
     * @param part  The token to parse.
     */
    private void updateTrend(final MetarTrend trend, final String part) {
        AbstractMetarTrendTime time = FactoryProvider.getMetarTrendTimeFactory().create(part.substring(0, 2));
        if (time != null) {
            time.setTime(Converter.stringToTime(part.substring(2)));
            trend.addTime(time);
        } else {
            generalParse(trend, part);
        }
    }

}
