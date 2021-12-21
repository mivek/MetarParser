package io.github.mivek.parser;

import io.github.mivek.command.AirportSupplier;
import io.github.mivek.command.common.CommonCommandSupplier;
import io.github.mivek.command.metar.Command;
import io.github.mivek.command.metar.MetarCommandSupplier;
import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.factory.FactoryProvider;
import io.github.mivek.model.Airport;
import io.github.mivek.model.Metar;
import io.github.mivek.model.trend.MetarTrend;
import io.github.mivek.model.trend.validity.AbstractMetarTrendTime;
import io.github.mivek.utils.Converter;
import java.util.Objects;

/**
 * This controller contains methods that parse the metar code. This class is a
 * singleton.
 *
 * @author mivek
 */
public final class MetarParser extends AbstractWeatherCodeParser<Metar> {
    /** Instance of the class. */
    private static final MetarParser INSTANCE = new MetarParser();
    /** The command supplier. */
    private final MetarCommandSupplier supplier;

    /**
     * Private constructor.
     */
    private MetarParser() {
        this(new CommonCommandSupplier(), RemarkParser.getInstance(), new AirportSupplier(), new MetarCommandSupplier());
    }

    /**
     * Dependency injection constructor.
     *
     * @param commonCommandSupplier      the common command supplier
     * @param remarkParser               the remark parser
     * @param metarCommandSupplier the metar command supplier.
     * @param airportSupplier            the airport supplier
     */
    MetarParser(final CommonCommandSupplier commonCommandSupplier, final RemarkParser remarkParser, final AirportSupplier airportSupplier,
            final MetarCommandSupplier metarCommandSupplier) {
        super(commonCommandSupplier, remarkParser, airportSupplier);
        supplier = Objects.requireNonNull(metarCommandSupplier);
    }

    /**
     * Get instance method.
     *
     * @return the instance of MetarParser.
     */
    public static MetarParser getInstance() {
        return INSTANCE;
    }

    /**
     * This is the main method of the parser. This method checks if the airport
     * exists. If it does then the metar code is decoded.
     *
     * @param code String representing the metar.
     * @return a decoded metar object.
     */
    @Override
    public Metar parse(final String code) {
        Metar m = new Metar();
        String[] metarTab = tokenize(code);
        Airport airport = getAirportSupplier().get(metarTab[0]);
        m.setStation(metarTab[0]);
        m.setAirport(airport);
        m.setMessage(code);
        parseDeliveryTime(m, metarTab[1]);
        int metarTabLength = metarTab.length;
        int i = 2;
        while (i < metarTabLength) {
            if (!generalParse(m, metarTab[i])) {
                if ("NOSIG".equals(metarTab[i])) {
                    m.setNosig(true);
                } else if ("AUTO".equals(metarTab[i])) {
                    m.setAuto(true);
                } else if (RMK.equals(metarTab[i])) {
                    parseRMK(m, metarTab, i);
                    break;
                } else if (metarTab[i].equals(TEMPO) || metarTab[i].equals(BECMG)) {
                    MetarTrend trend = new MetarTrend(WeatherChangeType.valueOf(metarTab[i]));
                    i = parseTrend(i, trend, metarTab);
                    m.addTrend(trend);
                } else {
                    executeCommand(m, metarTab[i]);
                }
            }
            i++;
        }
        return m;
    }

    /**
     * Execute the command given by the supplier.
     *
     * @param metar the metar
     * @param input the string to parse.
     */
    private void executeCommand(final Metar metar, final String input) {
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
