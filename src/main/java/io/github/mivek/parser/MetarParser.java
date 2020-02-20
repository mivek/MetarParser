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
    private static MetarParser instance = new MetarParser();
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
     * @param pCommonCommandSupplier      the command command supplier
     * @param pRemarkParser               the remark parser
     * @param pMetarParserCommandSupplier the metar command supplier.
     * @param pAirportSupplier            the airport supplier
     */
    protected MetarParser(final CommonCommandSupplier pCommonCommandSupplier, final RemarkParser pRemarkParser, final AirportSupplier pAirportSupplier,
            final MetarParserCommandSupplier pMetarParserCommandSupplier) {
        super(pCommonCommandSupplier, pRemarkParser, pAirportSupplier);
        supplier = pMetarParserCommandSupplier;
    }

    /**
     * Get instance method.
     *
     * @return the instance of MetarParser.
     */
    public static MetarParser getInstance() {
        return instance;
    }

    /**
     * This is the main method of the parser. This method checks if the airport
     * exists. If it does then the metar code is decoded.
     *
     * @param pMetarCode String representing the metar.
     * @return a decoded metar object.
     */
    @Override public Metar parse(final String pMetarCode) {
        Metar m = new Metar();
        String[] metarTab = tokenize(pMetarCode);
        Airport airport = getAirportSupplier().get(metarTab[0]);
        m.setStation(metarTab[0]);
        m.setAirport(airport);
        m.setMessage(pMetarCode);
        parseDeliveryTime(m, metarTab[1]);
        int metarTabLength = metarTab.length;
        for (int i = 2; i < metarTabLength; i++) {
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
        }
        return m;
    }

    /**
     * Initiate the trend according to string.
     *
     * @param pS the string to parse.
     * @return a concrete Trends object.
     */
    private AbstractMetarTrend initTrend(final String pS) {
        AbstractMetarTrend trend;
        if (pS.equals(TEMPO)) {
            trend = new TEMPOMetarTrend();
        } else {
            trend = new BECMGMetarTrend();
        }
        return trend;
    }

    /**
     * Execute the command given by the supplier.
     *
     * @param pM     the metar
     * @param pInput the string to parse.
     */
    private void executeCommand(final Metar pM, final String pInput) {
        Command command = supplier.get(pInput);
        if (command != null) {
            command.execute(pM, pInput);
        }
    }

    /**
     * Iterates over an array and parses the trends.
     *
     * @param pIndex the starting index.
     * @param pTrend the trend to update
     * @param pParts an array of strings
     * @return the next index to parse.
     */
    private int iterTrend(final int pIndex, final AbstractMetarTrend pTrend, final String[] pParts) {
        int i = pIndex + 1;
        while (i < pParts.length && !pParts[i].equals(TEMPO) && !pParts[i].equals(BECMG)) {
            processChange(pTrend, pParts[i]);
            i++;
        }
        return i - 1;
    }

    /**
     * Parses a string and updates the trend.
     *
     * @param pTrend the abstractMetarTrend object to update.
     * @param pPart  The token to parse.
     */
    private void processChange(final AbstractMetarTrend pTrend, final String pPart) {
        if (pPart.startsWith(AT)) {
            ATTime at = new ATTime();
            at.setTime(Converter.stringToTime(pPart.substring(2)));
            pTrend.addTime(at);
        } else if (pPart.startsWith(FM)) {
            FMTime fm = new FMTime();
            fm.setTime(Converter.stringToTime(pPart.substring(2)));
            pTrend.addTime(fm);
        } else if (pPart.startsWith(TILL)) {
            TLTime tl = new TLTime();
            tl.setTime(Converter.stringToTime(pPart.substring(2)));
            pTrend.addTime(tl);
        } else {
            generalParse(pTrend, pPart);
        }
    }

}
