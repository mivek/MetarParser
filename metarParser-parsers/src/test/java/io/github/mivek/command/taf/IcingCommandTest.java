package io.github.mivek.command.taf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mivek.enums.IcingIntensity;
import io.github.mivek.model.TAF;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class IcingCommandTest {

  private IcingCommand command;

  @BeforeEach
  void setUp() {
    command = new IcingCommand();
  }

  @Test
  void testExecute() {
    String input = "620304";
    TAF taf = new TAF();
    command.execute(taf, input);

    MatcherAssert.assertThat(taf.getIcings(), Matchers.not(Matchers.empty()));
    assertEquals(IcingIntensity.LIGHT_RIME_ICING_CLOUD, taf.getIcings().get(0).getIntensity());
    assertEquals(3000, taf.getIcings().get(0).getBaseHeight());
    assertEquals(4000, taf.getIcings().get(0).getDepth());
  }

  @Test
  void testCanParse() {
    String input = "620304";

    assertTrue(command.canParse(input));
  }
}
