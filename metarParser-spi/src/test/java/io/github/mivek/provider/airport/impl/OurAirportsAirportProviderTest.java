package io.github.mivek.provider.airport.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anEmptyMap;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import io.github.mivek.model.Airport;
import io.github.mivek.provider.airport.AirportProvider;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * @author mivek
 */
class OurAirportsAirportProviderTest {

    @Test
    void testGetAirport() throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = mock(HttpClient.class);
        HttpResponse<InputStream> countriesResponse = mock(HttpResponse.class);
        HttpResponse<InputStream> airportsResponse = mock(HttpResponse.class);
        doReturn(fixture("countries.csv")).when(countriesResponse).body();
        doReturn(fixture("airports.csv")).when(airportsResponse).body();
        doReturn(countriesResponse, airportsResponse).when(client).send(any(HttpRequest.class), any());

        AirportProvider provider = new OurAirportsAirportProvider(client);

        Map<String, Airport> airports = provider.getAirports();

        assertThat(airports, not(anEmptyMap()));
        assertThat(airports, hasKey("LFPG"));
        assertNotNull(airports.get("LFPG"));
    }

    private static InputStream fixture(final String name) {
        return Objects.requireNonNull(
                OurAirportsAirportProviderTest.class.getResourceAsStream("/ourairports/" + name));
    }
}
