package io.github.mivek.parser;

import io.github.mivek.command.common.CommonCommandSupplier;
import io.github.mivek.factory.FactoryProvider;
import io.github.mivek.model.trend.TafTrend;
import io.github.mivek.model.trend.validity.Validity;

/**
 * Parser for {@link TafTrend}.
 * @author Jean-Kevin KPADEY
 */
public final class TrendValididyParser extends AbstractTAFTrendParser<Validity> {
  /** Instance of the class. */
  private static final TrendValididyParser INSTANCE = new TrendValididyParser();

  /**
   * Default constructor.
   * @param commonCommandSupplier The common command supplier.
   * @param remarkParser The remark Parser instance.
   */
  TrendValididyParser(
      final CommonCommandSupplier commonCommandSupplier,
      final RemarkParser remarkParser) {
    super(commonCommandSupplier, remarkParser);
  }

  /**
   * Private constructor.
   */
  private TrendValididyParser() {
    this(new CommonCommandSupplier(), RemarkParser.getInstance());
  }

  /**
   * @return The TrendValidityParser instance.
   */
  public static TrendValididyParser getInstance() {
    return INSTANCE;
  }

  @Override
  public TafTrend parse(final String[] code) {
    TafTrend trend = FactoryProvider.getTAFTrendFactory().create(code[0]).orElseThrow(() -> new IllegalArgumentException(code[0]));
    updateTrend(1, code, trend);
    return trend;
  }
}
