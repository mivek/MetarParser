package io.github.mivek.provider.airport.impl;

import com.opencsv.exceptions.CsvValidationException;
import io.github.mivek.model.Airport;
import io.github.mivek.provider.airport.AirportProvider;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;

/**
 * @author mivek
 */
public class OurAirportsAirportProviderTest {

    @Test
    public void testGetAirport() throws CsvValidationException, IOException, URISyntaxException {
        AirportProvider provider = new OurAirportsAirportProvider();

        Map<String, Airport> airports = provider.getAirports();

        assertThat(airports, not(anEmptyMap()));
        assertThat(airports, hasKey("LFPG"));
        assertNotNull(airports.get("LFPG"));
    }
}
