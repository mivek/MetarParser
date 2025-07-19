package io.github.mivek.parser;

import io.github.mivek.command.common.CommonCommandSupplier;
import io.github.mivek.command.taf.TAFCommandSupplier;
import io.github.mivek.model.trend.AbstractTafTrend;
import io.github.mivek.model.trend.FMTafTrend;
import io.github.mivek.model.trend.validity.BeginningValidity;

/**
 * Trend parser for {@link FMTafTrend}.
 * @author Jean-Kevin KPADEY
 */
public final class FMTrendParser extends AbstractTAFTrendParser<BeginningValidity> {

  /**
   * Public package constructor.
   * @param commonCommandSupplier The common command Supplier
   * @param remarkParser The remarkParser
   * @param tafCommandSupplier The taf command supplier
   */
  public FMTrendParser(
      final CommonCommandSupplier commonCommandSupplier,
      final RemarkParser remarkParser, final TAFCommandSupplier tafCommandSupplier) {
    super(commonCommandSupplier, remarkParser, tafCommandSupplier);
  }

  /**
   * DI constructor.
   */
  public FMTrendParser() {
    this(new CommonCommandSupplier(), new RemarkParser(), new TAFCommandSupplier());
  }

  @Override
  public AbstractTafTrend<BeginningValidity> parse(final String[] code) {
    AbstractTafTrend<BeginningValidity> trend = new FMTafTrend();
    trend.setValidity(parseBasicValidity(code[0]));
    for (int k = 1; k < code.length; k++) {
      generalParse(trend, code[k]);
    }
    return trend;
  }

  /**
   * @return The singleton instance.
   * @deprecated Use the constructor instead.
   */
  @Deprecated(forRemoval = true, since = "2.19.0")
  public static FMTrendParser getInstance() {
    return new FMTrendParser();
  }

  /**
   * Parses the validity of a {@link FMTafTrend} object.
   *
   * @param validityString the string to parse
   * @return a {@link BeginningValidity} object.
   */
  BeginningValidity parseBasicValidity(final String validityString) {
    BeginningValidity validity = new BeginningValidity();
    validity.setStartDay(Integer.parseInt(validityString.substring(2, 4)));
    validity.setStartHour(Integer.parseInt(validityString.substring(4, 6)));
    validity.setStartMinutes(Integer.parseInt(validityString.substring(6, 8)));
    return validity;
  }
}
