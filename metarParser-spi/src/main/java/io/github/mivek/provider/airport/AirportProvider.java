package io.github.mivek.provider.airport;

import io.github.mivek.model.Airport;

import java.util.Map;

/**
 * Service providing {@link Airport}.
 *
 * @author mivek
 */
public interface AirportProvider {

    /**
     * @return a map of airports with the ICAO code as key.
     */
    Map<String, Airport> getAirports();
}

