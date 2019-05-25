package io.github.mivek.parser;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.Airport;
import io.github.mivek.model.Metar;
import io.github.mivek.model.trend.AbstractMetarTrend;
import io.github.mivek.model.trend.BECMGMetarTrend;
import io.github.mivek.model.trend.TEMPOMetarTrend;
import io.github.mivek.model.trend.validity.ATTime;
import io.github.mivek.model.trend.validity.FMTime;
import io.github.mivek.model.trend.validity.TLTime;
import io.github.mivek.parser.command.metar.*;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * This controller contains methods that parse the metar code. This class is a
 * singleton.
 * @author mivek
 */
public final class MetarParser extends AbstractParser<Metar> {
    /** Pattern of the temperature block. */
    public static final Pattern TEMPERATURE_REGEX = Pattern.compile("^(M?\\d{2})/(M?\\d{2})$");
    /** Pattern of the altimeter (Pascals). */
    public static final Pattern ALTIMETER_REGEX = Pattern.compile("^Q(\\d{4})$");
    /** Pattern for the altimeter in inches of mercury. */
    public static final Pattern ALTIMETER_MERCURY_REGEX = Pattern.compile("^A(\\d{4})$");
    /** Pattern to recognize a runway. */
    private static final Pattern GENERIC_RUNWAY_REGEX = Pattern.compile("^(R\\d{2}\\w?/)");
    /** Constant string for TL. */
    private static final String TILL = "TL";
    /** Constant string for AT. */
    private static final String AT = "AT";
    /** Instance of the class. */
    private static MetarParser instance = new MetarParser();
    /** The command map. */
    private final Map<Pattern, Command> commandMap;
    /** The list of patterns. */
    private final List<Pattern> patternList;


    /**
     * Private constructor.
     */
    private MetarParser() {
        super();
        patternList = buildListPattern();
        commandMap = buildCommand();
    }

    /**
     * Get instance method.
     * @return the instance of MetarParser.
     */
    public static MetarParser getInstance() {
        return instance;
    }

    /**
     * This is the main method of the parser. This method checks if the airport
     * exists. If it does then the metar code is decoded.
     * @param pMetarCode String representing the metar.
     * @return a decoded metar object.
     * @throws ParseException when an error occurs.
     */
    @Override
    public Metar parse(final String pMetarCode) throws ParseException {
        Metar m = new Metar();
        String[] metarTab = tokenize(pMetarCode);
        Airport airport = getAirports().get(metarTab[0]);
        if (airport == null) {
            throw new ParseException(ErrorCodes.ERROR_CODE_AIRPORT_NOT_FOUND);
        }

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
                } else if (metarTab[i].equals(TEMPO) || metarTab[i].equals(BECMG)) {
                    AbstractMetarTrend trend;
                    trend = initTrend(metarTab[i]);
                    i = iterTrend(i, trend, metarTab);
                    m.addTrend(trend);
                } else {
                    iterPatterns(m, metarTab[i]);
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
     * Iterate over the patterns and builds the metar.
     * @param pM the metar
     * @param pInput the string to parse.
     */
    private void iterPatterns(final Metar pM, final String pInput) {
        for (Pattern p : patternList) {
            if (Regex.find(p, pInput)) {
                Command command = commandMap.get(p);
                command.execute(pM, pInput);
            }
        }
    }

    /**
     * Iterates over an array and parses the trends.
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
     * @param pTrend the abstractMetarTrend object to update.
     * @param pPart The token to parse.
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

    /**
     * Builds the map of command.
     *
     * @return the map of command.
     */
    protected Map<Pattern, Command> buildCommand() {
        Map<Pattern, Command> map = new HashMap<>();
        map.put(GENERIC_RUNWAY_REGEX, new RunwayCommand());
        map.put(TEMPERATURE_REGEX, new TemperatureCommand());
        map.put(ALTIMETER_REGEX, new AltimeterCommand());
        map.put(ALTIMETER_MERCURY_REGEX, new AltimeterMecuryCommand());
        return map;
    }

    /**
     * Builds the list of patterns.
     *
     * @return the list of patterns.
     */
    protected List<Pattern> buildListPattern() {
        List<Pattern> patterns = new ArrayList<>();
        patterns.add(GENERIC_RUNWAY_REGEX);
        patterns.add(TEMPERATURE_REGEX);
        patterns.add(ALTIMETER_REGEX);
        patterns.add(ALTIMETER_MERCURY_REGEX);
        return patterns;
    }

}
