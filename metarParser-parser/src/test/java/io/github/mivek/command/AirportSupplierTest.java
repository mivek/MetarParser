package io.github.mivek.command;

import io.github.mivek.model.Airport;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author mivek
 */
public class AirportSupplierTest {

    private AirportSupplier sut;

    @Before public void setUp() {
        sut = new AirportSupplier();
    }

    @Test public void testGetWithExistingIcao() {
        Airport res = sut.get("LFPG");
        assertNotNull(res);
    }

    @Test public void testWithNonExistingIcao() {
        Airport res = sut.get("AA");
        assertNull(res);
    }
}
