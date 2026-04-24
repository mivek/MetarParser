package io.github.mivek.model;

import io.github.mivek.enums.LengthUnit;
import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Visisbility class.
 *
 * @author mivek
 */
public class Visibility {
    /** mainVisibility of the metar. */
    private String mainVisibility;
    /** minimal visibility of the metar. */
    private Integer minVisibility;
    /** Direction of the minimal visibility. */
    private String minDirection;
    /** The unit of the visibility values. */
    private LengthUnit unit;

    /**
     * Getter of the mainVisibility.
     *
     * @return the mainvisibility.
     */
    public String getMainVisibility() {
        return mainVisibility;
    }

    /**
     * Setter of the main visibility.
     *
     * @param mainVisibility the main visibility to set.
     */
    public void setMainVisibility(final String mainVisibility) {
        this.mainVisibility = mainVisibility;
    }

    /**
     * Getter of the minimal visibility.
     *
     * @return the minimal visibility.
     */
    public Integer getMinVisibility() {
        return minVisibility;
    }

    /**
     * Setter of the minimal visibility.
     *
     * @param minVisibility the minimal visibility to set.
     */
    public void setMinVisibility(final int minVisibility) {
        this.minVisibility = minVisibility;
    }

    /**
     * Getter of the minimal direction.
     *
     * @return the direction.
     */
    public String getMinDirection() {
        return minDirection;
    }

    /**
     * Setter of the minimal direction.
     *
     * @param minDirection the minimal direction to set.
     */
    public void setMinDirection(final String minDirection) {
        this.minDirection = minDirection;
    }

    /**
     * Getter of the visibility unit.
     *
     * @return the visibility unit.
     */
    public LengthUnit getUnit() {
        return unit;
    }

    /**
     * Setter of the visibility unit.
     *
     * @param unit the visibility unit to set.
     */
    public void setUnit(final LengthUnit unit) {
        this.unit = unit;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                append(Messages.getInstance().getString("ToString.visibility.main"), mainVisibility).
                append(Messages.getInstance().getString("ToString.visibility.min"), minVisibility).
                append(Messages.getInstance().getString("ToString.visibility.min.direction"), minDirection).
                append(Messages.getInstance().getString("ToString.visibility.unit"), unit).toString();
    }
}
