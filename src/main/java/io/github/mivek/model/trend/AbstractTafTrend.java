package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.TemperatureDated;
import io.github.mivek.model.trend.validity.AbstractValidity;

/**
 * Class representing a weather change.
 * @param <T> a concrete subclass of {@link AbstractValidity}
 * @author mivek
 */
public abstract class AbstractTafTrend<T extends AbstractValidity> extends AbstractTrend {
    /** The validity of the change. */
    private T validity;
    /** The probability of the change. */
    private Integer probability;
    /** The maximum temperature. */
    private TemperatureDated maxTemperature;
    /** The minimum temperature. */
    private TemperatureDated minTemperature;

    /**
     * Constructor with parameter.
     * @param pType the type to set.
     */
    protected AbstractTafTrend(final WeatherChangeType pType) {
        super(pType);
    }

    /**
     * @return the validity
     */
    public T getValidity() {
        return validity;
    }

    /**
     * @param pValidity the validity to set
     */
    public void setValidity(final T pValidity) {
        validity = pValidity;
    }


    /**
     * @return the probability
     */
    public Integer getProbability() {
        return probability;
    }

    /**
     * @param pProbability the probability to set
     */
    public void setProbability(final Integer pProbability) {
        probability = pProbability;
    }

    /**
     * @return the maxTemperature
     */
    public TemperatureDated getMaxTemperature() {
        return maxTemperature;
    }

    /**
     * @param pMaxTemperature the maxTemperature to set
     */
    public void setMaxTemperature(final TemperatureDated pMaxTemperature) {
        maxTemperature = pMaxTemperature;
    }

    /**
     * @return the minTemperature
     */
    public TemperatureDated getMinTemperature() {
        return minTemperature;
    }

    /**
     * @param pMinTemperature the minTemperature to set
     */
    public void setMinTemperature(final TemperatureDated pMinTemperature) {
        minTemperature = pMinTemperature;
    }

}
