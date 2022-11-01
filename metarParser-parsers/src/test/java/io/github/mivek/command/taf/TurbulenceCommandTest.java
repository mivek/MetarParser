package io.github.mivek.command.taf;

import static org.junit.jupiter.api.Assertions.*;

import io.github.mivek.enums.TurbulenceIntensity;
import io.github.mivek.model.TAF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class TurbulenceCommandTest {

  private TurbulenceCommand command;

  @BeforeEach
  void setUp() {
    command = new TurbulenceCommand();
  }
  @Test
  void testExecute() {
    TAF taf = new TAF();

    command.execute(taf, "520014");
    assertNotNull(taf.getTurbulence());
    assertEquals(TurbulenceIntensity.MODERATE_CLEAR_AIR_OCCASIONAL, taf.getTurbulence().getIntensity());
    assertEquals(100, taf.getTurbulence().getBaseHeight());
    assertEquals(4000, taf.getTurbulence().getDepth());
  }
  @Test
  void testCanParse() {
    String input = "520004";

    assertTrue(command.canParse(input));
  }

}
