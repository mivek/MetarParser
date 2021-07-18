package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntensityTest {

    @Test
    public void testGetEnum() {
        assertThrows(IllegalArgumentException.class, () -> Intensity.getEnum("QWERTY"));
    }

    @Test
    public void testGetEnumValid() {
        assertEquals(Intensity.LIGHT, Intensity.getEnum("-"));
    }

    @Test
    public void testToStringWithMultipleLocale() {
        Messages.getInstance().setLocale(Locale.FRANCE);
        assertEquals("Faible", Intensity.LIGHT.toString());

        Messages.getInstance().setLocale(Locale.ENGLISH);
        assertEquals("Light", Intensity.LIGHT.toString());
    }
}
