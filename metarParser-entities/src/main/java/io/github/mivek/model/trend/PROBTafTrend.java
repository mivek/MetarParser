package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.trend.validity.Validity;

/**
 * @author mivek
 */
public class PROBTafTrend extends AbstractTafProbTrend<Validity> {

    /**
     * Constructor.
     */
    public PROBTafTrend() {
        super(WeatherChangeType.PROB);
    }

}
