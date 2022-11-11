package io.github.mivek.enums;

import static org.junit.jupiter.api.Assertions.*;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;
import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class TurbulenceIntensityTest {

  @Test
  void testGetEnum() {
    assertNull(TurbulenceIntensity.get("QWERTY"));
  }

  @Test
  void testGetEnumValid() {
    assertEquals(TurbulenceIntensity.LIGHT, TurbulenceIntensity.get("1"));
  }

  @Test
  void testToString() {
    Messages.getInstance().setLocale(Locale.ENGLISH);
    assertEquals("Extreme turbulence", TurbulenceIntensity.EXTREME.toString());
  }
}
