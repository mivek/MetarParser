package io.github.mivek.model.trend.validity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing a validity with start day, start hour and start minutes.
 * @author mivek
 */
public class BeginningValidity extends AbstractValidity {
    /**
     * the minutes.
     */
    private Integer fStartMinutes;

    /**
     * @return the startMinutes
     */
    public Integer getStartMinutes() {
        return fStartMinutes;
    }

    /**
     * @param pStartMinutes the startMinutes to set
     */
    public void setStartMinutes(final Integer pStartMinutes) {
        fStartMinutes = pStartMinutes;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                appendToString(super.toString()).
                append("starting minute", fStartMinutes).
                toString();
    }
}
