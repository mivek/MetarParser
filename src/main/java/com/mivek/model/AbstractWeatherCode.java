package com.mivek.model;

import java.time.LocalTime;

/**
 * @author mivek
 */
public abstract class AbstractWeatherCode extends AbstractWeatherContainer {

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
	 * Original message of the metar.
	 */
	private String fMessage;

	/**
	 * Default contructor.
	 */
	public AbstractWeatherCode() {
		super();
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
}
