package io.github.mivek.service;

import io.github.mivek.model.AbstractWeatherCode;
import io.github.mivek.parser.AbstractParser;

/**
 * Abstract service.
 *
 * @param <T> a concrete sub-class of {@link AbstractWeatherCode}.
 * @author mivek
 * Abstract class for the service.
 */
public abstract class AbstractWeatherCodeService<T extends AbstractWeatherCode> implements IWeatherCodeFacade<T> {
    /**
     * Const for icao length.
     */
    public static final int ICAO = 4;

    /**
     * The parser.
     */
    private final AbstractParser<T> fParser;

    /**
     * Protected constructor to be used by sub-classes.
     *
     * @param parser the parser to set.
     */
    protected AbstractWeatherCodeService(final AbstractParser<T> parser) {
        fParser = parser;
    }

    /**
     * @return the parser.
     */
    protected AbstractParser<T> getParser() {
        return fParser;
    }

}
