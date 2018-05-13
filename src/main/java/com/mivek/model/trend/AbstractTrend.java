package com.mivek.model.trend;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.mivek.enums.WeatherChangeType;
import com.mivek.model.Cloud;
import com.mivek.model.Visibility;
import com.mivek.model.WeatherCondition;
import com.mivek.model.Wind;

/**
 * Abstract class for trends.
 * @author mivek
 */
public abstract class AbstractTrend {
	/**
	 * Type of trend.
	 */
	private WeatherChangeType fType;
	/**
	 * Visibility of the trend.
	 */
	private Visibility fVisibility;
	/**
	 * The wind of the trend.
	 */
	private Wind fWind;
	/**
	 * List of clouds of the trend.
	 */
	private List<Cloud> fClouds;
	/**
	 * List of weather conditions.
	 */
	private List<WeatherCondition> fWeatherConditions;
	/**
	 * Vertical visibility.
	 */
	private Integer fVerticalVisibility;

	/**
	 * Constructor.
	 * @param pType the type of the trend.
	 */
	protected AbstractTrend(final WeatherChangeType pType) {
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
	 * @return the wind
	 */
	public Wind getWind() {
		return fWind;
	}

	/**
	 * @return the clouds
	 */
	public List<Cloud> getClouds() {
		return fClouds;
	}

	/**
	 * @return the weatherConditions
	 */
	public List<WeatherCondition> getWeatherConditions() {
		return fWeatherConditions;
	}

	/**
	 * @param pVisibility the visibility to set
	 */
	public void setVisibility(final Visibility pVisibility) {
		fVisibility = Validate.notNull(pVisibility);
	}

	/**
	 * @param pWind the wind to set
	 */
	public void setWind(final Wind pWind) {
		fWind = Validate.notNull(pWind);
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
	public final Integer getVerticalVisibility() {
		return fVerticalVisibility;
	}

	/**
	 * @param pVerticalVisibility the verticalVisibility to set
	 */
	public final void setVerticalVisibility(final Integer pVerticalVisibility) {
		fVerticalVisibility = pVerticalVisibility;
	}

	/**
	 * @return the type
	 */
	public final WeatherChangeType getType() {
		return fType;
	}

}
