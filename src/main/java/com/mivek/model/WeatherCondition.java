package com.mivek.model;

import java.util.ArrayList;
import java.util.List;

import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;

/**
 * Weather condition class.
 * @author mivek
 */
public class WeatherCondition {
    /**
     * Intensity of the condition (optional).
     */
    private Intensity fIntensity;
    /**
     * Descriptive of the condition (optional).
     */
    private Descriptive fDescriptive;
    /**
     * List of phenomenons of the condition.
     */
    private List<Phenomenon> fPhenomenons;

    /**
     * Constructor.
     */
    public WeatherCondition() {
        fPhenomenons = new ArrayList<>();
    }

    /**
     * Getter of intensity.
     * @return the Intensity of the condition.
     */
    public Intensity getIntensity() {
        return fIntensity;
    }

    /**
     * Setter of intensity.
     * @param pIntensity The intensity to set.
     */
    public void setIntensity(final Intensity pIntensity) {
        fIntensity = pIntensity;
    }

    /**
     * Getter of the descriptive.
     * @return the descriptive.
     */
    public Descriptive getDescriptive() {
        return fDescriptive;
    }

    /**
     * Setter of the descriptive.
     * @param pDescriptive the descriptive to set.
     */
    public void setDescriptive(final Descriptive pDescriptive) {
        fDescriptive = pDescriptive;
    }

    /**
     * Getter of the phenomenons list.
     * @return a list of phenomenons.
     */
    public List<Phenomenon> getPhenomenons() {
        return fPhenomenons;
    }

    /**
     * Setter of phenomenons list.
     * @param pPhenomenons the list to set.
     */
    public void setPhenomenons(final List<Phenomenon> pPhenomenons) {
        fPhenomenons = pPhenomenons;
    }

    /**
     * Adds a phenomenon to the list.
     * @param pPhenomenon The Phenomenon to add.
     */
    public void addPhenomenon(final Phenomenon pPhenomenon) {
        fPhenomenons.add(pPhenomenon);
    }

    /**
     * Checks if the weather condition is valid.
     * @return true if there is at least phenomenon.
     */
    public boolean isValid() {
        return !fPhenomenons.isEmpty();
    }
}
