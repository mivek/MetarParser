package io.github.mivek.parser;

import io.github.mivek.command.AirportSupplier;
import io.github.mivek.command.common.CommonCommandSupplier;
import io.github.mivek.command.metar.Command;
import io.github.mivek.command.metar.MetarParserCommandSupplier;
import io.github.mivek.model.Airport;
import io.github.mivek.model.Metar;
import io.github.mivek.model.trend.AbstractMetarTrend;
import io.github.mivek.model.trend.BECMGMetarTrend;
import io.github.mivek.model.trend.TEMPOMetarTrend;
import io.github.mivek.model.trend.validity.ATTime;
import io.github.mivek.model.trend.validity.FMTime;
import io.github.mivek.model.trend.validity.TLTime;
import io.github.mivek.utils.Converter;

/**
 * This controller contains methods that parse the metar code. This class is a
 * singleton.
 *
 * @author mivek
 */
public final class MetarParser extends AbstractParser<Metar> {
    /** Constant string for TL. */
    private static final String TILL = "TL";
    /** Constant string for AT. */
    private static final String AT = "AT";
    /** Instance of the class. */
    private static final MetarParser INSTANCE = new MetarParser();
    /** The command supplier. */
    private final MetarParserCommandSupplier supplier;

    /**
     * Private constructor.
     */
    private MetarParser() {
        this(new CommonCommandSupplier(), RemarkParser.getInstance(), new AirportSupplier(), new MetarParserCommandSupplier());
    }

    /**
     * Dependency injection constructor.
     *
     * @param commonCommandSupplier      the command command supplier
     * @param remarkParser               the remark parser
     * @param metarParserCommandSupplier the metar command supplier.
     * @param airportSupplier            the airport supplier
     */
    protected MetarParser(final CommonCommandSupplier commonCommandSupplier, final RemarkParser remarkParser, final AirportSupplier airportSupplier,
            final MetarParserCommandSupplier metarParserCommandSupplier) {
        super(commonCommandSupplier, remarkParser, airportSupplier);
        supplier = metarParserCommandSupplier;
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
                    AbstractMetarTrend trend;
                    trend = initTrend(metarTab[i]);
                    i = iterTrend(i, trend, metarTab);
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
     * Initiate the trend according to string.
     *
     * @param trendpart the string to parse.
     * @return a concrete Trends object.
     */
    private AbstractMetarTrend initTrend(final String trendpart) {
        AbstractMetarTrend trend;
        if (trendpart.equals(TEMPO)) {
            trend = new TEMPOMetarTrend();
        } else {
            trend = new BECMGMetarTrend();
        }
        return trend;
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
    private int iterTrend(final int index, final AbstractMetarTrend trend, final String[] parts) {
        int i = index + 1;
        while (i < parts.length && !parts[i].equals(TEMPO) && !parts[i].equals(BECMG)) {
            processChange(trend, parts[i]);
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
    private void processChange(final AbstractMetarTrend trend, final String part) {
        if (part.startsWith(AT)) {
            ATTime at = new ATTime();
            at.setTime(Converter.stringToTime(part.substring(2)));
            trend.addTime(at);
        } else if (part.startsWith(FM)) {
            FMTime fm = new FMTime();
            fm.setTime(Converter.stringToTime(part.substring(2)));
            trend.addTime(fm);
        } else if (part.startsWith(TILL)) {
            TLTime tl = new TLTime();
            tl.setTime(Converter.stringToTime(part.substring(2)));
            trend.addTime(tl);
        } else {
            generalParse(trend, part);
        }
    }

}
