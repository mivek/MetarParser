package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.trend.validity.AbstractMetarTrendTime;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for metar's trend.
 *
 * @author mivek
 */
public abstract class AbstractMetarTrend extends AbstractTrend {
    /**
     * List containing the times properties of the trend.
     */
    private final List<AbstractMetarTrendTime> times;

    /**
     * Constructor.
     *
     * @param type the WeatherChangeType to set.
     */
    protected AbstractMetarTrend(final WeatherChangeType type) {
        super(type);
        times = new ArrayList<>();
    }

    /**
     * @return the times
     */
    public List<AbstractMetarTrendTime> getTimes() {
        return times;
    }

    /**
     * Adds a AbstractMetarTrendTime to the list.
     *
     * @param time the element to add.
     */
    public void addTime(final AbstractMetarTrendTime time) {
        times.add(time);
    }

    /**
     * @return a description of the object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString()).append(times.toString()).toString();
    }

}
