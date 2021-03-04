package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Represents the percentage of the runway covered by the deposit.
 * @author mivek
 */
public enum DepositCoverage {
    NOT_REPORTED,
    LESS_10,
    FROM_11_TO_25,
    FROM_26_TO_50,
    FROM_51_TO_100;

    @Override
    public String toString() {
        return Messages.getInstance().getString("DepositCoverage." + name());
    }
}

