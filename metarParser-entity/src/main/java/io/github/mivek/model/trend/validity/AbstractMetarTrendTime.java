package io.github.mivek.model.trend.validity;

import io.github.mivek.enums.TimeIndicator;
import org.apache.commons.lang3.Validate;

import java.time.LocalTime;

/**
 * Abstract class for the time trend in a metar.
 * @author mivek
 */
public abstract class AbstractMetarTrendTime {
    /**
     * Type of trend AT, FM or TL.
     */
    private TimeIndicator type;
    /**
     * Time of the change.
     */
    private LocalTime time;

    /**
     * Constructor.
     * @param pType the type of trend.
     */
    protected AbstractMetarTrendTime(final TimeIndicator pType) {
        this.type = pType;
    }

    /**
     * @return the time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * @param pTime the time to set
     */
    public void setTime(final LocalTime pTime) {
        time = Validate.notNull(pTime);
    }

    /**
     * @return the type
     */
    public TimeIndicator getType() {
        return type;
    }

    @Override
    public final String toString() {
        return type + " " + time;
    }
}
