package io.github.mivek.factory;

import static org.junit.jupiter.api.Assertions.*;

import io.github.mivek.model.trend.validity.ATTime;
import io.github.mivek.model.trend.validity.FMTime;
import io.github.mivek.model.trend.validity.TLTime;
import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class MetarTrendTimeFactoryTest {

  @Test
  void testCreateFM() {
    assertInstanceOf(FMTime.class, new MetarTrendTimeFactory().create("FM"));
  }

  @Test
  void testCreateAT() {
    assertInstanceOf(ATTime.class, new MetarTrendTimeFactory().create("AT"));
  }

  @Test
  void testCreateTL() {
    assertInstanceOf(TLTime.class, new MetarTrendTimeFactory().create("TL"));
  }

  @Test
  void testCreateInvalid() {
    assertNull(new MetarTrendTimeFactory().create("R"));
  }
}
