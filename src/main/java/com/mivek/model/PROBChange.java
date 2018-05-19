package com.mivek.model;

import com.mivek.enums.WeatherChangeType;
import com.mivek.model.trend.AbstractWeatherChange;

/**
 * @author mivek
 */
public class PROBChange extends AbstractWeatherChange<Validity> {

    /**
     * Constructor.
     */
    public PROBChange() {
        super(WeatherChangeType.PROB);
    }

}
