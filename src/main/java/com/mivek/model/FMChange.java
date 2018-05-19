package com.mivek.model;

import com.mivek.enums.WeatherChangeType;
import com.mivek.model.trend.AbstractWeatherChange;

/**
 * Class representing From Changes.
 * @author mivek
 */
public class FMChange extends AbstractWeatherChange<BeginningValidity> {

    /**
     * Constructor.
     */
    public FMChange() {
        super(WeatherChangeType.FM);
    }

}
