package io.github.mivek.command.common;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.Wind;
import io.github.mivek.utils.Converter;

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
        wind.setSpeed(Integer.parseInt(speed));
        if (gust != null) {
            wind.setGust(Integer.parseInt(gust));
        }
        if (unit == null) {
            wind.setUnit("KT");
        } else {
            wind.setUnit(unit);
        }
    }
}
