package io.github.mivek.command.remark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrecipitationEndCommandTest {

  @BeforeEach
  public void setup() {
    Messages.getInstance().setLocale(Locale.ENGLISH);
  }

  @Test
  public void testExecute() {
    Command command = new PrecipitationEndCommand();
    StringBuilder sb = new StringBuilder();
    assertEquals("AO1", command.execute("RAE45 AO1", sb));
    MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("rain ending at :45"));
  }

  @Test
  public void testExecuteWithDescriptive() {
    Command command = new PrecipitationEndCommand();
    StringBuilder sb = new StringBuilder();
    MatcherAssert.assertThat(command.execute("SHRAE45", sb), Matchers.emptyString());
    MatcherAssert.assertThat(
        sb.toString(), CoreMatchers.containsString("showers of rain ending at :45"));
  }

  @Test
  public void testCanParse() {
    Command command = new PrecipitationEndCommand();
    assertTrue(command.canParse("SHRAE45"));
  }
}
