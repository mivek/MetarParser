package io.github.mivek.parser;

import io.github.mivek.command.common.CommonCommandSupplier;
import io.github.mivek.command.taf.TAFCommandSupplier;
import io.github.mivek.factory.FactoryProvider;
import io.github.mivek.model.trend.TafTrend;
import io.github.mivek.model.trend.validity.Validity;

/**
 * Parser for {@link TafTrend}.
 * @author Jean-Kevin KPADEY
 */
public final class TrendValididyParser extends AbstractTAFTrendParser<Validity> {
  /**
   * DI constructor.
   * @param commonCommandSupplier The common command supplier.
   * @param remarkParser The remark Parser instance.
   * @param tafCommandSupplier The taf command supplier.
   */
  TrendValididyParser(
      final CommonCommandSupplier commonCommandSupplier,
      final RemarkParser remarkParser, final TAFCommandSupplier tafCommandSupplier) {
    super(commonCommandSupplier, remarkParser, tafCommandSupplier);
  }

  /**
   * Default constructor.
   */
  public TrendValididyParser() {
    this(new CommonCommandSupplier(), new RemarkParser(), new TAFCommandSupplier());
  }

  /**
   * @deprecated Use constructor instead.
   * @return The TrendValidityParser instance.
   */
  @Deprecated(forRemoval = true, since = "2.19.0")
  public static TrendValididyParser getInstance() {
    return new TrendValididyParser();
  }

  @Override
  public TafTrend parse(final String[] code) {
    TafTrend trend = FactoryProvider.getTAFTrendFactory().create(code[0]).orElseThrow(() -> new IllegalArgumentException(code[0]));
    updateTrend(1, code, trend);
    return trend;
  }
}
