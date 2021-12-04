package io.github.mivek.command.remark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HourlyPrecipitationAmountCommandTest {

  @BeforeEach
  public void setup() {
    Messages.getInstance().setLocale(Locale.ENGLISH);
  }

  @Test
  public void testExecute() {
    Command command = new HourlyPrecipitationAmountCommand();
    StringBuilder sb = new StringBuilder();
    assertEquals("AO1", command.execute("P0009 AO1", sb));
    MatcherAssert.assertThat(
        sb.toString(),
        CoreMatchers.containsString("9/100 of an inch of precipitation fell in the last hour"));
  }

  @Test
  public void testCanParse() {
    Command command = new HourlyPrecipitationAmountCommand();
    assertTrue(command.canParse("P0009"));
  }
}
