package io.github.mivek.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing a temperature with its date.
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
     * @param pTemperature the temperature to set
     */
    public void setTemperature(final Integer pTemperature) {
        temperature = pTemperature;
    }

    /**
     * @return the day
     */
    public Integer getDay() {
        return day;
    }

    /**
     * @param pDay the day to set
     */
    public void setDay(final Integer pDay) {
        day = pDay;
    }

    /**
     * @return the hour
     */
    public Integer getHour() {
        return hour;
    }

    /**
     * @param pHour the hour to set
     */
    public void setHour(final Integer pHour) {
        hour = pHour;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                append("temperature (°C)", temperature).
                append("day of the month", day).
                append("hour of the day", hour).
                toString();
    }
}
