package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.trend.validity.Validity;

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
