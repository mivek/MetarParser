package io.github.mivek.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;
import org.junit.jupiter.api.Test;

/** @author mivek */
public class CloudQuantityTest {

  @Test
  public void testToStringMultipleLocale() {
    Messages.getInstance().setLocale(Locale.FRANCE);
    assertEquals("peu", CloudQuantity.FEW.toString());

    Messages.getInstance().setLocale(Locale.ENGLISH);
    assertEquals("few", CloudQuantity.FEW.toString());
  }
}
