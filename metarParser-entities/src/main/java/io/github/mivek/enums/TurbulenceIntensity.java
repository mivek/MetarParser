package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import java.util.Arrays;

/**
 * Intensity level of the Turbulence.
 * @author Jean-Kevin KPADEY
 */
public enum TurbulenceIntensity {
  /** None. */
  NONE("0"),
  /** Light turbulence. */
  LIGHT("1"),
  /** Moderate turbulence in clear air, occasional. */
  MODERATE_CLEAR_AIR_OCCASIONAL("2"),
  /** Moderate turbulence in clear air, frequent. */
  MODERATE_CLEAR_AIR_FREQUENT("3"),
  /** Moderate turbulence in cloud, occasional. */
  MODERATE_CLOUD_OCCASIONAL("4"),
  /** Moderate turbulence in cloud, frequent. */
  MODERATE_CLOUD_FREQUENT("5"),
  /** Severe turbulence in clear air, occasional. */
  SEVERE_CLEAR_AIR_OCCASIONAL("6"),
  /** Severe turbulence in clear air, frequent. */
  SEVERE_CLEAR_AIR_FREQUENT("7"),
  /** Severe turbulence in cloud, occasional. */
  SEVERE_CLOUD_OCCASIONAL("8"),
  /** Severe turbulence in cloud, frequent. */
  SEVERE_CLOUD_FREQUENT("9"),
  /** Extreme turbulence. */
  EXTREME("X");

  /** Shortcut of the intensity. */
  private final String shortcut;

  /**
   * Constructor.
   * @param shortcut The intensity's shortcut.
   */
  TurbulenceIntensity(final String shortcut) {
    this.shortcut = shortcut;
  }

  @Override
  public String toString() {
    return Messages.getInstance().getString("TurbulenceIntensity." + shortcut);
  }

  /**
   * Return the Intensity corresponding to the given input.
   * @param input The input to test.
   * @return The corresponding RunwayInfoIndicator or null
   */
  public static TurbulenceIntensity get(final String input) {
    return Arrays.stream(TurbulenceIntensity.values())
        .filter(intensity -> intensity.shortcut.equals(input))
        .findFirst()
        .orElse(null);
  }
}
