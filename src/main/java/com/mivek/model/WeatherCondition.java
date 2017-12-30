package com.mivek.model;

import java.util.ArrayList;
import java.util.List;

import com.mivek.enums.Descriptive;
import com.mivek.enums.Intensity;
import com.mivek.enums.Phenomenon;

/**
 * Weather condition class.
 *
 * @author mivek
 *
 */
public class WeatherCondition {
	/**
	 * Intensity of the condition (optional).
	 */
	private Intensity intensity;
	/**
	 * Descriptive of the condition (optional).
	 */
	private Descriptive descriptive;
	/**
	 * List of phenomenons of the condition.
	 */
	private List<Phenomenon> phenomenons;

	/**
	 * Constructor.
	 */
	public WeatherCondition() {
		phenomenons = new ArrayList<>();
	}

	/**
	 * Getter of intensity.
	 *
	 * @return the Intensity of the condition.
	 */
	public Intensity getIntensity() {
		return intensity;
	}

	/**
	 * Setter of intensity.
	 *
	 * @param pIntensity
	 *            The intensity to set.
	 */
	public void setIntensity(final Intensity pIntensity) {
		this.intensity = pIntensity;
	}

	/**
	 * Getter of the descriptive.
	 *
	 * @return the descriptive.
	 */
	public Descriptive getDescriptive() {
		return descriptive;
	}

	/**
	 * Setter of the descriptive.
	 *
	 * @param pDescriptive
	 *            the descriptive to set.
	 */
	public void setDescriptive(final Descriptive pDescriptive) {
		this.descriptive = pDescriptive;
	}

	/**
	 * Getter of the phenomenons list.
	 *
	 * @return a list of phenomenons.
	 */
	public List<Phenomenon> getPhenomenons() {
		return phenomenons;
	}

	/**
	 * Setter of phenomenons list.
	 *
	 * @param pPhenomenons
	 *            the list to set.
	 */
	public void setPhenomenons(final List<Phenomenon> pPhenomenons) {
		this.phenomenons = pPhenomenons;
	}

	/**
	 * Adds a phenomenon to the list.
	 *
	 * @param p
	 *            The Phenomenon to add.
	 */
	public void addPhenomenon(final Phenomenon p) {
		this.phenomenons.add(p);
	}

	/**
	 * Checks if the weather condition is valid.
	 *
	 * @return true if there is at least phenomenon.
	 */
	public boolean isValid() {
		return !phenomenons.isEmpty();
	}
}
