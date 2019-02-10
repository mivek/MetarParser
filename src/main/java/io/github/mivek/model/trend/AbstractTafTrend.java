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
    /**
     * The validity of the change.
     */
    private T fValidity;
    /**
     * The probability of the change.
     */
    private Integer fProbability;
    /**
     * The maximum temperature.
     */
    private TemperatureDated fMaxTemperature;
    /**
     * The minimum temperature.
     */
    private TemperatureDated fMinTemperature;

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
        return fValidity;
    }

    /**
     * @param pValidity the validity to set
     */
    public void setValidity(final T pValidity) {
        fValidity = pValidity;
    }


    /**
     * @return the probability
     */
    public Integer getProbability() {
        return fProbability;
    }

    /**
     * @param pProbability the probability to set
     */
    public void setProbability(final Integer pProbability) {
        fProbability = pProbability;
    }

    /**
     * @return the maxTemperature
     */
    public TemperatureDated getMaxTemperature() {
        return fMaxTemperature;
    }

    /**
     * @param pMaxTemperature the maxTemperature to set
     */
    public void setMaxTemperature(final TemperatureDated pMaxTemperature) {
        fMaxTemperature = pMaxTemperature;
    }

    /**
     * @return the minTemperature
     */
    public TemperatureDated getMinTemperature() {
        return fMinTemperature;
    }

    /**
     * @param pMinTemperature the minTemperature to set
     */
    public void setMinTemperature(final TemperatureDated pMinTemperature) {
        fMinTemperature = pMinTemperature;
    }

}
