package io.github.mivek.model.trend.validity;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing a validity with start day, start hour and start minutes.
 *
 * @author mivek
 */
public class BeginningValidity extends AbstractValidity {
    /** the minutes. */
    private int startMinutes;

    /**
     * @return the startMinutes
     */
    public int getStartMinutes() {
        return startMinutes;
    }

    /**
     * @param startMinutes the startMinutes to set
     */
    public void setStartMinutes(final int startMinutes) {
        this.startMinutes = startMinutes;
    }

    @Override
    public final String toString() {
        return toString(Locale.getDefault());
    }

    /**
     * Returns a locale-aware string representation.
     * @param locale the locale to use for labels.
     * @return the string representation.
     */
    @Override
    public String toString(final Locale locale) {
        return new ToStringBuilder(this).
                appendSuper(super.toString(locale)).
                append(Messages.getInstance().getString(locale, "ToString.start.minute"), startMinutes).
                toString();
    }
}
