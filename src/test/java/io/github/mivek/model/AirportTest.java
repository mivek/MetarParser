package io.github.mivek.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AirportTest {
    private Airport sut;

    @Before
    public void init() {
        sut = new Airport();
    }

    @Test
    public void testEqual() {
        sut.setIcao("111");

        Airport other1 = new Airport();
        other1.setIcao("111");

        Airport other2 = new Airport();
        other2.setIcao("498");

        assertTrue(sut.equals(sut));
        assertTrue(sut.equals(other1));
        assertFalse(sut.equals(other2));
        assertFalse(sut.equals(null));
        assertFalse(sut.equals(new Object()));
    }

    @Test
    public void test() {
        String city = "London";
        String timezone = "Europe/London";
        String icao = "EGLL";
        String iata = "LHR";
        String name = "London Heathrow Airport";
        int altitude = 51;
        double latitude = 51.4706;
        double longitude = -0.461941;
        sut = new Airport();
        sut.setCity(city);
        sut.setTimezone(timezone);
        sut.setIcao(icao);
        sut.setIata(iata);
        sut.setName(name);
        sut.setAltitude(altitude);
        sut.setLatitude(latitude);
        sut.setLongitude(longitude);
        assertEquals(city, sut.getCity());
        assertEquals(timezone, sut.getTimezone());
        assertEquals(icao, sut.getIcao());
        assertEquals(iata, sut.getIata());
        assertEquals(name, sut.getName());
        assertEquals(latitude, sut.getLatitude(), 0);
        assertEquals(longitude, sut.getLongitude(),0);
        assertEquals(altitude, sut.getAltitude());
    }
}
