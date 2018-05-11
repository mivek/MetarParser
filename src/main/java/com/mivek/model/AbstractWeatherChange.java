package com.mivek.model;

import java.util.ArrayList;
import java.util.List;

import com.mivek.enums.WeatherChangeType;

/**
 * Class representing a weather change.
 * @param <T> a concrete subclass of {@link AbstractValidity}
 * @author mivek
 *
 */
public abstract class AbstractWeatherChange<T extends AbstractValidity> {
	/**
	 * the type of change.
	 */
	private WeatherChangeType fType;
	/**
	 * the wind.
	 */
	private Wind fWind;
	/**
	 * The visibility.
	 */
	private Visibility fVisibility;
	/**
	 * The list of clouds.
	 */
	private List<Cloud> fClouds;
	/**
	 * The list of weatherCondition.
	 */
	private List<WeatherCondition> fWeatherConditions;
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
	 *
	 * @param pType
	 *            the type to set.
	 */
	protected AbstractWeatherChange(final WeatherChangeType pType) {
		fClouds = new ArrayList<>();
		fWeatherConditions = new ArrayList<>();
		fType = pType;
	}

	/**
	 * @return the visibility
	 */
	public Visibility getVisibility() {
		return fVisibility;
	}

	/**
	 * @param pVisibility
	 *            the visibility to set
	 */
	public void setVisibility(final Visibility pVisibility) {
		fVisibility = pVisibility;
	}

	/**
	 * @return the validity
	 */
	public T getValidity() {
		return fValidity;
	}

	/**
	 * @param pValidity
	 *            the validity to set
	 */
	public void setValidity(final T pValidity) {
		fValidity = pValidity;
	}

	/**
	 * @return the clouds
	 */
	public List<Cloud> getClouds() {
		return fClouds;
	}

	/**
	 * Adds a cloud to the list.
	 *
	 * @param pCloud
	 *            the cloud to add.
	 */
	public void addCloud(final Cloud pCloud) {
		fClouds.add(pCloud);
	}

	/**
	 * @return the weatherConditions
	 */
	public List<WeatherCondition> getWeatherConditions() {
		return fWeatherConditions;
	}

	/**
	 * Adds a weather condition to the list.
	 *
	 * @param pWeatherCondition
	 *            the weather condition to add.
	 */
	public void addWeatherCondition(final WeatherCondition pWeatherCondition) {
		fWeatherConditions.add(pWeatherCondition);
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
	 * @param pProbability
	 *            the probability to set
	 */
	public void setProbability(final Integer pProbability) {
		fProbability = pProbability;
	}

	/**
	 * @return the wind
	 */
	public Wind getWind() {
		return fWind;
	}

	/**
	 * @param pWind
	 *            the wind to set
	 */
	public void setWind(final Wind pWind) {
		fWind = pWind;
	}

	/**
	 * @return the maxTemperature
	 */
	public TemperatureDated getMaxTemperature() {
		return fMaxTemperature;
	}

	/**
	 * @param pMaxTemperature
	 *            the maxTemperature to set
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
	 * @param pMinTemperature
	 *            the minTemperature to set
	 */
	public void setMinTemperature(final TemperatureDated pMinTemperature) {
		fMinTemperature = pMinTemperature;
	}

}
