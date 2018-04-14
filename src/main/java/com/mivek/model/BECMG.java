package com.mivek.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the BECMG part of a metar.
 *
 * @author mivek
 *
 */
public class BECMG {
	/**
	 * Start time.
	 */
	private LocalTime start;
	/**
	 * End time.
	 */
	private LocalTime end;
	/**
	 * List of clouds.
	 */
	private List<Cloud> clouds;
	/**
	 * List of weather conditions.
	 */
	private List<WeatherCondition> weatherConditions;

	/**
	 * Constructor.
	 */
	public BECMG() {
		clouds = new ArrayList<>();
		weatherConditions = new ArrayList<>();
	}

	/**
	 * @return the start. The start of the changement.
	 */
	public LocalTime getStart() {
		return start;
	}

	/**
	 * @param pStart
	 *            the start to set
	 */
	public void setStart(final LocalTime pStart) {
		this.start = pStart;
	}

	/**
	 * @return the end. The end of the changement.
	 */
	public LocalTime getEnd() {
		return end;
	}

	/**
	 * @param pEnd
	 *            the end to set.
	 */
	public void setEnd(final LocalTime pEnd) {
		this.end = pEnd;
	}

	/**
	 * @return the clouds
	 */
	public List<Cloud> getClouds() {
		return clouds;
	}

	/**
	 * @return the weatherConditions
	 */
	public List<WeatherCondition> getWeatherConditions() {
		return weatherConditions;
	}

	/**
	 * Adds a cloud to the list.
	 *
	 * @param c
	 *            the cloud to add
	 */
	public void addCloud(final Cloud c) {
		clouds.add(c);
	}

	/**
	 * Adds a weather condition to the list.
	 *
	 * @param wc
	 *            the weathercondition to add.
	 */
	public void addWeatherCondition(final WeatherCondition wc) {
		weatherConditions.add(wc);
	}
}
