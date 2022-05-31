package io.github.mivek.command.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class MainVisibilityNauticalMilesCommandTest {

  @Test
  void testCanParse() {
    MainVisibilityNauticalMilesCommand command = new MainVisibilityNauticalMilesCommand();
    assertTrue(command.canParse("P6SM"));
    assertTrue(command.canParse("M6SM"));
    assertTrue(command.canParse("1 1/2SM"));
    assertTrue(command.canParse("3/4SM"));
    assertTrue(command.canParse("M6SM"));
  }
}
