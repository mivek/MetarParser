package io.github.mivek.parser;

import static io.github.mivek.parser.AbstractWeatherCodeParser.RMK;

import io.github.mivek.command.common.CommonCommandSupplier;
import io.github.mivek.command.taf.Command;
import io.github.mivek.command.taf.TAFCommandSupplier;
import io.github.mivek.model.trend.AbstractTafTrend;
import io.github.mivek.model.trend.TafTrend;
import io.github.mivek.model.trend.validity.AbstractValidity;
import io.github.mivek.utils.Regex;

/**
 * @author Jean-Kevin KPADEY
 * @param <T> a concrete subclass of {@link AbstractValidity}
 */
public abstract class AbstractTAFTrendParser<T extends AbstractValidity> extends AbstractWeatherContainerParser<AbstractTafTrend<T>, String[]> {
  /** The taf command supplier. */
  private final TAFCommandSupplier tafCommandSupplier;
  /**
   * DI constructor.
   * @param commonCommandSupplier The common command supplier
   * @param remarkParser The remark parser instance.
   * @param tafCommandSupplier The taf command supplier.
   */
  AbstractTAFTrendParser(
      final CommonCommandSupplier commonCommandSupplier,
      final RemarkParser remarkParser, final TAFCommandSupplier tafCommandSupplier) {
    super(commonCommandSupplier, remarkParser);
    this.tafCommandSupplier = tafCommandSupplier;
  }

  /**
   * Iterates over the string array and build the abstractWeather change.
   *
   * @param index  the starting index of the array.
   * @param parts  the array of string.
   * @param trend the abstractWeatherChange to update.
   */
  void updateTrend(final int index, final String[] parts, final TafTrend trend) {
    for (int i = index; i < parts.length; i++) {
      Command command = tafCommandSupplier.get(parts[i]);
      if (command != null) {
        command.execute(trend, parts[i]);
      } else if (RMK.equals(parts[i])) {
        parseRMK(trend, parts, i);
        break;
      } else if (Regex.match(VALIDITY_REGEX, parts[i])) {
        trend.setValidity(parseValidity(parts[i]));
      } else {
        generalParse(trend, parts[i]);
      }
    }
  }
}
