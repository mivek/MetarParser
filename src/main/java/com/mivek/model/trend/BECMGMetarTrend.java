package com.mivek.model.trend;

import com.mivek.enums.WeatherChangeType;

/**
 * BECMG class for metar.
 * @author mivek
 */
public final class BECMGMetarTrend extends AbstractMetarTrend {

    /**
     * Constructor.
     */
    public BECMGMetarTrend() {
        super(WeatherChangeType.BECMG);
    }

}
