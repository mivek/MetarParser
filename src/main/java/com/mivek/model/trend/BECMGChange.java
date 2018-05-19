package com.mivek.model.trend;

import com.mivek.enums.WeatherChangeType;
import com.mivek.model.Validity;

/**
 * Class representing a BECMG change of a TAF.
 * @author mivek
 */
public class BECMGChange extends AbstractWeatherChange<Validity> {

    /**
     * Constructor.
     */
    public BECMGChange() {
        super(WeatherChangeType.BECMG);
    }

}
