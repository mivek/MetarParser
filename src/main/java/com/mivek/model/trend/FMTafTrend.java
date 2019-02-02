package com.mivek.model.trend;

import com.mivek.model.trend.validity.BeginningValidity;

import io.github.mivek.enums.WeatherChangeType;

/**
 * Class representing From Changes.
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
