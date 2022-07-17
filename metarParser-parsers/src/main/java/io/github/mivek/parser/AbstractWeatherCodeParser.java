package io.github.mivek.parser;

import io.github.mivek.command.AirportSupplier;
import io.github.mivek.command.common.CommonCommandSupplier;
import io.github.mivek.enums.Flag;
import io.github.mivek.model.AbstractWeatherCode;
import java.time.LocalTime;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Abstract class for parser.
 *
 * @param <T> a concrete subclass of {@link AbstractWeatherCode}.
 * @author mivek
 * Abstract class for Parser.
 */
public abstract class AbstractWeatherCodeParser<T extends AbstractWeatherCode> extends AbstractWeatherContainerParser<T, String> {

    /** Tempo shortcut constant. */
    protected static final String TEMPO = "TEMPO";
    /** BECMG shortcut constant. */
    protected static final String BECMG = "BECMG";
    /** Pattern for RMK. */
    protected static final String RMK = "RMK";
    /** Pattern regex to tokenize the code. */
    private static final Pattern TOKENIZE_REGEX = Pattern.compile("\\s((?=\\d/\\dSM)(?<!\\s\\d\\s)|(?!\\d/\\dSM))|(=\\z)");
    /** The airport supplier. */
    private final AirportSupplier airportSupplier;

    /**
     * Dependency injection constructor.
     *
     * @param commonCommandSupplier the common command supplier
     * @param remarkParser          the remark parser.
     * @param airportSupplier       the airport supplier.
     */
    AbstractWeatherCodeParser(final CommonCommandSupplier commonCommandSupplier, final RemarkParser remarkParser, final AirportSupplier airportSupplier) {
        super(commonCommandSupplier, remarkParser);
        this.airportSupplier = Objects.requireNonNull(airportSupplier);
    }

    /**
     * Parses the string containing the delivery time.
     *
     * @param weatherCode The weather code.
     * @param time        the string to parse.
     */
    void parseDeliveryTime(final AbstractWeatherCode weatherCode, final String time) {
        weatherCode.setDay(Integer.parseInt(time.substring(0, 2)));
        int hours = Integer.parseInt(time.substring(2, 4));
        int minutes = Integer.parseInt(time.substring(4, 6));
        LocalTime t = LocalTime.of(hours, minutes);
        weatherCode.setTime(t);
    }

    /**
     * Adds flags to the WeatherCode.
     * @param weatherCode The weathercode to update
     * @param flag The string to test.
     * @return True if the flag was added.
     */
    boolean parseFlags(final AbstractWeatherCode weatherCode, final String flag) {
        try {
            weatherCode.getFlags().add(Flag.valueOf(flag));
            return true;
        } catch (IllegalArgumentException ignored) {
            return false;
        }
    }

    /**
     * Splits a string between spaces except if the space is between two digits with
     * SM.
     *
     * @param code the string to parse
     * @return an array of tokens
     */
    String[] tokenize(final String code) {
        return TOKENIZE_REGEX.split(code);
    }

    /**
     * @return the Airport supplier.
     */
    AirportSupplier getAirportSupplier() {
        return airportSupplier;
    }
}
