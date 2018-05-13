package com.mivek.model;

import com.mivek.enums.WeatherChangeType;

/**
 * Class representing a weather change.
 * @param <T> a concrete subclass of {@link AbstractValidity}
 * @author mivek
 */
public abstract class AbstractWeatherChange<T extends AbstractValidity> extends AbstractWeatherContainer {
	/**
	 * the type of change.
	 */
	private WeatherChangeType fType;
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
	protected AbstractWeatherChange(final WeatherChangeType pType) {
		super();
		fType = pType;
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
	 * @return the type of change.
	 */
	public WeatherChangeType geType() {
		return fType;
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
