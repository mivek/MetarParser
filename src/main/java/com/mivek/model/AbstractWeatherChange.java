/**
 * 
 */
package com.mivek.model;

import java.util.ArrayList;
import java.util.List;

import com.mivek.enums.WeatherChangeType;

/**
 * @author mivek
 *
 */
public abstract class AbstractWeatherChange<T extends AbstractValidity> {
	protected WeatherChangeType fType;
	private Wind fWind;
	private Visibility fVisibility;
	private List<Cloud> fClouds;
	private ArrayList<WeatherCondition> fWeatherConditions;
	private T fValidity;
	private Integer probability;

	public AbstractWeatherChange() {
		fClouds = new ArrayList<>();
		fWeatherConditions = new ArrayList<>();
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
	public void setVisibility(Visibility pVisibility) {
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
	public void setValidity(T pValidity) {
		fValidity = pValidity;
	}

	/**
	 * @return the clouds
	 */
	public List<Cloud> getClouds() {
		return fClouds;
	}

	public void addCloud(Cloud pCloud) {
		fClouds.add(pCloud);
	}

	/**
	 * @return the weatherConditions
	 */
	public List<WeatherCondition> getWeatherConditions() {
		return fWeatherConditions;
	}

	public void addWeatherCondition(WeatherCondition pWeatherCondition) {
		fWeatherConditions.add(pWeatherCondition);
	}

	public WeatherChangeType geType() {
		return fType;
	}

	/**
	 * @return the probability
	 */
	public Integer getProbability() {
		return probability;
	}

	/**
	 * @param pProbability
	 *            the probability to set
	 */
	public void setProbability(final Integer pProbability) {
		probability = pProbability;
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
	public void setWind(Wind pWind) {
		fWind = pWind;
	}

}
