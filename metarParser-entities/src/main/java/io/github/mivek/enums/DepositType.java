package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Represents the type of deposit on a runway.
 * @author mivek
 */
public enum DepositType {

    NOT_REPORTED,
    CLEAR_DRY,
    DAMP,
    WET_WATER_PATCHES,
    RIME_FROST_COVERED,
    DRY_SNOW,
    WET_SNOW,
    SLUSH,
    ICE,
    COMPACTED_SNOW,
    FROZEN_RIDGES;

    @Override
    public String toString() {
        return Messages.getInstance().getString("DepositType." + name());
    }
}

