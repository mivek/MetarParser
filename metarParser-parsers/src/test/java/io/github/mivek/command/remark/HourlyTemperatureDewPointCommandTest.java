package io.github.mivek.command.remark;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HourlyTemperatureDewPointCommandTest {

  @BeforeEach
  public void setup() {
    Messages.getInstance().setLocale(Locale.ENGLISH);
  }

  @Test
  public void testExecute() {
    Command command = new HourlyTemperatureDewPointCommand();
    StringBuilder sb = new StringBuilder();
    assertThat(command.execute("T00261015", sb), Matchers.emptyString());
    assertThat(
        sb.toString(),
        Matchers.containsString("hourly temperature of 2.6°C and dew point of -1.5°C"));
  }

  @Test
  public void testExecuteOnlyTemperature() {
    Command command = new HourlyTemperatureDewPointCommand();
    StringBuilder sb = new StringBuilder();
    assertEquals("AO1", command.execute("T0026 AO1", sb));
    assertThat(sb.toString(), Matchers.containsString("hourly temperature of 2.6°C"));
    assertThat(sb.toString(), Matchers.not(Matchers.containsString("dew point of")));
  }

  @Test
  public void testCanParse() {
    Command command = new HourlyTemperatureDewPointCommand();
    assertTrue(command.canParse("T0026"));
  }
}
