package com.mivek.model;

/**
 * Visisbility class.
 *
 * @author mivek
 *
 */
public class Visibility {
	/**
	 * mainVisibility of the metar.
	 */
	private String mainVisibility;
	/**
	 * minimal visibility of the metar.
	 */
	private int minVisibility;
	/**
	 * Direction of the minimal visibility.
	 */
	private String minDirection;

	/**
	 * Getter of the mainVisibility.
	 *
	 * @return the mainvisibility.
	 */
	public String getMainVisibility() {
		return mainVisibility;
	}

	/**
	 * Setter of the main visibility.
	 *
	 * @param pMainVisibility
	 *            the main visibility to set.
	 */
	public void setMainVisibility(final String pMainVisibility) {
		this.mainVisibility = pMainVisibility;
	}

	/**
	 * Getter of the minimal visibility.
	 *
	 * @return the minimal visibility.
	 */
	public int getMinVisibility() {
		return minVisibility;
	}

	/**
	 * Setter of the minimal visibility.
	 *
	 * @param pMinVisibility
	 *            the minimal visibility to set.
	 */
	public void setMinVisibility(final int pMinVisibility) {
		this.minVisibility = pMinVisibility;
	}

	/**
	 * Getter of direction.
	 *
	 * @return the direction.
	 */
	public String getMinDirection() {
		return minDirection;
	}

	/**
	 * Setter of the minimal direction.
	 *
	 * @param pMinDirection
	 *            the minimal direction to set.
	 */
	public void setMinDirection(final String pMinDirection) {
		this.minDirection = pMinDirection;
	}
}
