/**
 * 
 */
package com.mivek.model;

import java.util.ArrayList;
import java.util.List;

import com.mivek.enums.WeatherChangeType;
import com.mivek.parser.TAFValidity;

/**
 * @author mivek
 *
 */
public abstract class AbstractWeatherChange {
	protected WeatherChangeType fType;
	private Visibility fVisibility;
	private List<Cloud> fClouds;
	private List<WeatherCondition> fWeatherConditions;
	private TAFValidity fValidity;

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
	public TAFValidity getValidity() {
		return fValidity;
	}

	/**
	 * @param pValidity
	 *            the validity to set
	 */
	public void setValidity(TAFValidity pValidity) {
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

}
