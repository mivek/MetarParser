package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import java.util.Arrays;

/**
 * Intensity level for the Icing.
 * @author Jean-Kevin KPADEY
 */
public enum IcingIntensity {
  /** Trace Icing or None. */
  NONE("0"),
  /** Light Mixed Icing. */
  LIGHT("1"),
  /** Light Rime Icing In Cloud. */
  LIGHT_RIME_ICING_CLOUD("2"),
  /** Light Clear Icing In Precipitation. */
  LIGHT_CLEAR_ICING_PRECIPITATION("3"),
  /** Moderate Mixed Icing. */
  MODERATE_MIXED_ICING("4"),
  /** Moderate Rime Icing In Cloud. */
  MODERATE_RIME_ICING_CLOUD("5"),
  /** Moderate Clear Icing In Precipitation. */
  MODERATE_CLEAR_ICING_PRECIPITATION("6"),
  /** Severe Mixed Icing. */
  SEVERE_MIXED_ICING("7"),
  /** Severe Rime Icing In Cloud. */
  SEVERE_RIME_ICING_CLOUD("8"),
  /** Severe Clear Icing In Precipitation. */
  SEVERE_CLEAR_ICING_PRECIPITATION("9");

  /** Shortcut of the intensity. */
  private final String shortcut;

  /**
   * Constructor.
   * @param shortcut The intensity's shortcut.
   */
  IcingIntensity(final String shortcut) {
    this.shortcut = shortcut;
  }

  @Override
  public String toString() {
    return Messages.getInstance().getString("IcingIntensity." + shortcut);
  }

  /**
   * Return the Intensity corresponding to the given input.
   * @param input The input to test.
   * @return The corresponding RunwayInfoIndicator or null
   */
  public static IcingIntensity get(final String input) {
    return Arrays.stream(IcingIntensity.values())
        .filter(intensity -> intensity.shortcut.equals(input))
        .findFirst()
        .orElse(null);
  }
}
