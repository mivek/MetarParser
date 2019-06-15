package io.github.mivek.command;

import io.github.mivek.model.Airport;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author mivek
 */
public class AirportSupplierTest {

    private AirportSupplier sut;

    @Before public void setUp() {
        sut = new AirportSupplier();
    }

    @Test public void testGetWithExistingIcao() {
        Optional<Airport> res = sut.get("LFPG");

        assertTrue(res.isPresent());
    }

    @Test public void testWithNonExistingIcao() {
        Optional<Airport> res = sut.get("AA");

        assertFalse(res.isPresent());
    }
}
