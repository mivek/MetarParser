package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Represents the breaking capacity on the runway.
 * @author mivek
 */
public enum DepositBrakingCapacity {
    NOT_REPORTED,
    POOR,
    MEDIUM_POOR,
    MEDIUM,
    MEDIUM_GOOD,
    GOOD,
    UNRELIABLE;

    @Override
    public String toString() {
        return Messages.getInstance().getString("DepositBrakingCapacity." + name());
    }
}

