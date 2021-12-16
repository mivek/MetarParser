package io.github.mivek.factory;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.trend.TafTrend;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Jean-Kevin KPADEY
 */
public class TAFTrendFactory implements AbstractFactory<Optional<TafTrend>> {
  /** Map between String and the corresponding TAFTrend. */
  private final Map<String, TafTrend> trendMap;

  TAFTrendFactory() {
    trendMap = new HashMap<>();
    trendMap.put("BECMG", new TafTrend(WeatherChangeType.BECMG));
    trendMap.put("INTER", new TafTrend(WeatherChangeType.INTER));
  }

  @Override
  public final Optional<TafTrend> create(final String discriminant) {
    return Optional.ofNullable(trendMap.get(discriminant));
  }
}
