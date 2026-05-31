package io.github.mivek.enums;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;
import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class IcingIntensityTest {
  @Test
  void testGetEnum() {
    assertNull(IcingIntensity.get("QWERTY"));
  }

  @Test
  void testGetEnumValid() {
    assertEquals(IcingIntensity.LIGHT, IcingIntensity.get("1"));
  }

  @Test
  void testToString() {
    assertEquals("Severe Mixed Icing", IcingIntensity.SEVERE_MIXED_ICING.toString(Locale.ENGLISH));
  }
}
