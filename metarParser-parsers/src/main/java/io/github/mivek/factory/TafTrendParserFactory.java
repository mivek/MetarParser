package io.github.mivek.factory;

import io.github.mivek.model.trend.validity.AbstractValidity;
import io.github.mivek.parser.AbstractTAFTrendParser;
import io.github.mivek.parser.FMTrendParser;
import io.github.mivek.parser.ProbTrendParser;
import io.github.mivek.parser.TrendValididyParser;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jean-Kevin KPADEY
 */
public final class TafTrendParserFactory implements AbstractFactory<AbstractTAFTrendParser<? extends AbstractValidity>> {

  /** Map returning the corresponding trend parser depending on the trend. */
  private final Map<String, AbstractTAFTrendParser<? extends AbstractValidity>> trendParserMap;

  /**
   * Default constructor.
   */
  TafTrendParserFactory() {
    trendParserMap = new HashMap<>();
    trendParserMap.put("TE", new ProbTrendParser());
    trendParserMap.put("BE", new TrendValididyParser());
    trendParserMap.put("IN", new TrendValididyParser());
    trendParserMap.put("PR", new ProbTrendParser());
    trendParserMap.put("FM", new FMTrendParser());
  }

  @Override
  public AbstractTAFTrendParser<? extends AbstractValidity> create(final String discriminant) {
    return trendParserMap.get(discriminant);
  }
}
