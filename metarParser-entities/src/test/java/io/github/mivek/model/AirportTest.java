package io.github.mivek.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {
    private Airport airport;

    @BeforeEach
    void init() {
        airport = new Airport();
    }

    @Test
    void testEqual() {
        airport.setIcao("111");

        Airport other1 = new Airport();
        other1.setIcao("111");

        Airport other2 = new Airport();
        other2.setIcao("498");

        assertEquals(airport, other1);
        assertNotEquals(airport, other2);
        assertNotNull(airport);
        assertNotEquals(new Object(), airport);
    }

    @Test
    void test() {
        String city = "London";
        String timezone = "Europe/London";
        String icao = "EGLL";
        String iata = "LHR";
        String name = "London Heathrow Airport";
        int altitude = 51;
        double latitude = 51.4706;
        double longitude = -0.461941;
        airport = new Airport();
        airport.setCity(city);
        airport.setTimezone(timezone);
        airport.setIcao(icao);
        airport.setIata(iata);
        airport.setName(name);
        airport.setAltitude(altitude);
        airport.setLatitude(latitude);
        airport.setLongitude(longitude);
        assertEquals(city, airport.getCity());
        assertEquals(timezone, airport.getTimezone());
        assertEquals(icao, airport.getIcao());
        assertEquals(iata, airport.getIata());
        assertEquals(name, airport.getName());
        assertEquals(latitude, airport.getLatitude(), 0);
        assertEquals(longitude, airport.getLongitude(), 0);
        assertEquals(altitude, airport.getAltitude());
    }
}
