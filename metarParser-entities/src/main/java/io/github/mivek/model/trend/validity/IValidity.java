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

    int getStartDay();

    /**
     * @param startDay the startDay to set
     */
    void setStartDay(int startDay);

    /**
     * @return the startHour
     */
    int getStartHour();

    /**
     * @param startHour the startHour to set
     */
    void setStartHour(int startHour);
}
