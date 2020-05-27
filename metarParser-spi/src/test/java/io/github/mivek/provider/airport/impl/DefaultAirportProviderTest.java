package io.github.mivek.provider.airport.impl;

import io.github.mivek.model.Airport;
import io.github.mivek.provider.airport.AirportProvider;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;

/**
 * @author mivek
 */
public class DefaultAirportProviderTest {

    @Test
    public void testGetAirport() {
        AirportProvider provider = new DefaultAirportProvider();

        Map<String, Airport> airports = provider.getAirports();

        assertThat(airports, not(anEmptyMap()));
        assertThat(airports, hasKey("LFPG"));
        assertNotNull(airports.get("LFPG"));
    }
}
