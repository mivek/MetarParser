package io.github.mivek.command.remark;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HourlyMaximumMinimumTemperatureCommandTest {

  @BeforeEach
  public void setup() {
    Messages.getInstance().setLocale(Locale.ENGLISH);
  }

  @Test
  public void testExecute() {
    HourlyMaximumMinimumTemperatureCommand command = new HourlyMaximumMinimumTemperatureCommand();
    StringBuilder sb = new StringBuilder();
    assertEquals("AO1", command.execute("401001015 AO1", sb));
    assertThat(
        sb.toString(),
        CoreMatchers.containsString(
            "24-hour maximum temperature of 10°C and 24-hour minimum temperature of -1.5°C"));
  }

  @Test
  public void testCanParse() {
    Command command = new HourlyMaximumMinimumTemperatureCommand();
    assertTrue(command.canParse("401001015"));
  }
}
