package io.github.mivek.command.taf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mivek.enums.IcingIntensity;
import io.github.mivek.model.TAF;
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

    assertNotNull(taf.getIcing());
    assertEquals(IcingIntensity.LIGHT_RIME_ICING_CLOUD, taf.getIcing().getIntensity());
    assertEquals(3000, taf.getIcing().getBaseHeight());
    assertEquals(4000, taf.getIcing().getDepth());
  }

  @Test
  void testCanParse() {
    String input = "620304";

    assertTrue(command.canParse(input));
  }
}
