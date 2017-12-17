package com.mivek.model;

import java.util.ArrayList;
import java.util.List;

public class Metar {
	private Integer day;
	private Time time;
	private Integer temperature;
	private Integer dewPoint;
	private Integer altimeter;
	private Integer verticalVisibility;
	private String message;
	private Airport airport;
	private Visibility visibility;
	private Wind wind;
	private boolean nosig;
	private boolean auto;
	private List<RunwayInfo> runways;
	private List<WeatherCondition> weatherConditions;
	private List<Cloud> clouds;
	
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
	 * @param day the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}

	/**
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Time time) {
		this.time = time;
	}

	/**
	 * @return the temperature
	 */
	public Integer getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	/**
	 * @return the dewPoint
	 */
	public Integer getDewPoint() {
		return dewPoint;
	}

	/**
	 * @param dewPoint the dewPoint to set
	 */
	public void setDewPoint(Integer dewPoint) {
		this.dewPoint = dewPoint;
	}

	/**
	 * @return the altimeter
	 */
	public Integer getAltimeter() {
		return altimeter;
	}

	/**
	 * @param altimeter the altimeter to set
	 */
	public void setAltimeter(Integer altimeter) {
		this.altimeter = altimeter;
	}

	/**
	 * @return the verticalVisibility
	 */
	public Integer getVerticalVisibility() {
		return verticalVisibility;
	}

	/**
	 * @param verticalVisibility the verticalVisibility to set
	 */
	public void setVerticalVisibility(Integer verticalVisibility) {
		this.verticalVisibility = verticalVisibility;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the airport
	 */
	public Airport getAirport() {
		return airport;
	}

	/**
	 * @param airport the airport to set
	 */
	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	/**
	 * @return the visibility
	 */
	public Visibility getVisibility() {
		return visibility;
	}

	/**
	 * @param visibility the visibility to set
	 */
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	/**
	 * @return the runways
	 */
	public List<RunwayInfo> getRunways() {
		return runways;
	}

	public void addRunwayInfo(RunwayInfo ri) {
		this.runways.add(ri);
	}

	/**
	 * @return the weatherConditions
	 */
	public List<WeatherCondition> getWeatherConditions() {
		return weatherConditions;
	}

	public void addWeatherCondition(WeatherCondition wc) {
		weatherConditions.add(wc);
	}
	/**
	 * @return the clouds
	 */
	public List<Cloud> getClouds() {
		return clouds;
	}

	
	public void addCloud(Cloud cloud) {
		this.clouds.add(cloud);
	}
	
	/**
	 * @return the wind
	 */
	public Wind getWind() {
		return wind;
	}

	/**
	 * @param wind the wind to set
	 */
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	/**
	 * @return the nosig
	 */
	public boolean isNosig() {
		return nosig;
	}

	/**
	 * @param nosig the nosig to set
	 */
	public void setNosig(boolean nosig) {
		this.nosig = nosig;
	}

	/**
	 * @return the auto
	 */
	public boolean isAuto() {
		return auto;
	}

	/**
	 * @param auto the auto to set
	 */
	public void setAuto(boolean auto) {
		this.auto = auto;
	}

}
