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
    private int temperature;
    /** The day. */
    private int day;
    /** The hour. */
    private int hour;

    /**
     * @return the temperature
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(final int temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(final int day) {
        this.day = day;
    }

    /**
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(final int hour) {
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
