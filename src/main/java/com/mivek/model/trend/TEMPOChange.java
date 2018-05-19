package com.mivek.model.trend;

import com.mivek.enums.WeatherChangeType;
import com.mivek.model.Validity;

/**
 * Class representing a Tempo change in a taf message.
 * @author mivek
 */
public final class TEMPOChange extends AbstractTafTrend<Validity> {
    /**
     * Default constructor.
     */
    public TEMPOChange() {
        super(WeatherChangeType.TEMPO);
    }

}
