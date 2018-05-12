package com.mivek.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mivek
 */
public abstract class AbstractWeatherCode {

	/**
	 * Integer for the day of the metar.
	 */
	private Integer fDay;
	/**
	 * Time of the metar.
	 */
	private LocalTime fTime;
	/**
	 * Airport of the metar.
	 */
	private Airport fAirport;
	/**
	 * Visibility.
	 */
	private Visibility fVisibility;
	/**
	 * Wind.
	 */
	private Wind fWind;
	/**
	 * List of weather conditions.
	 */
	private List<WeatherCondition> fWeatherConditions;
	/**
	 * Vertical visibility.
	 */
	private Integer fVerticalVisibility;
	/**
	 * Original message of the metar.
	 */
	private String fMessage;
	/**
	 * List of clouds.
	 */
	private List<Cloud> fClouds;

	/**
	 * Default contructor.
	 */
	public AbstractWeatherCode() {
		fWeatherConditions = new ArrayList<>();
		fClouds = new ArrayList<>();
	}

	/**
	 * @return the day
	 */
	public Integer getDay() {
		return fDay;
	}

	/**
	 * @param pDay the day to set
	 */
	public void setDay(final Integer pDay) {
		fDay = pDay;
	}

	/**
	 * @return the time
	 */
	public LocalTime getTime() {
		return fTime;
	}

	/**
	 * @param pTime the time to set
	 */
	public void setTime(final LocalTime pTime) {
		fTime = pTime;
	}

	/**
	 * @return the airport
	 */
	public Airport getAirport() {
		return fAirport;
	}

	/**
	 * @param pAirport the airport to set
	 */
	public void setAirport(final Airport pAirport) {
		fAirport = pAirport;
	}

	/**
	 * @return the visibility
	 */
	public Visibility getVisibility() {
		return fVisibility;
	}

	/**
	 * @param pVisibility the visibility to set
	 */
	public void setVisibility(final Visibility pVisibility) {
		fVisibility = pVisibility;
	}

	/**
	 * @return the weatherConditions
	 */
	public List<WeatherCondition> getWeatherConditions() {
		return fWeatherConditions;
	}

	/**
	 * Adds a weatherCondition to the list.
	 * @param pWeatherCondition the WeatherCondition to add.
	 */
	public void addWeatherCondition(final WeatherCondition pWeatherCondition) {
		fWeatherConditions.add(pWeatherCondition);
	}

	/**
	 * @return the wind
	 */
	public Wind getWind() {
		return fWind;
	}

	/**
	 * @param pWind the wind to set
	 */
	public void setWind(final Wind pWind) {
		fWind = pWind;
	}

	/**
	 * @return the verticalVisibility
	 */
	public Integer getVerticalVisibility() {
		return fVerticalVisibility;
	}

	/**
	 * @param pVerticalVisibility the verticalVisibility to set
	 */
	public void setVerticalVisibility(final Integer pVerticalVisibility) {
		fVerticalVisibility = pVerticalVisibility;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return fMessage;
	}

	/**
	 * @param pMessage the message to set
	 */
	public void setMessage(final String pMessage) {
		fMessage = pMessage;
	}

	/**
	 * @return the clouds
	 */
	public List<Cloud> getClouds() {
		return fClouds;
	}

	/**
	 * Adds a cloud to the list.
	 * @param pCloud The cloud to add.
	 */
	public void addCloud(final Cloud pCloud) {
		fClouds.add(pCloud);
	}

}
