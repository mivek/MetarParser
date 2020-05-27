package io.github.mivek.model.trend.validity;

import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing a validity with start day, start hour and start minutes.
 *
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
     * @param startMinutes the startMinutes to set
     */
    public void setStartMinutes(final Integer startMinutes) {
        this.startMinutes = startMinutes;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                appendSuper(super.toString()).
                append(Messages.getInstance().getString("ToString.start.minute"), startMinutes).
                toString();
    }
}
