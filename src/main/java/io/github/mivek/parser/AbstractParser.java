package io.github.mivek.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import java.util.stream.Collectors;

import com.opencsv.CSVReader;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.CloudType;
import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.AbstractWeatherCode;
import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Airport;
import io.github.mivek.model.Cloud;
import io.github.mivek.model.Country;
import io.github.mivek.model.Visibility;
import io.github.mivek.model.WeatherCondition;
import io.github.mivek.model.Wind;
import io.github.mivek.model.WindShear;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;
import io.github.mivekinternationalization.Messages;

/**
 * Abstract class for parser.
 * @author mivek
 * Abstract class for Parser.
 * @param <T> a concrete subclass of {@link AbstractWeatherCode}.
 */
public abstract class AbstractParser<T extends AbstractWeatherCode> {
    /** Pattern regex to tokenize the code. */
    protected static final Pattern TOKENIZE_REGEX = Pattern.compile("(\\d \\d\\/\\dSM|\\S+)");
    /** Pattern regex for the intensity of a phenomenon. */
    protected static final Pattern INTENSITY_REGEX = Pattern.compile("^(-|\\+|VC)");
    /** Pattern regex for wind. */
    protected static final Pattern WIND_REGEX = Pattern.compile("(\\w{3})(\\d{2})G?(\\d{2})?(KT|MPS|KM\\/H)");
    /** Pattern regex for windshear. */
    protected static final Pattern WIND_SHEAR_REGEX = Pattern.compile("WS(\\d{3})\\/(\\w{3})(\\d{2})G?(\\d{2})?(KT|MPS|KM\\/H)");
    /** Pattern regex for extreme winds. */
    protected static final Pattern WIND_EXTREME_REGEX = Pattern.compile("^(\\d{3})V(\\d{3})");
    /** Pattern for the main visibility. */
    protected static final Pattern MAIN_VISIBILITY_REGEX = Pattern.compile("^(\\d{4})(|NDV)$");
    /** Pattern for the main visibility in SM. */
    protected static final Pattern MAIN_VISIBILITY_SM_REGEX = Pattern.compile("^(\\d)*(\\s)?((\\d\\/\\d)?SM)$");
    /** Pattern to recognize clouds. */
    protected static final Pattern CLOUD_REGEX = Pattern.compile("^([A-Z]{3})(\\d{3})?([A-Z]{2,3})?$");
    /** Pattern for the vertical visibility. */
    protected static final Pattern VERTICAL_VISIBILITY = Pattern.compile("^VV(\\d{3})$");
    /** Pattern for the minimum visibility. */
    protected static final Pattern MIN_VISIBILITY_REGEX = Pattern.compile("^(\\d{4}[a-z])$");
    /** From shortcut constant. */
    protected static final String FM = "FM";
    /** Tempo shortcut constant. */
    protected static final String TEMPO = "TEMPO";
    /** BECMG shortcut constant. */
    protected static final String BECMG = "BECMG";
    /** Pattern for CAVOK. */
    protected static final String CAVOK = "CAVOK";
    /** Pattern for RMK. */
    protected static final String RMK = "RMK";
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

    /**
     * Constructor.
     */
    protected AbstractParser() {
        initCountries();
        initAirports();
    }

    /**
     * Initiate airports map.
     */
    private void initAirports() {
        fAirports = new HashMap<>();
        String[] line;
        try (CSVReader reader = new CSVReader(new InputStreamReader(fAirportsFile))) {
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
        try (CSVReader reader = new CSVReader(new InputStreamReader(fCountriesFile))) {
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
     * This method parses the wind part of the metar code. It parses the generic
     * part.
     * @param pStringWind a string with wind elements.
     * @return a Wind element with the informations.
     */
    protected Wind parseWind(final String pStringWind) {
        Wind wind = new Wind();
        String[] windPart = Regex.pregMatch(WIND_REGEX, pStringWind);
        setWindElements(wind, windPart[1], windPart[2], windPart[3], windPart[4]);
        return wind;
    }

    /**
     * Sets the elements of the wind.
     * @param pWind the wind element.
     * @param pDirection the direction of the wind in degrees.
     * @param pSpeed the speed of the wind
     * @param pGust the speed of the gust if any
     * @param pUnit the unit.
     */
    private void setWindElements(final Wind pWind, final String pDirection, final String pSpeed, final String pGust, final String pUnit) {
        String direction = Converter.degreesToDirection(pDirection);
        pWind.setDirection(direction);
        if (!direction.equals(Messages.getInstance().getConverterVRB())) {
            pWind.setDirectionDegrees(Integer.parseInt(pDirection));
        }
        pWind.setSpeed(Integer.parseInt(pSpeed));
        if (pGust != null) {
            pWind.setGust(Integer.parseInt(pGust));
        }
        pWind.setUnit(pUnit);
    }

    /**
     * Parses the wind shear part.
     * @param pStringWindShear the string to parse
     * @return a wind shear object.
     */
    protected WindShear parseWindShear(final String pStringWindShear) {
        WindShear wind = new WindShear();
        String[] windPart = Regex.pregMatch(WIND_SHEAR_REGEX, pStringWindShear);
        wind.setHeight(100 * Integer.parseInt(windPart[1]));
        setWindElements(wind, windPart[2], windPart[3], windPart[4], windPart[5]);
        return wind;
    }

    /**
     * Parses the wind.
     * @param pWind the wind to update
     * @param pExtremeWind String with extreme wind information
     */
    protected void parseExtremeWind(final Wind pWind, final String pExtremeWind) {
        String[] matches = Regex.pregMatch(WIND_EXTREME_REGEX, pExtremeWind);
        pWind.setExtreme1(Integer.parseInt(matches[1]));
        pWind.setExtreme2(Integer.parseInt(matches[2]));
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
     * This method parses the cloud part of the metar.
     * @param pCloudString string with cloud elements.
     * @return a decoded cloud with its quantity, its altitude and its type.
     */
    protected Cloud parseCloud(final String pCloudString) {
        Cloud cloud = new Cloud();
        String[] cloudPart = Regex.pregMatch(CLOUD_REGEX, pCloudString);
        try {
            CloudQuantity cq = CloudQuantity.valueOf(cloudPart[1]);
            cloud.setQuantity(cq);
            if (cloudPart[2] != null) {
                cloud.setHeight(100 * Integer.parseInt(cloudPart[2]));
                if (cloudPart[3] != null) {
                    CloudType ct = CloudType.valueOf(cloudPart[3]);
                    cloud.setType(ct);
                }
            }
            return cloud;
        } catch (IllegalArgumentException e) {
            return null;
        }
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
        String match = null;
        if (Regex.find(INTENSITY_REGEX, weatherPart)) {
            match = Regex.findString(INTENSITY_REGEX, weatherPart);
            Intensity i = Intensity.getEnum(match);
            wc.setIntensity(i);
        }
        for (Descriptive des : Descriptive.values()) {
            if (Regex.findString(Pattern.compile("(" + des.getShortcut() + ")"), weatherPart) != null) {
                wc.setDescriptive(des);
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
     * @return the countries
     */
    protected Map<String, Country> getCountries() {
        return fCountries;
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
    public boolean generalParse(final AbstractWeatherContainer pContainer, final String pPart) {
        if (Regex.find(WIND_SHEAR_REGEX, pPart)) {
            WindShear windShear = parseWindShear(pPart);
            pContainer.setWindShear(windShear);
        } else if (Regex.find(WIND_REGEX, pPart)) {
            Wind wind = parseWind(pPart);
            pContainer.setWind(wind);
        } else if (Regex.find(WIND_EXTREME_REGEX, pPart)) {
            parseExtremeWind(pContainer.getWind(), pPart);
        } else if (Regex.find(MAIN_VISIBILITY_REGEX, pPart)) {
            String[] matches = Regex.pregMatch(MAIN_VISIBILITY_REGEX, pPart);
            if (pContainer.getVisibility() == null) {
                pContainer.setVisibility(new Visibility());
            }
            pContainer.getVisibility().setMainVisibility(Converter.convertVisibility(matches[1]));
        } else if (Regex.find(MAIN_VISIBILITY_SM_REGEX, pPart)) {
            String[] matches = Regex.pregMatch(MAIN_VISIBILITY_SM_REGEX, pPart);
            if (pContainer.getVisibility() == null) {
                pContainer.setVisibility(new Visibility());
            }
            pContainer.getVisibility().setMainVisibility(matches[0]);
        } else if (Regex.find(MIN_VISIBILITY_REGEX, pPart)) {
            parseMinimalVisibility(pContainer.getVisibility(), pPart);
        } else if (Regex.match(VERTICAL_VISIBILITY, pPart)) {
            String[] matches = Regex.pregMatch(VERTICAL_VISIBILITY, pPart);
            pContainer.setVerticalVisibility(100 * Integer.parseInt(matches[1]));
        } else if (CAVOK.equals(pPart)) {
            pContainer.setCavok(true);
            if (pContainer.getVisibility() == null) {
                pContainer.setVisibility(new Visibility());
            }
            pContainer.getVisibility().setMainVisibility(">10km");
        } else if (Regex.find(CLOUD_REGEX, pPart)) {
            Cloud c = parseCloud(pPart);
            return pContainer.addCloud(c);
        } else {
            WeatherCondition wc = parseWeatherCondition(pPart);
            return pContainer.addWeatherCondition(wc);
        }
        return true;
    }

    /***
     * Adds the remark part to the event.
     * @param pContainer the event to update
     * @param pParts the tokens of the event
     * @param index the RMK index in the event.
     */
    protected void parseRMK(final AbstractWeatherContainer pContainer, final String[] pParts, final int index) {
        String[] subArray = Arrays.copyOfRange(pParts, index + 1, pParts.length);
        pContainer.setRemark(Arrays.stream(subArray).collect(Collectors.joining(" ")));
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
}
