package io.github.mivek.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Wind class.
 * TODO Change unit to enumeration class.
 * @author mivek
 */
public class Wind {
    /** Speed of the wind. */
    private int fSpeed;
    /** Direction of the wind. */
    private String fDirection;
    /** Direction of the wind. */
    private Integer fDirectionDegrees;
    /** The speed of the gust. */
    private int fGust;
    /** The minimal variation of the wind. */
    private int fExtreme1;
    /** The maximum variation of the wind. */
    private int fExtreme2;
    /** The unit of the speed. */
    private String fUnit;

    /**
     * Getter of the speed.
     * @return the speed.
     */
    public int getSpeed() {
        return fSpeed;
    }

    /**
     * Setter of the speed.
     * @param pSpeed the speed to set.
     */
    public void setSpeed(final int pSpeed) {
        fSpeed = pSpeed;
    }

    /**
     * Getter of the direction.
     * @return The Direction of the wind.
     */
    public String getDirection() {
        return fDirection;
    }

    /**
     * Setter of the direction of the wind.
     * @param pDirection the direction to set.
     */
    public void setDirection(final String pDirection) {
        fDirection = pDirection;
    }

    /**
     * Getter of the gust.
     * @return the gust.
     */
    public int getGust() {
        return fGust;
    }

    /**
     * Setter of the gust.
     * @param pGust the gust to set.
     */
    public void setGust(final int pGust) {
        fGust = pGust;
    }

    /**
     * Getter of the minimal variation of the wind.
     * @return the minimal variation of the wind.
     */
    public int getExtreme1() {
        return fExtreme1;
    }

    /**
     * Setter of extreme1.
     * @param pExtreme1 the minimal wind variation to set.
     */
    public void setExtreme1(final int pExtreme1) {
        fExtreme1 = pExtreme1;
    }

    /**
     * Getter of the maximal wind variation.
     * @return the wind variation.
     */
    public int getExtreme2() {
        return fExtreme2;
    }

    /**
     * Setter.
     * @param pExtreme2 the wind variation to set.
     */
    public void setExtreme2(final int pExtreme2) {
        fExtreme2 = pExtreme2;
    }

    /**
     * Getter of the unit.
     * @return the unit.
     */
    public String getUnit() {
        return fUnit;
    }

    /**
     * Setter.
     * @param pUnit The unit to set.
     */
    public void setUnit(final String pUnit) {
        fUnit = pUnit;
    }

    /**
     * @return the directionDegrees.
     */
    public Integer getDirectionDegrees() {
        return fDirectionDegrees;
    }

    /**
     * @param pDirectionDegrees the directionDegrees to set.
     */
    public void setDirectionDegrees(final Integer pDirectionDegrees) {
        fDirectionDegrees = pDirectionDegrees;
    }

    /**
     * @return a description of the wind component.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("speed", fSpeed).
                append("unit", fUnit).
                append("direction", fDirection).
                append("direction (degrees)", fDirectionDegrees).
                append("gusts", fGust).
                append("minimal wind variation", fExtreme1).
                append("maximal wind variation", fExtreme2).
                toString();
    }
}
