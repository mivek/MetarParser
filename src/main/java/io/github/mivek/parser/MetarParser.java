package io.github.mivek.parser;

import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.Airport;
import io.github.mivek.model.Metar;
import io.github.mivek.model.RunwayInfo;
import io.github.mivek.model.Visibility;
import io.github.mivek.model.trend.AbstractMetarTrend;
import io.github.mivek.model.trend.BECMGMetarTrend;
import io.github.mivek.model.trend.TEMPOMetarTrend;
import io.github.mivek.model.trend.validity.ATTime;
import io.github.mivek.model.trend.validity.FMTime;
import io.github.mivek.model.trend.validity.TLTime;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;

/**
 * This controller contains methods that parse the metar code. This class is a
 * singleton.
 * @author mivek
 */
public final class MetarParser extends AbstractParser<Metar> {
    /**
     * Instance of the class.
     */
    private static MetarParser instance = new MetarParser();
    /**
     * Pattern regex for runway with min and max range visibility.
     */
    private static final Pattern RUNWAY_MAX_RANGE_REGEX = Pattern.compile("^R(\\d{2}\\w?)\\/(\\d{4})V(\\d{3})(\\w{0,2})");
    /**
     * Pattern regex for runway visibility.
     */
    private static final Pattern RUNWAY_REGEX = Pattern.compile("^R(\\d{2}\\w?)\\/(\\w)?(\\d{4})(\\w{0,2})$");
    /**
     * Pattern to recognize a runway.
     */
    private static final Pattern GENERIC_RUNWAY_REGEX = Pattern.compile("^(R\\d{2}\\w?\\/)");
    /**
     * Pattern of the temperature block.
     */
    private static final Pattern TEMPERATURE_REGEX = Pattern.compile("^(M?\\d{2})\\/(M?\\d{2})$");
    /**
     * Pattern of the altimeter (Pascals).
     */
    private static final Pattern ALTIMETER_REGEX = Pattern.compile("^Q(\\d{4})$");
    /**
     * Constant string for TL.
     */
    private static final String TILL = "TL";
    /**
     * Constant string for AT.
     */
    private static final String AT = "AT";

    /**
     * Private constructor.
     */
    private MetarParser() {
        super();
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
        String[] metarTab = pMetarCode.split(" ");
        Airport airport = getAirports().get(metarTab[0]);
        if (airport == null) {
            throw new ParseException(ErrorCodes.ERROR_CODE_AIRPORT_NOT_FOUND);
        }

        m.setAirport(airport);
        m.setMessage(pMetarCode);
        parseDeliveryTime(m, metarTab[1]);
        Visibility visibility = new Visibility();
        m.setVisibility(visibility);
        int metarTabLength = metarTab.length;
        for (int i = 2; i < metarTabLength; i++) {
            String[] matches;
            if (generalParse(m, metarTab[i])) {
                continue;
            } else if ("NOSIG".equals(metarTab[i])) {
                m.setNosig(true);
            } else if ("AUTO".equals(metarTab[i])) {
                m.setAuto(true);
            } else if (Regex.find(GENERIC_RUNWAY_REGEX, metarTab[i])) {
                RunwayInfo ri = parseRunWayAction(metarTab[i]);
                m.addRunwayInfo(ri);
            } else if (Regex.find(TEMPERATURE_REGEX, metarTab[i])) {
                matches = Regex.pregMatch(TEMPERATURE_REGEX, metarTab[i]);
                m.setTemperature(Converter.convertTemperature(matches[1]));
                m.setDewPoint(Converter.convertTemperature(matches[2]));
            } else if (Regex.find(ALTIMETER_REGEX, metarTab[i])) {
                matches = Regex.pregMatch(ALTIMETER_REGEX, metarTab[i]);
                m.setAltimeter(Integer.parseInt(matches[1]));
            } else if (metarTab[i].equals(TEMPO) || metarTab[i].equals(BECMG)) {
                AbstractMetarTrend trend;
                if (metarTab[i].equals(TEMPO)) {
                    trend = new TEMPOMetarTrend();
                } else {
                    trend = new BECMGMetarTrend();
                }
                i = iterTrend(i, trend, metarTab);
                m.addTrend(trend);
            }
        }
        return m;
    }

    /**
     * This method parses the string and returns an object.
     * @param runWayPart String containing the runway info
     * @return a object with parsed informations.
     */
    protected RunwayInfo parseRunWayAction(final String runWayPart) {
        String[] matches;
        RunwayInfo ri = new RunwayInfo();
        if (ArrayUtils.isNotEmpty(matches = Regex.pregMatch(RUNWAY_REGEX, runWayPart))) {
            ri.setName(matches[1]);
            ri.setMinRange(Integer.parseInt(matches[3]));
            ri.setTrend(Converter.convertTrend(matches[4]));
            return ri;
        } else if (ArrayUtils.isNotEmpty(matches = Regex.pregMatch(RUNWAY_MAX_RANGE_REGEX, runWayPart))) {
            ri.setName(matches[1]);
            ri.setMinRange(Integer.parseInt(matches[2]));
            ri.setMaxRange(Integer.parseInt(matches[3]));
            ri.setTrend(Converter.convertTrend(matches[4]));
            return ri;
        }
        return null;
    }

    /**
     * Iterates over an array and parses the trends.
     * @param pIndex the starting index.
     * @param pTrend the trend to update
     * @param pParts an array of strings
     * @return the next index to parse.
     */
    protected int iterTrend(final int pIndex, final AbstractMetarTrend pTrend, final String[] pParts) {
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
    protected void processChange(final AbstractMetarTrend pTrend, final String pPart) {
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
