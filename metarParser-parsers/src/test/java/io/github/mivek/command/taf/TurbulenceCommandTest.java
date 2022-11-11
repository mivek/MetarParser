package io.github.mivek.command.taf;

import static org.junit.jupiter.api.Assertions.*;

import io.github.mivek.enums.TurbulenceIntensity;
import io.github.mivek.model.TAF;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
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
    MatcherAssert.assertThat(taf.getTurbulences(), Matchers.not(Matchers.empty()));
    assertEquals(TurbulenceIntensity.MODERATE_CLEAR_AIR_OCCASIONAL, taf.getTurbulences().get(0).getIntensity());
    assertEquals(100, taf.getTurbulences().get(0).getBaseHeight());
    assertEquals(4000, taf.getTurbulences().get(0).getDepth());
  }
  @Test
  void testCanParse() {
    String input = "520004";

    assertTrue(command.canParse(input));
  }

}
