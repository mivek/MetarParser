package io.github.mivek.model.trend.validity;

/**
 * Interface for validity objects.
 * @author mivek
 */
public interface IValidity {

    /**
     * @return the startDay
     */

    Integer getStartDay();

    /**
     * @param pStartDay the startDay to set
     */
    void setStartDay(Integer pStartDay);

    /**
     * @return the startHour
     */
    Integer getStartHour();

    /**
     * @param pStartHour the startHour to set
     */
    void setStartHour(Integer pStartHour);
}
