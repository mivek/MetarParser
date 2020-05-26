package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.trend.validity.BeginningValidity;

/**
 * Class representing From Changes.
 *
 * @author mivek
 */
public class FMTafTrend extends AbstractTafTrend<BeginningValidity> {

    /**
     * Constructor.
     */
    public FMTafTrend() {
        super(WeatherChangeType.FM);
    }

}
