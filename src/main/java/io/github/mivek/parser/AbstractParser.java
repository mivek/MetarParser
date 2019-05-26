package io.github.mivek.parser;

import com.opencsv.CSVReader;
import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.AbstractWeatherCode;
import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Airport;
import io.github.mivek.model.Country;
import io.github.mivek.model.Visibility;
import io.github.mivek.model.WeatherCondition;
import io.github.mivek.parser.command.common.CloudCommand;
import io.github.mivek.parser.command.common.Command;
import io.github.mivek.parser.command.common.MainVisibilityCommand;
import io.github.mivek.parser.command.common.MainVisibilityNauticalMilesCommand;
import io.github.mivek.parser.command.common.MinimalVisibilityCommand;
import io.github.mivek.parser.command.common.VerticalVisibilityCommand;
import io.github.mivek.parser.command.common.WindCommand;
import io.github.mivek.parser.command.common.WindExtremeCommand;
import io.github.mivek.parser.command.common.WindShearCommand;
import io.github.mivek.utils.Regex;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract class for parser.
 * @author mivek
 * Abstract class for Parser.
 * @param <T> a concrete subclass of {@link AbstractWeatherCode}.
 */
public abstract class AbstractParser<T extends AbstractWeatherCode> {
    /** Pattern regex for wind. */
    public static final Pattern WIND_REGEX = Pattern.compile("(\\w{3})(\\d{2})G?(\\d{2})?(KT|MPS|KM\\/H)");
    /** Pattern regex for windshear. */
    public static final Pattern WIND_SHEAR_REGEX = Pattern.compile("WS(\\d{3})\\/(\\w{3})(\\d{2})G?(\\d{2})?(KT|MPS|KM\\/H)");
    /** Pattern regex for extreme winds. */
    public static final Pattern WIND_EXTREME_REGEX = Pattern.compile("^(\\d{3})V(\\d{3})");
    /** Pattern for the main visibility. */
    public static final Pattern MAIN_VISIBILITY_REGEX = Pattern.compile("^(\\d{4})(|NDV)$");
    /** Pattern for the main visibility in SM. */
    public static final Pattern MAIN_VISIBILITY_SM_REGEX = Pattern.compile("^(\\d)*(\\s)?((\\d\\/\\d)?SM)$");
    /** Pattern to recognize clouds. */
    public static final Pattern CLOUD_REGEX = Pattern.compile("^([A-Z]{3})(\\d{3})?([A-Z]{2,3})?$");
    /** Pattern for the vertical visibility. */
    public static final Pattern VERTICAL_VISIBILITY = Pattern.compile("^VV(\\d{3})$");
    /** Pattern for the minimum visibility. */
    public static final Pattern MIN_VISIBILITY_REGEX = Pattern.compile("^(\\d{4}[a-z])$");
    /** From shortcut constant. */
    protected static final String FM = "FM";
    /** Tempo shortcut constant. */
    protected static final String TEMPO = "TEMPO";
    /** BECMG shortcut constant. */
    protected static final String BECMG = "BECMG";
    /** Pattern for RMK. */
    protected static final String RMK = "RMK";
    /** Pattern regex to tokenize the code. */
    private static final Pattern TOKENIZE_REGEX = Pattern.compile("(\\d \\d\\/\\dSM|\\S+)");
    /** Pattern regex for the intensity of a phenomenon. */
    private static final Pattern INTENSITY_REGEX = Pattern.compile("^(-|\\+|VC)");
    /** Pattern for CAVOK. */
    private static final String CAVOK = "CAVOK";

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractParser.class.getName());

    /**
     * Path of airport file.
     */
    private final InputStream fAirportsFile = AbstractParser.class.getClassLoader()
            .getResourceAsStream("data/airports.dat");
    /**
     * Path of countries file.
     */
    private final InputStream fCountriesFile = AbstractParser.class.getClassLoader()
            .getResourceAsStream("data/countries.dat");
    /**
     * Map of airports.
     */
    private Map<String, Airport> fAirports;
    /**
     * Map of countries.
     */
    private Map<String, Country> fCountries;

    /** The remark parser. */
    private final RemarkParser fRemarkParser;
    /** The list of patterns. */
    private final List<Pattern> patterns;

    /** The map of commands. */
    private final Map<Pattern, Command> commandMap;
    /**
     * Constructor.
     */
    protected AbstractParser() {
        initCountries();
        initAirports();
        fRemarkParser = RemarkParser.getInstance();
        patterns = buildPatternsList();
        commandMap = buildCommands();
    }

    /**
     * Initiate airports map.
     */
    private void initAirports() {
        fAirports = new HashMap<>();
        String[] line;
        try (CSVReader reader = new CSVReader(new InputStreamReader(fAirportsFile, StandardCharsets.UTF_8))) {
            while ((line = reader.readNext()) != null) {
                Airport airport = new Airport();
                airport.setName(line[1]);
                airport.setCity(line[2]);
                airport.setCountry(fCountries.get(line[3]));
                airport.setIata(line[4]);
                airport.setIcao(line[5]);
                airport.setLatitude(Double.parseDouble(line[6]));
                airport.setLongitude(Double.parseDouble(line[7]));
                airport.setAltitude(Integer.parseInt(line[8]));
                airport.setTimezone(line[9]);
                airport.setDst(line[10]);
                fAirports.put(airport.getIcao(), airport);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    /**
     * Initiate countries map.
     */
    private void initCountries() {
        fCountries = new HashMap<>();
        String[] line;
        try (CSVReader reader = new CSVReader(new InputStreamReader(fCountriesFile, StandardCharsets.UTF_8))) {
            while ((line = reader.readNext()) != null) {
                Country country = new Country();
                country.setName(line[0]);
                fCountries.put(country.getName(), country);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    /**
     * Parses the minimal visibility and updates the visibility object.
     * @param pVisibility the visibility object
     * @param pVisibilityPart the string containing the information.
     */
    protected void parseMinimalVisibility(final Visibility pVisibility, final String pVisibilityPart) {
        String[] matches = Regex.pregMatch(MIN_VISIBILITY_REGEX, pVisibilityPart);
        pVisibility.setMinVisibility(Integer.parseInt(matches[1].substring(0, 4)));
        pVisibility.setMinDirection(matches[1].substring(4));
    }


    /**
     * This method parses the weather conditions of the metar.
     * @param weatherPart
     * String representing the weather.
     * @return a weather condition with its intensity, its descriptor and the
     * phenomenon.
     */
    protected WeatherCondition parseWeatherCondition(final String weatherPart) {
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
     * @param pWeatherCode The weather code.
     * @param pTime the string to parse.
     */
    protected void parseDeliveryTime(final AbstractWeatherCode pWeatherCode, final String pTime) {
        pWeatherCode.setDay(Integer.parseInt(pTime.substring(0, 2)));
        int hours = Integer.parseInt(pTime.substring(2, 4));
        int minutes = Integer.parseInt(pTime.substring(4, 6));
        LocalTime t = LocalTime.of(hours, minutes);
        pWeatherCode.setTime(t);
    }

    /**
     * @return the airports
     */
    protected Map<String, Airport> getAirports() {
        return fAirports;
    }

    /**
     * Abstract method parse.
     * @param pCode the message to parse.
     * @throws ParseException when an error occurs during parsing.
     * @return The decoded object.
     */
    public abstract T parse(String pCode) throws ParseException;

    /**
     * Method that parses common elements of a abstract weather container.
     * @param pContainer The object to update
     * @param pPart the token to parse.
     * @return boolean if the token pPart as been parsed.
     */
    protected boolean generalParse(final AbstractWeatherContainer pContainer, final String pPart) {
        if (CAVOK.equals(pPart)) {
            pContainer.setCavok(true);
            if (pContainer.getVisibility() == null) {
                pContainer.setVisibility(new Visibility());
            }
            pContainer.getVisibility().setMainVisibility(">10km");
            return true;
        }
        for (Pattern p : patterns) {
            if (Regex.find(p, pPart)) {
                Command command = commandMap.get(p);
                return command.execute(pContainer, pPart);
            }
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
    protected void parseRMK(final AbstractWeatherContainer pContainer, final String[] pParts, final int index) {
        String[] subArray = Arrays.copyOfRange(pParts, index + 1, pParts.length);
        pContainer.setRemark(fRemarkParser.parse(String.join(" ", subArray)));
    }

    /**
     * Splits a string between spaces except if the space is between two digits with
     * SM.
     * @param pCode the string to parse
     * @return a array of tokens
     */
    protected String[] tokenize(final String pCode) {
        List<String> tokens = new ArrayList<>();

        Matcher m = TOKENIZE_REGEX.matcher(pCode);
        while (m.find()) {
            tokens.add(m.group(1));
        }
        return tokens.toArray(new String[0]);
    }

    /**
     * @return the list of patterns
     */
    protected List<Pattern> buildPatternsList() {
        List<Pattern> listPattern = new ArrayList<>();
        listPattern.add(WIND_SHEAR_REGEX);
        listPattern.add(WIND_REGEX);
        listPattern.add(WIND_EXTREME_REGEX);
        listPattern.add(MAIN_VISIBILITY_REGEX);
        listPattern.add(MAIN_VISIBILITY_SM_REGEX);
        listPattern.add(MIN_VISIBILITY_REGEX);
        listPattern.add(VERTICAL_VISIBILITY);
        listPattern.add(CLOUD_REGEX);
        return listPattern;
    }

    /**
     * Builds the map of command map.
     *
     * @return the patterns with their command.
     */
    protected Map<Pattern, Command> buildCommands() {
        Map<Pattern, Command> map = new HashMap<>();
        map.put(WIND_SHEAR_REGEX, new WindShearCommand());
        map.put(WIND_REGEX, new WindCommand());
        map.put(WIND_EXTREME_REGEX, new WindExtremeCommand());
        map.put(MAIN_VISIBILITY_REGEX, new MainVisibilityCommand());
        map.put(MAIN_VISIBILITY_SM_REGEX, new MainVisibilityNauticalMilesCommand());
        map.put(MIN_VISIBILITY_REGEX, new MinimalVisibilityCommand());
        map.put(VERTICAL_VISIBILITY, new VerticalVisibilityCommand());
        map.put(CLOUD_REGEX, new CloudCommand());
        return map;
    }
}
