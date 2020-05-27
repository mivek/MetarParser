package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.AbstractWeatherContainer;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Abstract class for trends.
 *
 * @author mivek
 */
public abstract class AbstractTrend extends AbstractWeatherContainer {
    /** Type of trend. */
    private final WeatherChangeType type;

    /**
     * Constructor.
     *
     * @param type the type of the trend.
     */
    protected AbstractTrend(final WeatherChangeType type) {
        this.type = type;
    }

    /**
     * @return the type
     */
    public final WeatherChangeType getType() {
        return type;
    }

    /**
     * @return a description of the object
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).appendToString(type.toString()).appendSuper(super.toString()).toString();
    }
}
