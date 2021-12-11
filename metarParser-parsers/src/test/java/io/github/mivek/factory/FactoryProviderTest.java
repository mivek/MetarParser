package io.github.mivek.factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class FactoryProviderTest {

  @Test
  void testGetMetarTrendTimeFactory() {
    assertInstanceOf(MetarTrendTimeFactory.class, FactoryProvider.getMetarTrendTimeFactory());
  }
}
