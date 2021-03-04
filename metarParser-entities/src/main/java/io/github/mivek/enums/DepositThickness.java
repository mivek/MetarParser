package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Represent the thickness of the deposit on the runway
 * @author mivek
 */
public enum DepositThickness {
    NOT_REPORTED,
    LESS_1_MM,
    /** 10 cm. */
    THICKNESS_10,
    /** 15 cm. */
    THICKNESS_15,
    /** 20 cm. */
    THICKNESS_20,
    /** 25 cm. */
    THICKNESS_25,
    /** 30 cm. */
    THICKNESS_30,
    /** 35 cm. */
    THICKNESS_35,
    /** 40 cm or more. */
    THICKNESS_40,
    CLOSED;

    @Override
    public String toString() {
        return Messages.getInstance().getString("DepositThickness." + name());
    }
}

