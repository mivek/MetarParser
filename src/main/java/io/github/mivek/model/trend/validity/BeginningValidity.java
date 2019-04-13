package io.github.mivek.model.trend.validity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing a validity with start day, start hour and start minutes.
 * @author mivek
 */
public class BeginningValidity extends AbstractValidity {
    /** the minutes. */
    private Integer startMinutes;

    /**
     * @return the startMinutes
     */
    public Integer getStartMinutes() {
        return startMinutes;
    }

    /**
     * @param pStartMinutes the startMinutes to set
     */
    public void setStartMinutes(final Integer pStartMinutes) {
        startMinutes = pStartMinutes;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                appendSuper(super.toString()).
                append("starting minute", startMinutes).
                toString();
    }
}
