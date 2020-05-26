package io.github.mivek.model.trend.validity;

import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing the validity of a TAF message.
 *
 * @author mivek
 */
public final class Validity extends AbstractValidity {

    /**
     * Ending day of the taf's validity.
     */
    private Integer endDay;
    /**
     * Ending hour of the taf's validity.
     */
    private Integer endHour;

    /**
     * @return the endDay
     */
    public Integer getEndDay() {
        return endDay;
    }

    /**
     * @param pEndDay the endDay to set
     */
    public void setEndDay(final Integer pEndDay) {
        endDay = pEndDay;
    }

    /**
     * @return the endHour
     */
    public Integer getEndHour() {
        return endHour;
    }

    /**
     * @param pEndHour the endHour to set
     */
    public void setEndHour(final Integer pEndHour) {
        endHour = pEndHour;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                appendSuper(super.toString()).
                append(Messages.getInstance().getString("ToString.end.day.month"), endDay).
                append(Messages.getInstance().getString("ToString.end.hour.day"), endHour).
                toString();
    }
}
