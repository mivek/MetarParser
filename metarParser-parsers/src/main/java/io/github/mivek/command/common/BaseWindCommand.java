package io.github.mivek.command.common;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.Wind;
import io.github.mivek.utils.Converter;

import java.util.Objects;

/**
 * @author mivek
 */
public interface BaseWindCommand extends Command {

    /**
     * Sets the elements of the wind.
     *
     * @param wind         the wind element.
     * @param directionStr the direction of the wind in degrees.
     * @param speed        the speed of the wind
     * @param gust         the speed of the gust if any
     * @param unit         the unit.
     */
    default void setWindElements(final Wind wind, final String directionStr, final String speed, final String gust, final String unit) {
        String direction = Converter.degreesToDirection(directionStr);
        wind.setDirection(direction);
        if (!direction.equals(Messages.getInstance().getString("Converter.VRB"))) {
            wind.setDirectionDegrees(Integer.parseInt(directionStr));
        }
        if (!speed.contains("/")) {
            int windSpeed = handleWindSpeed(speed);
            wind.setSpeed(windSpeed);
        }
        if (gust != null && !gust.isEmpty() && !gust.contains("/")) {
            int gustSpeed = handleWindSpeed(gust);
            wind.setGust(gustSpeed);
        }
        wind.setUnit(Objects.requireNonNullElse(unit, "KT"));
    }

    /**
     * Handles wind speed parsing, including P99 format.
     *
     * @param speedStr the speed string
     * @return the parsed speed
     */
    private int handleWindSpeed(final String speedStr) {
        if (speedStr.startsWith("P")) {
            return Integer.parseInt(speedStr.substring(1)) + 1;
        }
        return Integer.parseInt(speedStr);
    }
}
