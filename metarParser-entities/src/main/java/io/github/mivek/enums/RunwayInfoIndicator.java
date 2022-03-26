package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import java.util.Arrays;

/**
 * Enumeration to represent the indicator of a RunwayTrend.
 * @author Jean-Kevin KPADEY
 */
public enum RunwayInfoIndicator {

  LESS_THAN("M"),
  MORE_THAN("P");

  private final String shortcut;

  RunwayInfoIndicator(final String shortcut) {
    this.shortcut = shortcut;
  }

  @Override
  public String toString() {
    return Messages.getInstance().getString("Indicator." + shortcut);
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
