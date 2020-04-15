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
    /**
     * Intensity of the condition (optional).
     */
    private Intensity intensity;
    /**
     * Descriptive of the condition (optional).
     */
    private List<Descriptive> descriptives;
    /**
     * List of phenomenons of the condition.
     */
    private List<Phenomenon> phenomenons;

    /**
     * Constructor.
     */
    public WeatherCondition() {
        phenomenons = new ArrayList<>();
        descriptives = new ArrayList<>();
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
     * @deprecated replaced by {@link #getDescriptives()}
     * @return the descriptive.
     */
    @Deprecated
    public Descriptive getDescriptive() {
        return descriptives.stream().findFirst().orElse(null);
    }

    /**
     * Getter of the descriptives.
     *
     * @return the descriptives.
     */
    public List<Descriptive> getDescriptives() {
        return descriptives;
    }

    /**
     * Setter of the descriptive.
     *
     * @param pDescriptive the descriptive to set.
     */
    public void addDescriptive(final Descriptive pDescriptive) {
        descriptives.add(pDescriptive);
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
        return !phenomenons.isEmpty() || !descriptives.isEmpty();
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                append(Messages.getInstance().getString("ToString.intensity"), intensity).
                append(Messages.getInstance().getString("ToString.descriptive"), descriptives.toString()).
                append(Messages.getInstance().getString("ToString.phenomenons"), phenomenons.toString()).
                toString();
    }
}
