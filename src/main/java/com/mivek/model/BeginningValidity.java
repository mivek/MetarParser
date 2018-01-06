/**
 * 
 */
package com.mivek.model;

/**
 * @author mivek
 *
 */
public class BeginningValidity extends AbstractValidity {
	private Integer fStartMinutes;

	/**
	 * @return the startMinutes
	 */
	public Integer getStartMinutes() {
		return fStartMinutes;
	}

	/**
	 * @param pStartMinutes
	 *            the startMinutes to set
	 */
	public void setStartMinutes(final Integer pStartMinutes) {
		fStartMinutes = pStartMinutes;
	}
}
