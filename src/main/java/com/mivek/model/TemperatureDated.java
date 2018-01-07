/**
 * 
 */
package com.mivek.model;

/**
 * @author mivek
 *
 */
public class TemperatureDated {
	private Integer fTemperature;
	private Integer fDay;
	private Integer fHour;

	/**
	 * @return the temperature
	 */
	public Integer getTemperature() {
		return fTemperature;
	}

	/**
	 * @param pTemperature
	 *            the temperature to set
	 */
	public void setTemperature(final Integer pTemperature) {
		fTemperature = pTemperature;
	}

	/**
	 * @return the day
	 */
	public Integer getDay() {
		return fDay;
	}

	/**
	 * @param pDay
	 *            the day to set
	 */
	public void setDay(final Integer pDay) {
		fDay = pDay;
	}

	/**
	 * @return the hour
	 */
	public Integer getHour() {
		return fHour;
	}

	/**
	 * @param pHour
	 *            the hour to set
	 */
	public void setHour(final Integer pHour) {
		fHour = pHour;
	}

}
