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

  /**
   * Map returning the corresponding trend parser depending on the trend.
   */
  private final Map<String, AbstractTAFTrendParser<? extends AbstractValidity>> trendParserMap;

  /**
   * Default constructor.
   */
  TafTrendParserFactory() {
    trendParserMap = new HashMap<>();
    trendParserMap.put("TE", ProbTrendParser.getInstance());
    trendParserMap.put("BE", TrendValididyParser.getInstance());
    trendParserMap.put("IN", TrendValididyParser.getInstance());
    trendParserMap.put("PR", ProbTrendParser.getInstance());
    trendParserMap.put("FM", FMTrendParser.getInstance());
  }

  @Override
  public AbstractTAFTrendParser<? extends AbstractValidity> create(final String discriminant) {
    return trendParserMap.get(discriminant);
  }
}
