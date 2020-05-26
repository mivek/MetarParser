package io.github.mivek.model.trend.validity;

import io.github.mivek.enums.TimeIndicator;

/**
 * This class represents the FM part of a trend in a metar.
 *
 * @author mivek
 */
public class FMTime extends AbstractMetarTrendTime {

    /**
     * Constructor.
     */
    public FMTime() {
        super(TimeIndicator.FM);
    }

}
