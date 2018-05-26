package com.mivek.model.trend;

import com.mivek.enums.WeatherChangeType;
import com.mivek.model.trend.validity.BeginningValidity;

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
