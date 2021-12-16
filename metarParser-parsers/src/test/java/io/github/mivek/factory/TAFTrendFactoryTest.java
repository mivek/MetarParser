package io.github.mivek.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.trend.TafTrend;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class TAFTrendFactoryTest {

  @Test
  void testCreateWithBECMG() {
    Optional<TafTrend> trend = new TAFTrendFactory().create("BECMG");
    assertTrue(trend.isPresent());
    assertEquals(WeatherChangeType.BECMG, trend.get().getType());
  }
  @Test
  void testCreateWithINTER() {
    Optional<TafTrend> trend = new TAFTrendFactory().create("INTER");
    assertTrue(trend.isPresent());
    assertEquals(WeatherChangeType.INTER, trend.get().getType());
  }

  @Test
  void testCreateInvalid() {
    Optional<TafTrend> trend = new TAFTrendFactory().create("QWEERTY");
    assertTrue(trend.isEmpty());
  }
}
