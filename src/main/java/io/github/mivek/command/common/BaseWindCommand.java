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
     * @param pWind      the wind element.
     * @param pDirection the direction of the wind in degrees.
     * @param pSpeed     the speed of the wind
     * @param pGust      the speed of the gust if any
     * @param pUnit      the unit.
     */
    default void setWindElements(final Wind pWind, final String pDirection, final String pSpeed, final String pGust, final String pUnit) {
        String direction = Converter.degreesToDirection(pDirection);
        pWind.setDirection(direction);
        if (!direction.equals(Messages.getInstance().getString("Converter.VRB"))) {
            pWind.setDirectionDegrees(Integer.parseInt(pDirection));
        }
        pWind.setSpeed(Integer.parseInt(pSpeed));
        if (pGust != null) {
            pWind.setGust(Integer.parseInt(pGust));
        }
        pWind.setUnit(pUnit);
    }
}
