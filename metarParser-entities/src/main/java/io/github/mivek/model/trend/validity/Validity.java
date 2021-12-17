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
    private int endDay;
    /**
     * Ending hour of the taf's validity.
     */
    private int endHour;

    /**
     * @return the endDay
     */
    public int getEndDay() {
        return endDay;
    }

    /**
     * @param endDay the endDay to set
     */
    public void setEndDay(final int endDay) {
        this.endDay = endDay;
    }

    /**
     * @return the endHour
     */
    public int getEndHour() {
        return endHour;
    }

    /**
     * @param endHour the endHour to set
     */
    public void setEndHour(final int endHour) {
        this.endHour = endHour;
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
