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
  private static final Map<String, AbstractMetarTrendTime> TREND_TIME_MAP = new HashMap<>();

  static {
    TREND_TIME_MAP.put("AT", new ATTime());
    TREND_TIME_MAP.put("FM", new FMTime());
    TREND_TIME_MAP.put("TL", new TLTime());
  }

  @Override
  public AbstractMetarTrendTime create(final String discriminant) {
    return TREND_TIME_MAP.get(discriminant);
  }
}
