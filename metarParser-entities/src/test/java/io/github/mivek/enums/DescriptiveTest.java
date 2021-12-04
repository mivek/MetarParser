package io.github.mivek.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;
import org.junit.jupiter.api.Test;

/** @author mivek */
public class DescriptiveTest {

  @Test
  public void testToStringWithMultipleLocale() {
    Messages.getInstance().setLocale(Locale.FRANCE);
    assertEquals("averses de", Descriptive.SHOWERS.toString());

    Messages.getInstance().setLocale(Locale.ENGLISH);
    assertEquals("showers of", Descriptive.SHOWERS.toString());
  }
}
