package io.github.mivek.service.provider;

import io.github.mivek.exception.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Interface for weather data providers.
 * Implementations are responsible for fetching raw METAR and TAF strings from external sources.
 * To add a new provider, implement this interface and pass an instance to
 * {@link io.github.mivek.service.MetarService#withProvider(WeatherProvider)} or
 * {@link io.github.mivek.service.TAFService#withProvider(WeatherProvider)}.
 *
 * @author mivek
 */
public interface WeatherProvider {

    /**
     * Retrieves the raw METAR string for the given ICAO code.
     *
     * @param icao the ICAO code of the airport.
     * @return the raw METAR string ready for parsing.
     * @throws ParseException       when the ICAO is invalid or no data is found for the station.
     * @throws IOException          when a network error occurs.
     * @throws URISyntaxException   when the request URI is malformed.
     * @throws InterruptedException when the HTTP request is interrupted.
     */
    String retrieveMetar(String icao) throws ParseException, IOException, URISyntaxException, InterruptedException;

    /**
     * Retrieves the raw TAF string for the given ICAO code.
     *
     * @param icao the ICAO code of the airport.
     * @return the raw TAF string ready for parsing.
     * @throws ParseException       when the ICAO is invalid or no data is found for the station.
     * @throws IOException          when a network error occurs.
     * @throws URISyntaxException   when the request URI is malformed.
     * @throws InterruptedException when the HTTP request is interrupted.
     */
    String retrieveTaf(String icao) throws ParseException, IOException, URISyntaxException, InterruptedException;
}
