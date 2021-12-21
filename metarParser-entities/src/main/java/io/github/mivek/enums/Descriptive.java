package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Enumeration for descriptive. The first attribute is the code used in the
 * metar. The second attribute is the meaning of the code.
 *
 * @author mivek
 */
public enum Descriptive {
    /** Showers. */
    SHOWERS("SH"),
    /** Shallow. */
    SHALLOW("MI"),
    /** Patches. */
    PATCHES("BC"),
    /** Partial. */
    PARTIAL("PR"),
    /** Low drifting. */
    DRIFTING("DR"),
    /** Thunderstorm. */
    THUNDERSTORM("TS"),
    /** blowing. */
    BLOWING("BL"),
    /** Freezing. */
    FREEZING("FZ");

    /** The descriptive's shortcut. */
    private final String shortcut;

    /**
     * Constructor.
     * @param shortcut the shortcut of the descriptive.
     */
    Descriptive(final String shortcut) {
        this.shortcut = shortcut;
    }

    /**
     * @return The shortcut.
     */
    public String getShortcut() {
        return this.shortcut;
    }

    @Override
    public String toString() {
        return Messages.getInstance().getString("Descriptive." + shortcut);
    }
}
