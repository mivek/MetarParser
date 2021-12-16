package io.github.mivek.factory;

import io.github.mivek.model.trend.validity.ATTime;
import io.github.mivek.model.trend.validity.AbstractMetarTrendTime;
import io.github.mivek.model.trend.validity.FMTime;
import io.github.mivek.model.trend.validity.TLTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jean-Kevin KPADEY
 */
public final class MetarTrendTimeFactory implements AbstractFactory<AbstractMetarTrendTime> {

  /**
   * Map matching the discriminator to a AbstractMetarTrendTime.
   */
  private final Map<String, AbstractMetarTrendTime> trendTimeMap;


  MetarTrendTimeFactory() {
    trendTimeMap = new HashMap<>();
    trendTimeMap.put("AT", new ATTime());
    trendTimeMap.put("FM", new FMTime());
    trendTimeMap.put("TL", new TLTime());
  }
  @Override
  public AbstractMetarTrendTime create(final String discriminant) {
    return trendTimeMap.get(discriminant);
  }
}
