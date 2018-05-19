package com.mivek.model;

import com.mivek.enums.WeatherChangeType;
import com.mivek.model.trend.AbstractTafTrend;

/**
 * @author mivek
 */
public class PROBChange extends AbstractTafTrend<Validity> {

    /**
     * Constructor.
     */
    public PROBChange() {
        super(WeatherChangeType.PROB);
    }

}
