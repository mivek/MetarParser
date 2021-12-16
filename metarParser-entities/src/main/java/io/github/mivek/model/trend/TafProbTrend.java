package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.internationalization.Messages;
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
    public void setProbability(final Integer probability) {
        this.probability = probability;
    }

    /**
     * @return A description of the object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).
                appendSuper(super.toString()).
                append(Messages.getInstance().getString("ToString.probability"), probability).
                toString();
    }
}
