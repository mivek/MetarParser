package io.github.mivek.enums;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntensityTest {

    @Test
    void testGetEnum() {
        assertThrows(IllegalArgumentException.class, () -> Intensity.getEnum("QWERTY"));
    }

    @Test
    void testGetEnumValid() {
        assertEquals(Intensity.LIGHT, Intensity.getEnum("-"));
    }

    @Test
    void testToStringWithMultipleLocale() {
        assertEquals("Faible", Intensity.LIGHT.toString(Locale.FRANCE));
        assertEquals("Light", Intensity.LIGHT.toString(Locale.ENGLISH));
    }
}
