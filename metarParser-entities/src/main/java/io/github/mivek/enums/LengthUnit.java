package io.github.mivek.enums;

import java.util.Arrays;

/**
 * Enumeration representing length units used in aviation weather reports.
 *
 * @author mivek
 */
public enum LengthUnit {
    /** Metric unit used by default in METAR runway visual range. */
    METERS("M"),
    /** Imperial unit occasionally used in US reports. */
    FEET("FT"),
    /** Statute miles unit used in US METAR visibility. */
    STATUTE_MILES("SM");

    /** Shortcut found in METAR token. */
    private final String shortcut;

    /**
     * Constructor.
     *
     * @param shortcut shorthand representation of the unit
     */
    LengthUnit(final String shortcut) {
        this.shortcut = shortcut;
    }

    @Override
    public String toString() {
        return shortcut;
    }

    /**
     * Returns the matching unit from a METAR token suffix.
     *
     * @param input token suffix to parse
     * @return the matching unit or {@code null} if unknown
     */
    public static LengthUnit get(final String input) {
        return Arrays.stream(LengthUnit.values())
                .filter(unit -> unit.shortcut.equals(input))
                .findFirst()
                .orElse(null);
    }
}

