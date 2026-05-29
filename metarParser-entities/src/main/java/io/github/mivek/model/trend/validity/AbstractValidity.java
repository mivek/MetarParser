package io.github.mivek.model.trend.validity;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;
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
    private int startDay;
    /**
     * Beginning hour of the taf's validity.
     */
    private int startHour;

    @Override
    public final int getStartDay() {
        return startDay;
    }

    @Override
    public final void setStartDay(final int startDay) {
        this.startDay = startDay;
    }

    @Override
    public final int getStartHour() {
        return startHour;
    }

    @Override
    public final void setStartHour(final int startHour) {
        this.startHour = startHour;
    }

    /**
     * @return a string describing the object using the JVM default locale.
     */
    @Override
    public String toString() {
        return toString(Locale.getDefault());
    }

    /**
     * Returns a locale-aware string representation.
     * @param locale the locale to use for labels.
     * @return the string representation.
     */
    public String toString(final Locale locale) {
        return new ToStringBuilder(this).
                append(Messages.getInstance().getString(locale, "ToString.start.day.month"), startDay).
                append(Messages.getInstance().getString(locale, "ToString.start.hour.day"), startHour).
                toString();
    }

}
