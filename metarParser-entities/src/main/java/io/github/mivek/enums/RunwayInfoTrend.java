package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import java.util.Arrays;

/**
 * This class contains the possible trends for a RunwayInfo.
 * @author Jean-Kevin KPADEY
 */
public enum RunwayInfoTrend {
  /** Uprising trend. */
  UPRISING("U", "U"),
  /** Decreasing trend. */
  DECREASING("D", "D"),
  /** No significant change trend. */
  NO_SIGNIFICANT_CHANGE("N", "NSC");

  /** Shortcut of the trend. */
  private final String shortcut;
  /** Translation key. */
  private final String key;

  /**
   * Constructor.
   * @param shortcut in metar code.
   * @param key for translation.
   */
  RunwayInfoTrend(final String shortcut, final String key) {
    this.shortcut = shortcut;
    this.key = key;
  }

  @Override
  public String toString() {
    return Messages.getInstance().getString("Converter." + key);
  }

  public static RunwayInfoTrend get(final String input) {
    return Arrays.stream(RunwayInfoTrend.values())
        .filter(trend -> trend.shortcut.equals(input))
        .findFirst().orElse(null);
  }
}
