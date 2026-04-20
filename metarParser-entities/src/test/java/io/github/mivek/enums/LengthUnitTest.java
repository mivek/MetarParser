package io.github.mivek.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class LengthUnitTest {

    @Test
    void testGetMeters() {
        assertEquals(LengthUnit.METERS, LengthUnit.get("M"));
    }

    @Test
    void testGetFeet() {
        assertEquals(LengthUnit.FEET, LengthUnit.get("FT"));
    }

    @Test
    void testGetUnknown() {
        assertNull(LengthUnit.get("NM"));
    }

    @Test
    void testToString() {
        assertEquals("FT", LengthUnit.FEET.toString());
    }
}

