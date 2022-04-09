package io.github.mivek.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class RunwayInfoIndicatorTest {

  @Test
  void testIndicatorMinus() {
    assertEquals(RunwayInfoIndicator.LESS_THAN, RunwayInfoIndicator.get("M"));
  }

  @Test
  void testIndicatorPlus() {
    assertEquals(RunwayInfoIndicator.MORE_THAN, RunwayInfoIndicator.get("P"));
  }

  @Test
  void testIndicatorNull() {
    assertNull(RunwayInfoIndicator.get(null));
  }

  @Test
  void testIndicatorInvalid() {
    assertNull(RunwayInfoIndicator.get("R"));
  }
}
