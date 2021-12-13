package io.github.mivek.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;
import org.junit.jupiter.api.Test;

/** @author mivek */
public class CloudTypeTest {

  @Test
  public void testToStringWithMultipleLocale() {
    Messages.getInstance().setLocale(Locale.FRANCE);
    assertEquals("Cirrocumulus", CloudType.CC.toString());

    Messages.getInstance().setLocale(Locale.ENGLISH);
    assertEquals("CirroCumulus", CloudType.CC.toString());
  }
}
