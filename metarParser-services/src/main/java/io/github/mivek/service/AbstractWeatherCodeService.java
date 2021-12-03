package io.github.mivek.service;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.AbstractWeatherCode;
import io.github.mivek.parser.AbstractParser;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

/**
 * Abstract service.
 *
 * @param <T> a concrete sub-class of {@link AbstractWeatherCode}.
 * @author mivek
 * Abstract class for the service.
 */
public abstract class AbstractWeatherCodeService<T extends AbstractWeatherCode> implements IWeatherCodeFacade<T> {
    /** Const for icao length. */
    public static final int ICAO = 4;

    /** The parser. */
    private final AbstractParser<T> parser;

    /**
     * Protected constructor to be used by sub-classes.
     *
     * @param parser the parser to set.
     */
    protected AbstractWeatherCodeService(final AbstractParser<T> parser) {
        this.parser = parser;
    }

    /**
     * @return the parser.
     */
    protected AbstractParser<T> getParser() {
        return parser;
    }

    /**
     * Checks if the icao is composed of 4 characteres.
     * @param icao The icao to test
     * @throws ParseException if the icao is invalid
     */
    void checkIcao(final String icao) throws ParseException {
        if (icao.length() != AbstractWeatherCodeService.ICAO) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO);
        }
    }

    /**
     * Builds a request object.
     * @param website The URI of the request
     * @return The request object ready to use.
     * @throws URISyntaxException When URI is invalid
     */
    HttpRequest buildRequest(final String website) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(website))
                .GET()
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.ofSeconds(5))
                .build();
    }
}
