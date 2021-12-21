package io.github.mivek.command;

import io.github.mivek.model.Airport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author mivek
 */
public class AirportSupplierTest {

    private AirportSupplier sut;

    @BeforeEach
    void setUp() {
        sut = new AirportSupplier();
    }

    @Test
    void testGetWithExistingIcao() {
        Airport res = sut.get("LFPG");
        assertNotNull(res);
    }

    @Test
    void testWithNonExistingIcao() {
        Airport res = sut.get("AA");
        assertNull(res);
    }
}
