package io.github.mivek.factory;

import io.github.mivek.model.trend.TafTrend;
import io.github.mivek.model.trend.validity.AbstractMetarTrendTime;
import io.github.mivek.model.trend.validity.AbstractValidity;
import io.github.mivek.parser.AbstractTAFTrendParser;
import java.util.Optional;

/**
 * @author Jean-Kevin KPADEY
 * Provides a factory.
 */
public final class FactoryProvider {

  /**
   * Private constructor.
   */
  private FactoryProvider() { }

  /**
   * @return a new MetarTrendFactory.
   */
  public static AbstractFactory<AbstractMetarTrendTime> getMetarTrendTimeFactory() {
    return new MetarTrendTimeFactory();
  }

  public static AbstractFactory<Optional<TafTrend>> getTAFTrendFactory() {
    return new TAFTrendFactory();
  }

  public static AbstractFactory<AbstractTAFTrendParser<? extends AbstractValidity>> getTrendParser() {
    return new TafTrendParserFactory();
  }
}
