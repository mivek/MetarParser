package io.github.mivek.model;

import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Weather condition class.
 *
 * @author mivek
 */
public class WeatherCondition {
    /** Intensity of the condition (optional). */
    private Intensity intensity;
    /** Descriptive of the condition (optional). */
    private Descriptive descriptive;
    /** List of phenomenons of the condition. */
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
     * @param pIntensity The intensity to set.
     */
    public void setIntensity(final Intensity pIntensity) {
        intensity = pIntensity;
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
     * @param pDescriptive the descriptive to set.
     */
    public void setDescriptive(final Descriptive pDescriptive) {
        descriptive = pDescriptive;
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
     * Adds a phenomenon to the list.
     *
     * @param pPhenomenon The Phenomenon to add.
     */
    public void addPhenomenon(final Phenomenon pPhenomenon) {
        phenomenons.add(pPhenomenon);
    }

    /**
     * Checks if the weather condition is valid.
     *
     * @return true if there is at least phenomenon.
     */
    public boolean isValid() {
        return !phenomenons.isEmpty() || descriptive != null;
    }

    @Override public final String toString() {
        return new ToStringBuilder(this).
                append(Messages.getInstance().getString("ToString.intensity"), intensity).
                append(Messages.getInstance().getString("ToString.descriptive"), descriptive).
                append(Messages.getInstance().getString("ToString.phenomenons"), phenomenons.toString()).
                toString();
    }
}
