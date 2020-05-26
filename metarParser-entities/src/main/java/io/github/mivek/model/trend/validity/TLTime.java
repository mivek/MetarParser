package io.github.mivek.model.trend.validity;

import io.github.mivek.enums.TimeIndicator;

/**
 * This class represents the TL part of the trend part of a metar.
 *
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
