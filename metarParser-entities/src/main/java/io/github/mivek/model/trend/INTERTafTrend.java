package io.github.mivek.model.trend;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.trend.validity.Validity;

/** @author Jean-Kevin KPADEY */
public class INTERTafTrend extends AbstractTafTrend<Validity> {
  /** Default constructor. */
  public INTERTafTrend() {
    super(WeatherChangeType.INTER);
  }
}
