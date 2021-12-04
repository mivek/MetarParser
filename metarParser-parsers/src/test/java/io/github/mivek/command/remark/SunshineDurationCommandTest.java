package io.github.mivek.command.remark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SunshineDurationCommandTest {

  @BeforeEach
  public void setup() {
    Messages.getInstance().setLocale(Locale.ENGLISH);
  }

  @Test
  public void testExecute() {
    Command command = new SunshineDurationCommand();
    StringBuilder sb = new StringBuilder();

    assertEquals("AO1", command.execute("98096 AO1", sb));
    MatcherAssert.assertThat(sb.toString(), CoreMatchers.containsString("96 minutes of sunshine"));
  }

  @Test
  public void testCanParse() {
    Command command = new SunshineDurationCommand();
    assertTrue(command.canParse("98096"));
  }
}
