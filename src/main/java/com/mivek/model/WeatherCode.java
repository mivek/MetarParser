package com.mivek.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mivek
 *
 */
public abstract class WeatherCode {

	/**
	 * Integer for the day of the metar.
	 */
	private Integer day;
	/**
	 * Time of the metar.
	 */
	private LocalTime time;
	/**
	 * Airport of the metar.
	 */
	private Airport airport;
	/**
	 * Visibility.
	 */
	private Visibility visibility;
	/**
	 * Wind.
	 */
	private Wind wind;
	/**
	 * List of weather conditions.
	 */
	private List<WeatherCondition> weatherConditions;
	/**
	 * Vertical visibility.
	 */
	private Integer verticalVisibility;
	/**
	 * Original message of the metar.
	 */
	private String message;
	/**
	 * List of clouds.
	 */
	private List<Cloud> clouds;

	/**
	 * Default contructor.
	 */
	public WeatherCode() {
		weatherConditions = new ArrayList<>();
		clouds = new ArrayList<>();
	}

	/**
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}

	/**
	 * @param pDay
	 *            the day to set
	 */
	public void setDay(final Integer pDay) {
		this.day = pDay;
	}

	/**
	 * @return the time
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * @param pTime
	 *            the time to set
	 */
	public void setTime(final LocalTime pTime) {
		this.time = pTime;
	}

	/**
	 * @return the airport
	 */
	public Airport getAirport() {
		return airport;
	}

	/**
	 * @param pAirport
	 *            the airport to set
	 */
	public void setAirport(final Airport pAirport) {
		this.airport = pAirport;
	}

	/**
	 * @return the visibility
	 */
	public Visibility getVisibility() {
		return visibility;
	}

	/**
	 * @param pVisibility
	 *            the visibility to set
	 */
	public void setVisibility(final Visibility pVisibility) {
		this.visibility = pVisibility;
	}

	/**
	 * @return the weatherConditions
	 */
	public List<WeatherCondition> getWeatherConditions() {
		return weatherConditions;
	}

	/**
	 * Adds a weatherCondition to the list.
	 *
	 * @param wc
	 *            the WeatherCondition to add.
	 */
	public void addWeatherCondition(final WeatherCondition wc) {
		weatherConditions.add(wc);
	}

	/**
	 * @return the wind
	 */
	public Wind getWind() {
		return wind;
	}

	/**
	 * @param pWind
	 *            the wind to set
	 */
	public void setWind(final Wind pWind) {
		this.wind = pWind;
	}

	/**
	 * @return the verticalVisibility
	 */
	public Integer getVerticalVisibility() {
		return verticalVisibility;
	}

	/**
	 * @param pVerticalVisibility
	 *            the verticalVisibility to set
	 */
	public void setVerticalVisibility(final Integer pVerticalVisibility) {
		this.verticalVisibility = pVerticalVisibility;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param pMessage
	 *            the message to set
	 */
	public void setMessage(final String pMessage) {
		this.message = pMessage;
	}

	/**
	 * @return the clouds
	 */
	public List<Cloud> getClouds() {
		return clouds;
	}

	/**
	 * Adds a cloud to the list.
	 *
	 * @param cloud
	 *            The cloud to add.
	 */
	public void addCloud(final Cloud cloud) {
		this.clouds.add(cloud);
	}

}
