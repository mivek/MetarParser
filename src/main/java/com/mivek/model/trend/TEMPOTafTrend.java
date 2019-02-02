package com.mivek.model.trend;

import com.mivek.model.trend.validity.Validity;

import io.github.mivek.enums.WeatherChangeType;

/**
 * Class representing a Tempo change in a taf message.
 * @author mivek
 */
public final class TEMPOTafTrend extends AbstractTafTrend<Validity> {
    /**
     * Default constructor.
     */
    public TEMPOTafTrend() {
        super(WeatherChangeType.TEMPO);
    }

}
