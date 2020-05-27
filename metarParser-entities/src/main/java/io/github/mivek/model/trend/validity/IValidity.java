package io.github.mivek.model.trend.validity;

/**
 * Interface for validity objects.
 *
 * @author mivek
 */
public interface IValidity {

    /**
     * @return the startDay
     */

    Integer getStartDay();

    /**
     * @param startDay the startDay to set
     */
    void setStartDay(Integer startDay);

    /**
     * @return the startHour
     */
    Integer getStartHour();

    /**
     * @param startHour the startHour to set
     */
    void setStartHour(Integer startHour);
}
