package io.github.mivek.service.provider;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

/**
 * Abstract base class for weather providers.
 * Provides common HTTP utilities for fetching weather data from remote sources.
 *
 * @author mivek
 */
public abstract class AbstractWeatherProvider implements WeatherProvider {

    /** The required length of a valid ICAO code. */
    static final int ICAO_LENGTH = 4;

    /**
     * Protected constructor.
     */
    protected AbstractWeatherProvider() {
    }

    /**
     * Validates that the given ICAO code has the correct length.
     *
     * @param icao the ICAO code to validate.
     * @throws ParseException when the ICAO code does not have exactly 4 characters.
     */
    protected final void checkIcao(final String icao) throws ParseException {
        if (icao.length() != ICAO_LENGTH) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO);
        }
    }

    /**
     * Builds an HTTP GET request for the given URL.
     *
     * @param url the URL to request.
     * @return the constructed {@link HttpRequest}.
     * @throws URISyntaxException when the URL string cannot be parsed as a URI.
     */
    protected final HttpRequest buildRequest(final String url) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    /**
     * Executes an HTTP GET request and returns the response body as a stream of lines.
     * Throws a {@link ParseException} when the response status is not 200 OK.
     *
     * @param url the URL to request.
     * @return the HTTP response with body as a stream of lines.
     * @throws IOException          when a network error occurs.
     * @throws URISyntaxException   when the URL string cannot be parsed as a URI.
     * @throws InterruptedException when the HTTP request is interrupted.
     * @throws ParseException       when the server returns a non-200 status code.
     */
    protected final HttpResponse<Stream<String>> getHttpResponse(final String url)
            throws IOException, URISyntaxException, InterruptedException, ParseException {
        HttpRequest request = buildRequest(url);
        HttpResponse<Stream<String>> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofLines());
        if (response.statusCode() != HttpURLConnection.HTTP_OK) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO);
        }
        return response;
    }
}
