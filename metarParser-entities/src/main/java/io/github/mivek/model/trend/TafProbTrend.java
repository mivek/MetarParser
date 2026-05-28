package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.internationalization.Messages;
import java.util.Locale;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing a weather change with a given probability.
 *
 * @author f.loris
 */
public class TafProbTrend extends TafTrend {

    /** Probability of the trend. */
    private Integer probability;

    /**
     * Constructor with parameter.
     *
     * @param type the type to set.
     */
    public TafProbTrend(final WeatherChangeType type) {
        super(type);
    }

    /**
     * @return the probability
     */
    public Integer getProbability() {
        return probability;
    }

    /**
     * @param probability the probability to set
     */
    public void setProbability(final int probability) {
        this.probability = probability;
    }

    /**
     * @return A description of the object using the JVM default locale.
     */
    @Override
    public String toString() {
        return toString(Locale.getDefault());
    }

    /**
     * Returns a locale-aware string representation.
     * @param locale the locale to use for labels and sub-objects.
     * @return the string representation.
     */
    public String toString(final Locale locale) {
        return new ToStringBuilder(this).
                appendSuper(super.toString(locale)).
                append(Messages.getInstance().getString(locale, "ToString.probability"), probability).
                toString();
    }
}
