package com.mivek.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

/**
 * @author mivek
 *
 */
public abstract class AbstractWeatherContainer {
	/**
	 * The wind.
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
	 * The list of weatherConditions.
	 */
	private List<WeatherCondition> fWeatherConditions;
	/**
	 * the vertical Visibility.
	 */
	private int fVerticalVisibility;

	/**
	 * Constructor to initialize the lists.
	 */
	public AbstractWeatherContainer() {
		fClouds = new ArrayList<>();
		fWeatherConditions = new ArrayList<>();
	}

	/**
	 * @return the wind
	 */
	public final Wind getWind() {
		return fWind;
	}

	/**
	 * @param pWind
	 *            the wind to set
	 */
	public final void setWind(final Wind pWind) {
		fWind = pWind;
	}

	/**
	 * @return the visibility
	 */
	public final Visibility getVisibility() {
		return fVisibility;
	}

	/**
	 * @param pVisibility
	 *            the visibility to set
	 */
	public final void setVisibility(final Visibility pVisibility) {
		fVisibility = pVisibility;
	}

	/**
	 * @return the clouds
	 */
	public final List<Cloud> getClouds() {
		return fClouds;
	}

	/**
	 * @return the weatherConditions
	 */
	public final List<WeatherCondition> getWeatherConditions() {
		return fWeatherConditions;
	}

	/**
	 * Adds a cloud to the list.
	 * @param pCloud the cloud to add.
	 */
	public void addCloud(final Cloud pCloud) {
		fClouds.add(Validate.notNull(pCloud));
	}

	/**
	 * Adds a weather condition to the list.
	 * @param pWeatherCondition the weather condition to add.
	 */
	public void addWeatherCondition(final WeatherCondition pWeatherCondition) {
		fWeatherConditions.add(Validate.notNull(pWeatherCondition));
	}

	/**
	 * @return the verticalVisibility
	 */
	public int getVerticalVisibility() {
		return fVerticalVisibility;
	}

	/**
	 * @param pVerticalVisibility the verticalVisibility to set
	 */
	public void setVerticalVisibility(int pVerticalVisibility) {
		fVerticalVisibility = pVerticalVisibility;
	}
}
