package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Represents the percentage of the runway covered by the deposit.
 *
 * @author mivek
 */
public enum DepositCoverage {
  /** Not reported: / . */
  NOT_REPORTED,
  /** less than 10%: 1. */
  LESS_10,
  /** 11% to 25%: 2. */
  FROM_11_TO_25,
  /** 26% to 50: 3. */
  FROM_26_TO_50,
  /** 51 to 100%: 4. */
  FROM_51_TO_100;

  @Override
  public String toString() {
    return Messages.getInstance().getString("DepositCoverage." + name());
  }
}
