package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Visisbility class.
 * @author mivek
 */
public class Visibility {
    /** mainVisibility of the metar. */
    private String mainVisibility;
    /** minimal visibility of the metar. */
    private int minVisibility;
    /** Direction of the minimal visibility. */
    private String minDirection;

    /**
     * Getter of the mainVisibility.
     * @return the mainvisibility.
     */
    public String getMainVisibility() {
        return mainVisibility;
    }

    /**
     * Setter of the main visibility.
     * @param pMainVisibility the main visibility to set.
     */
    public void setMainVisibility(final String pMainVisibility) {
        mainVisibility = pMainVisibility;
    }

    /**
     * Getter of the minimal visibility.
     * @return the minimal visibility.
     */
    public int getMinVisibility() {
        return minVisibility;
    }

    /**
     * Setter of the minimal visibility.
     * @param pMinVisibility the minimal visibility to set.
     */
    public void setMinVisibility(final int pMinVisibility) {
        minVisibility = pMinVisibility;
    }

    /**
     * Getter of direction.
     * @return the direction.
     */
    public String getMinDirection() {
        return minDirection;
    }

    /**
     * Setter of the minimal direction.
     * @param pMinDirection the minimal direction to set.
     */
    public void setMinDirection(final String pMinDirection) {
        minDirection = pMinDirection;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                append(Messages.getInstance().getString("ToString.visibility.main"), mainVisibility).
                append(Messages.getInstance().getString("ToString.visibility.min"), minVisibility).
                append(Messages.getInstance().getString("ToString.visibility.min.direction"), minDirection).toString();
    }
}
