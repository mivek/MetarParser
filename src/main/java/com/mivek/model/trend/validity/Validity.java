package com.mivek.model.trend.validity;

/**
 * Class representing the validity of a TAF message.
 * @author mivek
 */
public final class Validity extends AbstractValidity {

    /**
     * Ending day of the taf's validity.
     */
    private Integer fEndDay;
    /**
     * Ending hour of the taf's validity.
     */
    private Integer fEndHour;

    /**
     * @return the endDay
     */
    public Integer getEndDay() {
        return fEndDay;
    }

    /**
     * @param pEndDay the endDay to set
     */
    public void setEndDay(final Integer pEndDay) {
        fEndDay = pEndDay;
    }

    /**
     * @return the endHour
     */
    public Integer getEndHour() {
        return fEndHour;
    }

    /**
     * @param pEndHour the endHour to set
     */
    public void setEndHour(final Integer pEndHour) {
        fEndHour = pEndHour;
    }
}
