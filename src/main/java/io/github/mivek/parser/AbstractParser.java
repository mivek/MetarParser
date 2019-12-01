package io.github.mivek.parser;

import io.github.mivek.command.AirportSupplier;
import io.github.mivek.command.common.Command;
import io.github.mivek.command.common.CommonCommandSupplier;
import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.AbstractWeatherCode;
import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Visibility;
import io.github.mivek.model.WeatherCondition;
import io.github.mivek.utils.Regex;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Abstract class for parser.
 *
 * @param <T> a concrete subclass of {@link AbstractWeatherCode}.
 * @author mivek
 * Abstract class for Parser.
 */
public abstract class AbstractParser<T extends AbstractWeatherCode> {

    /** From shortcut constant. */
    protected static final String FM = "FM";
    /** Tempo shortcut constant. */
    protected static final String TEMPO = "TEMPO";
    /** BECMG shortcut constant. */
    protected static final String BECMG = "BECMG";
    /** Pattern for RMK. */
    protected static final String RMK = "RMK";
    /** Pattern regex to tokenize the code. */
    private static final Pattern TOKENIZE_REGEX = Pattern.compile("\\s((?=\\d/\\dSM)(?<!\\s\\d\\s)|(?!\\d/\\dSM))|=\\z");
    /** Pattern regex for the intensity of a phenomenon. */
    private static final Pattern INTENSITY_REGEX = Pattern.compile("^(-|\\+|VC)");
    /** Pattern for CAVOK. */
    private static final String CAVOK = "CAVOK";
    /** The common command commonSupplier. */
    private final CommonCommandSupplier commonSupplier;
    /** The remark parser. */
    private final RemarkParser remarkParser;
    /** The airport supplier. */
    private final AirportSupplier airportSupplier;

    /**
     * Dependency injection constructor.
     * @param pCommonCommandSupplier the common command supplier
     * @param pRemarkParser the remark parser.
     * @param pAirportSupplier the airport supplier.
     */
    AbstractParser(final CommonCommandSupplier pCommonCommandSupplier, final RemarkParser pRemarkParser, final AirportSupplier pAirportSupplier) {
        commonSupplier = pCommonCommandSupplier;
        remarkParser = pRemarkParser;
        airportSupplier = pAirportSupplier;
    }

    /**
     * This method parses the weather conditions of the metar.
     *
     * @param weatherPart String representing the weather.
     * @return a weather condition with its intensity, its descriptor and the
     * phenomenon.
     */
    WeatherCondition parseWeatherCondition(final String weatherPart) {
        WeatherCondition wc = new WeatherCondition();
        String match;
        if (Regex.find(INTENSITY_REGEX, weatherPart)) {
            match = Regex.findString(INTENSITY_REGEX, weatherPart);
            Intensity i = Intensity.getEnum(match);
            wc.setIntensity(i);
        }
        for (Descriptive des : Descriptive.values()) {
            if (Regex.findString(Pattern.compile("(" + des.getShortcut() + ")"), weatherPart) != null) {
                wc.setDescriptive(des);
                break;
            }
        }
        for (Phenomenon phe : Phenomenon.values()) {
            if (Regex.findString(Pattern.compile("(" + phe.getShortcut() + ")"), weatherPart) != null) {
                wc.addPhenomenon(phe);
            }
        }
        if (wc.isValid()) {
            return wc;
        }
        return null;
    }

    /**
     * Parses the string containing the delivery time.
     *
     * @param pWeatherCode The weather code.
     * @param pTime        the string to parse.
     */
    void parseDeliveryTime(final AbstractWeatherCode pWeatherCode, final String pTime) {
        pWeatherCode.setDay(Integer.parseInt(pTime.substring(0, 2)));
        int hours = Integer.parseInt(pTime.substring(2, 4));
        int minutes = Integer.parseInt(pTime.substring(4, 6));
        LocalTime t = LocalTime.of(hours, minutes);
        pWeatherCode.setTime(t);
    }

    /**
     * Abstract method parse.
     *
     * @param pCode the message to parse.
     * @return The decoded object.
     * @throws ParseException when an error occurs during parsing.
     */
    public abstract T parse(String pCode) throws ParseException;

    /**
     * Method that parses common elements of a abstract weather container.
     *
     * @param pContainer The object to update
     * @param pPart      the token to parse.
     * @return boolean if the token pPart as been parsed.
     */
    boolean generalParse(final AbstractWeatherContainer pContainer, final String pPart) {
        if (CAVOK.equals(pPart)) {
            pContainer.setCavok(true);
            if (pContainer.getVisibility() == null) {
                pContainer.setVisibility(new Visibility());
            }
            pContainer.getVisibility().setMainVisibility(">10km");
            return true;
        }

        Command command = commonSupplier.get(pPart);
        if (command != null && command.canParse(pPart)) {
            return command.execute(pContainer, pPart);
        }

        WeatherCondition wc = parseWeatherCondition(pPart);
        return pContainer.addWeatherCondition(wc);
    }

    /***
     * Adds the remark part to the event.
     * @param pContainer the event to update
     * @param pParts the tokens of the event
     * @param index the RMK index in the event.
     */
    void parseRMK(final AbstractWeatherContainer pContainer, final String[] pParts, final int index) {
        String[] subArray = Arrays.copyOfRange(pParts, index + 1, pParts.length);
        pContainer.setRemark(remarkParser.parse(String.join(" ", subArray)));
    }

    /**
     * Splits a string between spaces except if the space is between two digits with
     * SM.
     *
     * @param pCode the string to parse
     * @return a array of tokens
     */
    String[] tokenize(final String pCode) {
        return TOKENIZE_REGEX.split(pCode);
    }

    /**
     * @return the Airport supplier.
     */
    AirportSupplier getAirportSupplier() {
        return airportSupplier;
    }
}
