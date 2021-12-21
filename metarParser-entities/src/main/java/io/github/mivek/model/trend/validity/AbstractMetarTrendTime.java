package io.github.mivek.model.trend.validity;

import io.github.mivek.enums.TimeIndicator;

import java.time.LocalTime;
import java.util.Objects;

/**
 * Abstract class for the time trend in a metar.
 *
 * @author mivek
 */
public abstract class AbstractMetarTrendTime {
    /**
     * Type of trend AT, FM or TL.
     */
    private final TimeIndicator type;
    /**
     * Time of the change.
     */
    private LocalTime time;

    /**
     * Constructor.
     *
     * @param type the type of trend.
     */
    protected AbstractMetarTrendTime(final TimeIndicator type) {
        this.type = type;
    }

    /**
     * @return the time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(final LocalTime time) {
        this.time = Objects.requireNonNull(time);
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
