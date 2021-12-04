package io.github.mivek.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;
import org.junit.jupiter.api.Test;

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
