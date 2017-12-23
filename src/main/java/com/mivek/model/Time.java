package com.mivek.model;

/**
 * Class representing the time of the metar.
 *
 * @author mivek
 *
 */
public class Time {
	/**
	 * The hour.
	 */
	private int hours;
	/**
	 * The minute.
	 */
	private int minutes;

	/**
	 * Getter of hours.
	 *
	 * @return the hours.
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * Setter of hours.
	 *
	 * @param pHours
	 *            the hours to set.
	 */
	public void setHours(final int pHours) {
		this.hours = pHours;
	}

	/**
	 * Getter of minutes.
	 *
	 * @return the minutes.
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * Setter of minutes.
	 *
	 * @param pMinutes
	 *            the minutes to set.
	 */
	public void setMinutes(final int pMinutes) {
		this.minutes = pMinutes;
	}
}
