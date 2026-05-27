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

    /** The default User-Agent string sent with every HTTP request. */
    static final String DEFAULT_USER_AGENT = "MetarParser";

    /** The User-Agent string sent with every HTTP request. */
    private final String userAgent;

    /** The HTTP client used to execute requests. */
    private final HttpClient httpClient;

    /**
     * Protected constructor. Uses the default User-Agent and a default {@link HttpClient}.
     */
    protected AbstractWeatherProvider() {
        this(DEFAULT_USER_AGENT, HttpClient.newBuilder().build());
    }

    /**
     * Protected constructor with a custom User-Agent string and a default {@link HttpClient}.
     *
     * @param userAgent the User-Agent header value to send with every HTTP request.
     */
    protected AbstractWeatherProvider(final String userAgent) {
        this(userAgent, HttpClient.newBuilder().build());
    }

    /**
     * Package-private constructor for testing. Accepts an injectable {@link HttpClient}.
     *
     * @param httpClient the HTTP client to use for all requests.
     */
    AbstractWeatherProvider(final HttpClient httpClient) {
        this(DEFAULT_USER_AGENT, httpClient);
    }

    /**
     * Private canonical constructor used by all other constructors.
     *
     * @param userAgent  the User-Agent header value to send with every HTTP request.
     * @param httpClient the HTTP client to use for all requests.
     */
    private AbstractWeatherProvider(final String userAgent, final HttpClient httpClient) {
        this.userAgent = userAgent;
        this.httpClient = httpClient;
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
     * Builds an HTTP GET request for the given URL, including the configured {@code User-Agent} header.
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
                .header("User-Agent", userAgent)
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
        HttpResponse<Stream<String>> response = httpClient.send(request, HttpResponse.BodyHandlers.ofLines());
        if (response.statusCode() != HttpURLConnection.HTTP_OK) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO);
        }
        return response;
    }
}
