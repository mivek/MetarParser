package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Represents the breaking capacity on the runway.
 * @author mivek
 */
public enum DepositBrakingCapacity {
    /** Not reported: // */
    NOT_REPORTED,
    /** 91. */
    POOR,
    /** 92. */
    MEDIUM_POOR,
    /** 93. */
    MEDIUM,
    /** 94. */
    MEDIUM_GOOD,
    /** 95. */
    GOOD,
    /** 99. */
    UNRELIABLE;

    @Override
    public String toString() {
        return Messages.getInstance().getString("DepositBrakingCapacity." + name());
    }
}

