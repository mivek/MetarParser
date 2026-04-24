package io.github.mivek.service;

import io.github.mivek.model.AbstractWeatherCode;
import io.github.mivek.parser.AbstractWeatherCodeParser;
import io.github.mivek.service.provider.WeatherProvider;

/**
 * Abstract service.
 *
 * @param <T> a concrete subclass of {@link AbstractWeatherCode}.
 * @author mivek
 * Abstract class for the service.
 */
public abstract class AbstractWeatherCodeService<T extends AbstractWeatherCode> implements IWeatherCodeFacade<T> {
    /** Const for icao length. */
    public static final int ICAO = 4;

    /** The parser. */
    private final AbstractWeatherCodeParser<T> parser;

    /** The weather provider used to fetch raw weather data. */
    private final WeatherProvider provider;

    /**
     * Protected constructor to be used by subclasses.
     *
     * @param parser   the parser to set.
     * @param provider the weather provider to use for fetching raw data.
     */
    protected AbstractWeatherCodeService(final AbstractWeatherCodeParser<T> parser, final WeatherProvider provider) {
        this.parser = parser;
        this.provider = provider;
    }

    /**
     * @return the parser.
     */
    protected AbstractWeatherCodeParser<T> getParser() {
        return parser;
    }

    /**
     * @return the weather provider.
     */
    protected WeatherProvider getProvider() {
        return provider;
    }
}
