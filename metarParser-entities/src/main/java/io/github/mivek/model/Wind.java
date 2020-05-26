package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Wind class.
 *
 * @author mivek
 */
public class Wind {
    /** Speed of the wind. */
    private int speed;
    /** Direction of the wind. */
    private String direction;
    /** Direction of the wind. */
    private Integer directionDegrees;
    /** The speed of the gust. */
    private int gust;
    /** The minimal variation of the wind. */
    private int extreme1;
    /** The maximum variation of the wind. */
    private int extreme2;
    /** The unit of the speed. */
    private String unit;

    /**
     * Getter of the speed.
     *
     * @return the speed.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Setter of the speed.
     *
     * @param pSpeed the speed to set.
     */
    public void setSpeed(final int pSpeed) {
        speed = pSpeed;
    }

    /**
     * Getter of the direction.
     *
     * @return The Direction of the wind.
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Setter of the direction of the wind.
     *
     * @param pDirection the direction to set.
     */
    public void setDirection(final String pDirection) {
        direction = pDirection;
    }

    /**
     * Getter of the gust.
     *
     * @return the gust.
     */
    public int getGust() {
        return gust;
    }

    /**
     * Setter of the gust.
     *
     * @param pGust the gust to set.
     */
    public void setGust(final int pGust) {
        gust = pGust;
    }

    /**
     * Getter of the minimal variation of the wind.
     *
     * @return the minimal variation of the wind.
     */
    public int getExtreme1() {
        return extreme1;
    }

    /**
     * Setter of extreme1.
     *
     * @param pExtreme1 the minimal wind variation to set.
     */
    public void setExtreme1(final int pExtreme1) {
        extreme1 = pExtreme1;
    }

    /**
     * Getter of the maximal wind variation.
     *
     * @return the wind variation.
     */
    public int getExtreme2() {
        return extreme2;
    }

    /**
     * Setter.
     *
     * @param pExtreme2 the wind variation to set.
     */
    public void setExtreme2(final int pExtreme2) {
        extreme2 = pExtreme2;
    }

    /**
     * Getter of the unit.
     *
     * @return the unit.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Setter.
     *
     * @param pUnit The unit to set.
     */
    public void setUnit(final String pUnit) {
        unit = pUnit;
    }

    /**
     * @return the directionDegrees.
     */
    public Integer getDirectionDegrees() {
        return directionDegrees;
    }

    /**
     * @param pDirectionDegrees the directionDegrees to set.
     */
    public void setDirectionDegrees(final Integer pDirectionDegrees) {
        directionDegrees = pDirectionDegrees;
    }

    /**
     * @return a description of the wind component.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append(Messages.getInstance().getString("ToString.wind.speed"), speed).
                append(Messages.getInstance().getString("ToString.wind.unit"), unit).
                append(Messages.getInstance().getString("ToString.wind.direction"), direction).
                append(Messages.getInstance().getString("ToString.wind.direction.degrees"), directionDegrees).
                append(Messages.getInstance().getString("ToString.wind.gusts"), gust).
                append(Messages.getInstance().getString("ToString.wind.min.variation"), extreme1).
                append(Messages.getInstance().getString("ToString.wind.max.variation"), extreme2).
                toString();
    }
}
