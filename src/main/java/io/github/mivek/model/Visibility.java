package io.github.mivek.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Visisbility class.
 * @author mivek
 */
public class Visibility {
    /**
     * mainVisibility of the metar.
     */
    private String fMainVisibility;
    /**
     * minimal visibility of the metar.
     */
    private int fMinVisibility;
    /**
     * Direction of the minimal visibility.
     */
    private String fMinDirection;

    /**
     * Getter of the mainVisibility.
     * @return the mainvisibility.
     */
    public String getMainVisibility() {
        return fMainVisibility;
    }

    /**
     * Setter of the main visibility.
     * @param pMainVisibility the main visibility to set.
     */
    public void setMainVisibility(final String pMainVisibility) {
        fMainVisibility = pMainVisibility;
    }

    /**
     * Getter of the minimal visibility.
     * @return the minimal visibility.
     */
    public int getMinVisibility() {
        return fMinVisibility;
    }

    /**
     * Setter of the minimal visibility.
     * @param pMinVisibility the minimal visibility to set.
     */
    public void setMinVisibility(final int pMinVisibility) {
        fMinVisibility = pMinVisibility;
    }

    /**
     * Getter of direction.
     * @return the direction.
     */
    public String getMinDirection() {
        return fMinDirection;
    }

    /**
     * Setter of the minimal direction.
     * @param pMinDirection the minimal direction to set.
     */
    public void setMinDirection(final String pMinDirection) {
        fMinDirection = pMinDirection;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                append("main visibility", fMainVisibility).
                append("minimum visibility", fMinVisibility).
                append("minimum visibility direction", fMinDirection).toString();
    }
}
