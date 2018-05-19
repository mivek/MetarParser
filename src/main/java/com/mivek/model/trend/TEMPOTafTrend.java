package com.mivek.model.trend;

import com.mivek.enums.WeatherChangeType;
import com.mivek.model.trend.validity.Validity;

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
