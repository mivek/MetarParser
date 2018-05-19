package com.mivek.model.trend;

import com.mivek.enums.WeatherChangeType;
import com.mivek.model.BeginningValidity;

/**
 * Class representing From Changes.
 * @author mivek
 */
public class FMChange extends AbstractTafTrend<BeginningValidity> {

    /**
     * Constructor.
     */
    public FMChange() {
        super(WeatherChangeType.FM);
    }

}
