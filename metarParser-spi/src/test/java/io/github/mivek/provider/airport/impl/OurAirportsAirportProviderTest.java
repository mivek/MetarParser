package io.github.mivek.provider.airport.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anEmptyMap;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.mivek.model.Airport;
import io.github.mivek.provider.airport.AirportProvider;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * @author mivek
 */
class OurAirportsAirportProviderTest {

    @Test
    void testGetAirport() throws IOException, URISyntaxException, InterruptedException {
        AirportProvider provider = new OurAirportsAirportProvider();

        Map<String, Airport> airports = provider.getAirports();

        assertThat(airports, not(anEmptyMap()));
        assertThat(airports, hasKey("LFPG"));
        assertNotNull(airports.get("LFPG"));
    }
}
