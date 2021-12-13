package io.github.mivek.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;
import org.junit.jupiter.api.Test;

/** @author mivek */
public class PhenomenonTest {

  @Test
  public void testToStringWithMultipleLocale() {
    Messages.getInstance().setLocale(Locale.FRANCE);
    assertEquals("pluie", Phenomenon.RAIN.toString());

    Messages.getInstance().setLocale(Locale.ENGLISH);
    assertEquals("rain", Phenomenon.RAIN.toString());
  }
}
