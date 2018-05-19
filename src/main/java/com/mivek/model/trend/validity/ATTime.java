package com.mivek.model.trend.validity;

import com.mivek.enums.TimeIndicator;

/**
 * This class represents the AT part of the trend in a metar.
 * @author mivek
 */
public class ATTime extends AbstractMetarTrendTime {

    /**
     * Constructor.
     */
    public ATTime() {
        super(TimeIndicator.AT);
    }

}
