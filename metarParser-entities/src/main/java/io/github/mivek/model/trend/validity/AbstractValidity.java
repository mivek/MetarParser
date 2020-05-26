package io.github.mivek.model.trend.validity;

import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Abstract class for the validity of a TAF.
 *
 * @author mivek
 */
public abstract class AbstractValidity implements IValidity {
    /**
     * Beginning day of the taf's validity.
     */
    private Integer startDay;
    /**
     * Beginning hour of the taf's validity.
     */
    private Integer startHour;

    @Override
    public final Integer getStartDay() {
        return startDay;
    }

    @Override
    public final void setStartDay(final Integer startDay) {
        this.startDay = startDay;
    }

    @Override
    public final Integer getStartHour() {
        return startHour;
    }

    @Override
    public final void setStartHour(final Integer startHour) {
        this.startHour = startHour;
    }

    /**
     * @return a string describing the object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append(Messages.getInstance().getString("ToString.start.day.month"), startDay).
                append(Messages.getInstance().getString("ToString.start.hour.day"), startHour).
                toString();
    }

}
