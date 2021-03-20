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
    private final List<Phenomenon> phenomenons;

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
     * @param intensity The intensity to set.
     */
    public void setIntensity(final Intensity intensity) {
        this.intensity = intensity;
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
     * @param descriptive the descriptive to set.
     */
    public void setDescriptive(final Descriptive descriptive) {
        this.descriptive = descriptive;
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
     * @param phenomenon The Phenomenon to add.
     */
    public void addPhenomenon(final Phenomenon phenomenon) {
        phenomenons.add(phenomenon);
    }

    /**
     * Checks if the weather condition is valid.
     *
     * @return true if there is at least phenomenon.
     */
    public boolean isValid() {
        return !phenomenons.isEmpty() ||
                Descriptive.THUNDERSTORM == descriptive ||
                (Intensity.IN_VICINITY.equals(intensity) && Descriptive.SHOWERS.equals(descriptive));
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                append(Messages.getInstance().getString("ToString.intensity"), intensity).
                append(Messages.getInstance().getString("ToString.descriptive"), descriptive).
                append(Messages.getInstance().getString("ToString.phenomenons"), phenomenons.toString()).
                toString();
    }
}
