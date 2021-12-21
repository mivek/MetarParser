package io.github.mivek.command;

import io.github.mivek.model.Airport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author mivek
 */
class AirportSupplierTest {

    private AirportSupplier supplier;

    @BeforeEach
    void setUp() {
        supplier = new AirportSupplier();
    }

    @Test
    void testGetWithExistingIcao() {
        Airport res = supplier.get("LFPG");
        assertNotNull(res);
    }

    @Test
    void testWithNonExistingIcao() {
        Airport res = supplier.get("AA");
        assertNull(res);
    }
}
