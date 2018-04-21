package com.mivek.model;

/**
 * Wind class.
 * TODO Change unit to enumeration class.
 * @author mivek
 *
 */
public class Wind {
	/**
	 * Speed of the wind.
	 */
	private int speed;
	/**
	 * Direction of the wind.
	 */
	private String direction;
	/**
	 * Direction of the wind.
	 */
	private int directionDegrees;
	/**
	 * The speed of the gust.
	 */
	private int gust;
	/**
	 * The lowest speed of the extreme wind.
	 */
	private int extreme1;
	/**
	 * The maximum speed of the extreme wind.
	 */
	private int extreme2;
	/**
	 * The unit of the speed.
	 */
	private String unit;

	/**
	 * Getter of the speed.
	 *
	 * @return the speed.
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Setter of the speed.
	 *
	 * @param pSpeed
	 *            the speed to set.
	 */
	public void setSpeed(final int pSpeed) {
		this.speed = pSpeed;
	}

	/**
	 * Getter of the direction.
	 *
	 * @return The Direction of the wind.
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Setter of the direction of the wind.
	 *
	 * @param pDirection
	 *            the direction to set.
	 */
	public void setDirection(final String pDirection) {
		this.direction = pDirection;
	}

	/**
	 * Getter of the gust.
	 *
	 * @return the gust.
	 */
	public int getGust() {
		return gust;
	}

	/**
	 * Setter of the gust.
	 *
	 * @param pGust
	 *            the gust to set.
	 */
	public void setGust(final int pGust) {
		this.gust = pGust;
	}

	/**
	 * Getter of the lowest extreme wind speed.
	 *
	 * @return the lowest extreme speed.
	 */
	public int getExtreme1() {
		return extreme1;
	}

	/**
	 * Setter of extreme1.
	 *
	 * @param pExtreme1
	 *            the speed to set.
	 */
	public void setExtreme1(final int pExtreme1) {
		this.extreme1 = pExtreme1;
	}

	/**
	 * Getter of the maximal extreme speed.
	 *
	 * @return the speed.
	 */
	public int getExtreme2() {
		return extreme2;
	}

	/**
	 * Setter.
	 *
	 * @param pExtreme2
	 *            the speed to set.
	 */
	public void setExtreme2(final int pExtreme2) {
		this.extreme2 = pExtreme2;
	}

	/**
	 * Getter of the unit.
	 *
	 * @return the unit.
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Setter.
	 *
	 * @param pUnit
	 *            The unit to set.
	 */
	public void setUnit(final String pUnit) {
		this.unit = pUnit;
	}

	/**
	 * @return the directionDegrees.
	 */
	public int getDirectionDegrees() {
		return directionDegrees;
	}

	/**
	 * @param pDirectionDegrees
	 *            the directionDegrees to set.
	 */
	public void setDirectionDegrees(final int pDirectionDegrees) {
		directionDegrees = pDirectionDegrees;
	}

}
