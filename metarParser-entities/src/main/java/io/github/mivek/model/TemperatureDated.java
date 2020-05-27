package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing a temperature with its date.
 *
 * @author mivek
 */
public class TemperatureDated {
    /** The temperature. */
    private Integer temperature;
    /** The day. */
    private Integer day;
    /** The hour. */
    private Integer hour;

    /**
     * @return the temperature
     */
    public Integer getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(final Integer temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the day
     */
    public Integer getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(final Integer day) {
        this.day = day;
    }

    /**
     * @return the hour
     */
    public Integer getHour() {
        return hour;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(final Integer hour) {
        this.hour = hour;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                append(Messages.getInstance().getString("ToString.temperature"), temperature).
                append(Messages.getInstance().getString("ToString.day.month"), day).
                append(Messages.getInstance().getString("ToString.day.hour"), hour).
                toString();
    }
}
