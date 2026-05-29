package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.ITafGroups;
import io.github.mivek.model.Icing;
import io.github.mivek.model.Turbulence;
import io.github.mivek.model.trend.validity.AbstractValidity;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing a weather change.
 *
 * @param <T> a concrete subclass of {@link AbstractValidity}
 * @author mivek
 */
public abstract class AbstractTafTrend<T extends AbstractValidity> extends AbstractTrend implements
    ITafGroups {
    /** The validity of the change. */
    private T validity;
    /** List of turbulences. */
    private final List<Turbulence> turbulences;
    /** List of icings. */
    private final List<Icing> icings;
    /**
     * Constructor with parameter.
     *
     * @param type the type to set.
     */
    protected AbstractTafTrend(final WeatherChangeType type) {
        super(type);
        turbulences = new ArrayList<>();
        icings = new ArrayList<>();
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

    @Override
    public final List<Icing> getIcings() {
        return icings;
    }

    @Override
    public final List<Turbulence> getTurbulences() {
        return turbulences;
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
     * @param locale the locale to use for sub-objects.
     * @return the string representation.
     */
    public String toString(final Locale locale) {
        return new ToStringBuilder(this).
                appendSuper(super.toString(locale)).
                append(turbulences.stream().map(t -> t.toString(locale)).collect(Collectors.joining(", ", "[", "]"))).
                append(icings.stream().map(i -> i.toString(locale)).collect(Collectors.joining(", ", "[", "]"))).
                appendToString(validity != null ? validity.toString(locale) : null).
                toString();
    }
}
