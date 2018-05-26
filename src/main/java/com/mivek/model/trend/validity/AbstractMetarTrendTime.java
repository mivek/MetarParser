package com.mivek.model.trend.validity;

import java.time.LocalTime;

import org.apache.commons.lang3.Validate;

import com.mivek.enums.TimeIndicator;

/**
 * Abstract class for the time trend in a metar.
 * @author mivek
 */
public abstract class AbstractMetarTrendTime {
    /**
     * Type of trend AT, FM or TL.
     */
    private TimeIndicator fType;
    /**
     * Time of the change.
     */
    private LocalTime fTime;

    /**
     * Constructor.
     * @param pType the type of trend.
     */
    protected AbstractMetarTrendTime(final TimeIndicator pType) {
        fType = pType;
    }

    /**
     * @return the time
     */
    public LocalTime getTime() {
        return fTime;
    }

    /**
     * @param pTime the time to set
     */
    public void setTime(final LocalTime pTime) {
        fTime = Validate.notNull(pTime);
    }

    /**
     * @return the type
     */
    public TimeIndicator getType() {
        return fType;
    }

}
