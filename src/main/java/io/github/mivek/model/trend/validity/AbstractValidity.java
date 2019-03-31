package io.github.mivek.model.trend.validity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Abstract class for the validity of a TAF.
 * @author mivek
 */
public abstract class AbstractValidity implements IValidity {
    /**
     * Beginning day of the taf's validity.
     */
    private Integer fStartDay;
    /**
     * Beginning hour of the taf's validity.
     */
    private Integer fStartHour;


    @Override
    public final Integer getStartDay() {
        return fStartDay;
    }

    @Override
    public final void setStartDay(final Integer pStartDay) {
        fStartDay = pStartDay;
    }

    @Override
    public final Integer getStartHour() {
        return fStartHour;
    }

    @Override
    public final void setStartHour(final Integer pStartHour) {
        fStartHour = pStartHour;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("starting day of the month", fStartDay).
                append("starting hour of the day", fStartHour).
                toString();
    }

}
