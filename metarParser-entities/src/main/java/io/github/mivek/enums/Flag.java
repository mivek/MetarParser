package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Enumeration class for WeatherCode flags.
 *
 * @author Jean-Kevin KPADEY
 */
public enum Flag {
  /** Amended. */
  AMD,
  /** Auto. */
  AUTO,
  /** Cancelled. */
  CNL,
  /** Corrected. */
  COR,
  /** Null. */
  NIL;

  @Override
  public String toString() {
    return Messages.getInstance().getString("Flag." + name());
  }
}
