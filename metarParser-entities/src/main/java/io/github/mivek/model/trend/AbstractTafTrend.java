package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.trend.validity.AbstractValidity;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing a weather change.
 *
 * @param <T> a concrete subclass of {@link AbstractValidity}
 * @author mivek
 */
public abstract class AbstractTafTrend<T extends AbstractValidity> extends AbstractTrend {
    /** The validity of the change. */
    private T validity;

    /**
     * Constructor with parameter.
     *
     * @param type the type to set.
     */
    protected AbstractTafTrend(final WeatherChangeType type) {
        super(type);
    }

    /**
     * @return the validity
     */
    public T getValidity() {
        return validity;
    }

    /**
     * @param validity the validity to set
     */
    public void setValidity(final T validity) {
        this.validity = validity;
    }

    /**
     * @return A description of the object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).
                appendSuper(super.toString()).
                appendToString(validity.toString()).
                toString();
    }
}
