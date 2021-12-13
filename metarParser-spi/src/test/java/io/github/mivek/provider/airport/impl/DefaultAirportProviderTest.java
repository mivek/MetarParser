package io.github.mivek.provider.airport.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.mivek.model.Airport;
import io.github.mivek.provider.airport.AirportProvider;
import java.util.Map;
import org.junit.jupiter.api.Test;

/** @author mivek */
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
