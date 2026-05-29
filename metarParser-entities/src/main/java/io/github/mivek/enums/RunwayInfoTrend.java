package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import java.util.Arrays;
import java.util.Locale;

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

  /**
   * Returns the localized string using the JVM default locale.
   * @return the translated string.
   */
  @Override
  public String toString() {
    return toString(Locale.getDefault());
  }

  /**
   * Returns the localized string for the given locale.
   * @param locale the locale to use.
   * @return the translated string.
   */
  public String toString(final Locale locale) {
    return Messages.getInstance().getString(locale, "Converter." + key);
  }

  /**
   * Returns a trend given a token.
   * @param input String representing a trend.
   * @return The RunwayInfoTrend object.
   */
  public static RunwayInfoTrend get(final String input) {
    return Arrays.stream(RunwayInfoTrend.values())
        .filter(trend -> trend.shortcut.equals(input))
        .findFirst().orElse(null);
  }
}
