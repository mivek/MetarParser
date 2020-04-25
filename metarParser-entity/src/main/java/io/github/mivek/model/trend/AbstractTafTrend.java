package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.trend.validity.AbstractValidity;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing a weather change.
 * @param <T> a concrete subclass of {@link AbstractValidity}
 * @author mivek
 */
public abstract class AbstractTafTrend<T extends AbstractValidity> extends AbstractTrend {
    /** The validity of the change. */
    private T validity;

    /**
     * Constructor with parameter.
     * @param pType the type to set.
     */
    protected AbstractTafTrend(final WeatherChangeType pType) {
        super(pType);
    }

    /**
     * @return the validity
     */
    public T getValidity() {
        return validity;
    }

    /**
     * @param pValidity the validity to set
     */
    public void setValidity(final T pValidity) {
        validity = pValidity;
    }


    /**
     * @return A description of the object.
     */
    @Override public String toString() {
        return new ToStringBuilder(this).
                appendSuper(super.toString()).
                appendToString(validity.toString()).
                toString();
    }
}
