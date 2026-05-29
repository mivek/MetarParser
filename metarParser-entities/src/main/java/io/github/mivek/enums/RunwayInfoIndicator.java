package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import java.util.Arrays;
import java.util.Locale;

/**
 * Enumeration to represent the indicator of a RunwayTrend.
 * @author Jean-Kevin KPADEY
 */
public enum RunwayInfoIndicator {
  /** Runway indicator for less than. */
  LESS_THAN("M"),
  /** Runway indicator for more than. */
  MORE_THAN("P");

  /** Indicator shortcut. */
  private final String shortcut;

  /**
   * Constructor.
   * @param shortcut The shortcut indicator.
   */
  RunwayInfoIndicator(final String shortcut) {
    this.shortcut = shortcut;
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
    return Messages.getInstance().getString(locale, "Indicator." + shortcut);
  }

  /**
   * Return the indicator corresponding to the given input.
   * @param input The input to test.
   * @return The corresponding RunwayInfoIndicator or null
   */
  public static RunwayInfoIndicator get(final String input) {
    return Arrays.stream(RunwayInfoIndicator.values())
        .filter(indicator -> indicator.shortcut.equals(input))
        .findFirst()
        .orElse(null);
  }
}
