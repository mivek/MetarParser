package io.github.mivek.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class RunwayInfoTrendTest {

  @Test
  void testSize() {
    assertEquals(3, RunwayInfoTrend.values().length);
  }

  @Test
  void testGetValid() {
    assertEquals(RunwayInfoTrend.UPRISING, RunwayInfoTrend.get("U"));
  }

  @Test
  void testGetInvalid() {
    assertNull(RunwayInfoTrend.get("T"));}
}
