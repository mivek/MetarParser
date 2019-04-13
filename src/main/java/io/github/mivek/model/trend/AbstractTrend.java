package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.AbstractWeatherContainer;

/**
 * Abstract class for trends.
 * @author mivek
 */
public abstract class AbstractTrend extends AbstractWeatherContainer {
    /** Type of trend. */
    private WeatherChangeType type;

    /**
     * Constructor.
     * @param pType the type of the trend.
     */
    protected AbstractTrend(final WeatherChangeType pType) {
        type = pType;
    }

    /**
     * @return the type
     */
    public final WeatherChangeType getType() {
        return type;
    }
}
