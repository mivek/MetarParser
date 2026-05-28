package io.github.mivek.model;

import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.internationalization.Messages;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
        return (!phenomenons.isEmpty()
                || Descriptive.THUNDERSTORM == descriptive)
                || Intensity.IN_VICINITY.equals(intensity) && Descriptive.SHOWERS.equals(descriptive);
    }

    @Override
    public final String toString() {
        return toString(Locale.getDefault());
    }

    /**
     * Returns a locale-aware string representation.
     * @param locale the locale to use for labels and sub-objects.
     * @return the string representation.
     */
    public String toString(final Locale locale) {
        return new ToStringBuilder(this).
                append(Messages.getInstance().getString(locale, "ToString.intensity"), localized(intensity, locale)).
                append(Messages.getInstance().getString(locale, "ToString.descriptive"), localized(descriptive, locale)).
                append(Messages.getInstance().getString(locale, "ToString.phenomenons"),
                    phenomenons.stream().map(p -> p.toString(locale)).collect(Collectors.joining(", "))).
                toString();
    }

    private static String localized(final Object obj, final Locale locale) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Intensity i) {
            return i.toString(locale);
        }
        if (obj instanceof Descriptive d) {
            return d.toString(locale);
        }
        return obj.toString();
    }
}
