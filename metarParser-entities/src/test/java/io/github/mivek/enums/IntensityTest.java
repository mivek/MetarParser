package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class IntensityTest {

    @Test(expected = IllegalArgumentException.class)
    public void testGetEnum() {
        Intensity.getEnum("QWERTY");
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
