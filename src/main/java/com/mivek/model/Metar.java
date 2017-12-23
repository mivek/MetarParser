package com.mivek.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Metar class.
 *
 * @author mivek
 *
 */
public class Metar {
	/**
	 * Integer for the day of the metar.
	 */
	private Integer day;
	/**
	 * Time of the metar.
	 */
	private Time time;
	/**
	 * Temperature.
	 */
	private Integer temperature;
	/**
	 * Dew point.
	 */
	private Integer dewPoint;
	/**
	 * Altimeter.
	 */
	private Integer altimeter;
	/**
	 * Vertical visibility.
	 */
	private Integer verticalVisibility;
	/**
	 * Original message of the metar.
	 */
	private String message;
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
	 * Nosig value.
	 */
	private boolean nosig;
	/**
	 * Auto Value.
	 */
	private boolean auto;
	/**
	 * List of runways information.
	 */
	private List<RunwayInfo> runways;
	/**
	 * List of weather conditions.
	 */
	private List<WeatherCondition> weatherConditions;
	/**
	 * List of clouds.
	 */
	private List<Cloud> clouds;

	/**
	 * Constructor.
	 */
	public Metar() {
		runways = new ArrayList<>();
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
	public Time getTime() {
		return time;
	}

	/**
	 * @param pTime
	 *            the time to set
	 */
	public void setTime(final Time pTime) {
		this.time = pTime;
	}

	/**
	 * @return the temperature
	 */
	public Integer getTemperature() {
		return temperature;
	}

	/**
	 * @param pTemperature
	 *            the temperature to set
	 */
	public void setTemperature(final Integer pTemperature) {
		this.temperature = pTemperature;
	}

	/**
	 * @return the dewPoint
	 */
	public Integer getDewPoint() {
		return dewPoint;
	}

	/**
	 * @param pDewPoint
	 *            the dewPoint to set
	 */
	public void setDewPoint(final Integer pDewPoint) {
		this.dewPoint = pDewPoint;
	}

	/**
	 * @return the altimeter
	 */
	public Integer getAltimeter() {
		return altimeter;
	}

	/**
	 * @param pAltimeter
	 *            the altimeter to set
	 */
	public void setAltimeter(final Integer pAltimeter) {
		this.altimeter = pAltimeter;
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
	 * @return the runways
	 */
	public List<RunwayInfo> getRunways() {
		return runways;
	}

	/**
	 * Adds a runway to the list.
	 *
	 * @param ri
	 *            the runway to add.
	 */
	public void addRunwayInfo(final RunwayInfo ri) {
		this.runways.add(ri);
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
	 * @return the nosig
	 */
	public boolean isNosig() {
		return nosig;
	}

	/**
	 * @param pNosig
	 *            the nosig to set
	 */
	public void setNosig(final boolean pNosig) {
		this.nosig = pNosig;
	}

	/**
	 * @return the auto
	 */
	public boolean isAuto() {
		return auto;
	}

	/**
	 * @param pAuto
	 *            the auto to set
	 */
	public void setAuto(final boolean pAuto) {
		this.auto = pAuto;
	}

}
