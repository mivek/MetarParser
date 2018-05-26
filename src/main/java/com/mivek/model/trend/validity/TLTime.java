package com.mivek.model.trend.validity;

import com.mivek.enums.TimeIndicator;

/**
 * This class represents the TL part of the trend part of a metar.
 * @author mivek
 */
public final class TLTime extends AbstractMetarTrendTime {

    /**
     * Constructor.
     */
    public TLTime() {
        super(TimeIndicator.TL);
    }

}
