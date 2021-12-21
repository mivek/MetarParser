package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.trend.validity.Validity;

/**
 * Class representing a weather change.
 *
 * @author mivek
 */
public class TafTrend extends AbstractTafTrend<Validity> {

    /**
     * Constructor with parameter.
     *
     * @param type the type to set.
     */
    public TafTrend(final WeatherChangeType type) {
        super(type);
    }
}
