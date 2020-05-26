package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;

/**
 * TEMPO class for metar trend.
 *
 * @author mivek
 */
public final class TEMPOMetarTrend extends AbstractMetarTrend {

    /**
     * Constructor.
     */
    public TEMPOMetarTrend() {
        super(WeatherChangeType.TEMPO);
    }

}
