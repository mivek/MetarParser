package io.github.mivek.parser;

import io.github.mivek.command.common.CommonCommandSupplier;
import io.github.mivek.command.taf.TAFCommandSupplier;
import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.model.trend.TafProbTrend;
import io.github.mivek.model.trend.validity.Validity;

/**
 * Represents a TAF trend with probability.
 * @author Jean-Kevin KPADEY
 */
public final class ProbTrendParser extends AbstractTAFTrendParser<Validity> {

  /**
   * Public DI constructor.
   * @param commonCommandSupplier The commonCommon supplier
   * @param remarkParser The instance of RemarkParser.
   * @param tafCommandSupplier The taf command supplier.
   */
  public ProbTrendParser(
      final CommonCommandSupplier commonCommandSupplier,
      final RemarkParser remarkParser, final TAFCommandSupplier tafCommandSupplier) {
    super(commonCommandSupplier, remarkParser, tafCommandSupplier);
  }

  /**
   * Public default constructor.
   */
  public ProbTrendParser() {
    this(new CommonCommandSupplier(), new RemarkParser(), new TAFCommandSupplier());
  }

  @Override
  public TafProbTrend parse(final String[] code) {
    TafProbTrend trend = new TafProbTrend(WeatherChangeType.TEMPO);
    int startIndex = 1;
    if (code[0].startsWith("PROB")) {
      int probability = parseProbability(code[0]);
      if (code.length > 1 && code[1].equals("TEMPO")) {
        startIndex = 2;
      } else {
        trend = new TafProbTrend(WeatherChangeType.PROB);
      }
      trend.setProbability(probability);
    }
    updateTrend(startIndex, code, trend);
    return trend;
  }

  /**
   * @return The ProbTrendParser instance.
   * @deprecated Use the constructor instead.
   */
  @Deprecated(forRemoval = true, since = "2.19.0")
  public static ProbTrendParser getInstance() {
    return new ProbTrendParser();
  }

  /**
   * parses the probability out of PROB.
   *
   * @param part the string to parse.
   * @return probability of the trend.
   */
  private int parseProbability(final String part) {
    return Integer.parseInt(part.substring(4));
  }
}
