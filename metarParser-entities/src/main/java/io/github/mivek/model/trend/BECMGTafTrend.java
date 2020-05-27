package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.trend.validity.Validity;

/**
 * Class representing a BECMG change of a TAF.
 *
 * @author mivek
 */
public class BECMGTafTrend extends AbstractTafTrend<Validity> {

    /**
     * Constructor.
     */
    public BECMGTafTrend() {
        super(WeatherChangeType.BECMG);
    }

}
