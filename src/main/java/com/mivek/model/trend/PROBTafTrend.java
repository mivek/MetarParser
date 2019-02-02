package com.mivek.model.trend;

import com.mivek.model.trend.validity.Validity;

import io.github.mivek.enums.WeatherChangeType;

/**
 * @author mivek
 */
public class PROBTafTrend extends AbstractTafTrend<Validity> {

    /**
     * Constructor.
     */
    public PROBTafTrend() {
        super(WeatherChangeType.PROB);
    }

}
