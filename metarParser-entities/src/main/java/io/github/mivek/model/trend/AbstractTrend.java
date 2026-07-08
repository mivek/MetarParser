package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.AbstractWeatherContainer;
import java.util.Locale;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Abstract class for trends.
 *
 * @author mivek
 */
public abstract class AbstractTrend extends AbstractWeatherContainer {
    /** Type of trend. */
    private final WeatherChangeType type;

    /**
     * Constructor.
     *
     * @param type the type of the trend.
     */
    protected AbstractTrend(final WeatherChangeType type) {
        super();
        this.type = type;
    }

    /**
     * @return the type
     */
    public final WeatherChangeType getType() {
        return type;
    }

    /**
     * @return a description of the object using the JVM default locale.
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
    @Override
    public String toString(final Locale locale) {
        return new ToStringBuilder(this).appendToString(type.toString(locale)).appendSuper(super.toString(locale)).toString();
    }
}
