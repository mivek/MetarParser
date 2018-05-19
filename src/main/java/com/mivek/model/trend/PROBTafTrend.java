package com.mivek.model.trend;

import com.mivek.enums.WeatherChangeType;
import com.mivek.model.Validity;

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
