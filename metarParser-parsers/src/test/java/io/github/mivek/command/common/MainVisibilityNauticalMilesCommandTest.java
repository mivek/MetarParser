package io.github.mivek.command.common;

import static org.junit.jupiter.api.Assertions.*;

import io.github.mivek.enums.LengthUnit;
import io.github.mivek.model.Metar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Jean-Kevin KPADEY
 */
class MainVisibilityNauticalMilesCommandTest {

  private MainVisibilityNauticalMilesCommand command;

  @BeforeEach
  void setUp() {
    command = new MainVisibilityNauticalMilesCommand();
  }

  @Test
  void testCanParse() {
    assertTrue(command.canParse("P6SM"));
    assertTrue(command.canParse("M6SM"));
    assertTrue(command.canParse("1 1/2SM"));
    assertTrue(command.canParse("3/4SM"));
    assertTrue(command.canParse("6SM"));
  }

  @ParameterizedTest
  @CsvSource({
    "P6SM, P6",
    "M6SM, M6",
    "1 1/2SM, 1 1/2",
    "3/4SM, 3/4",
    "6SM, 6"
  })
  void testExecute(final String token, final String expectedVisibility) {
    Metar m = new Metar();
    assertTrue(command.execute(m, token));
    assertNotNull(m.getVisibility());
    assertEquals(expectedVisibility, m.getVisibility().getMainVisibility());
    assertEquals(LengthUnit.STATUTE_MILES, m.getVisibility().getUnit());
  }

  @Test
  void testCanParseSolidus() {
    assertFalse(command.canParse("////"));
  }
}
