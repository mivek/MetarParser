package io.github.mivek.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing a temperature with its date.
 * @author mivek
 */
public class TemperatureDated {
    /**
     * The temperature.
     */
    private Integer fTemperature;
    /**
     * The day.
     */
    private Integer fDay;
    /**
     * The hour.
     */
    private Integer fHour;

    /**
     * @return the temperature
     */
    public Integer getTemperature() {
        return fTemperature;
    }

    /**
     * @param pTemperature the temperature to set
     */
    public void setTemperature(final Integer pTemperature) {
        fTemperature = pTemperature;
    }

    /**
     * @return the day
     */
    public Integer getDay() {
        return fDay;
    }

    /**
     * @param pDay the day to set
     */
    public void setDay(final Integer pDay) {
        fDay = pDay;
    }

    /**
     * @return the hour
     */
    public Integer getHour() {
        return fHour;
    }

    /**
     * @param pHour the hour to set
     */
    public void setHour(final Integer pHour) {
        fHour = pHour;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                append("temperature (°C)", fTemperature).
                append("day of the month", fDay).
                append("hour of the day", fHour).
                toString();
    }
}
