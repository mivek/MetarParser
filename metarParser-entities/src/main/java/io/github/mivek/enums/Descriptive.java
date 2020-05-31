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
    SHOWERS("SH"), //$NON-NLS-1$
    /** Shallow. */
    SHALLOW("MI"), //$NON-NLS-1$
    /** Patches. */
    PATCHES("BC"), //$NON-NLS-1$
    /** Partial. */
    PARTIAL("PR"), //$NON-NLS-1$
    /** Low drifting. */
    DRIFTING("DR"), //$NON-NLS-1$
    /** Thunderstorm. */
    THUNDERSTORM("TS"), //$NON-NLS-1$
    /** blowing. */
    BLOWING("BL"), //$NON-NLS-1$
    /** Freezing. */
    FREEZING("FZ"); //$NON-NLS-1$

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
