package com.mivek.model;

import com.mivek.enums.WeatherChangeType;

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
