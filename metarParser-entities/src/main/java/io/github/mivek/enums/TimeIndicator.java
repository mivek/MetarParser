package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Enumeration for time indicator in metar trends.
 *
 * @author mivek
 */
public enum TimeIndicator {
    /** The AT value of the metar trend. */
    AT("AT"),
    /** The FM value of the metar trend. */
    FM("FM"),
    /** The TL value of the metar trend. */
    TL("TL");

    /**
     * Shortcut of the time indicator.
     */
    private final String shortcut;

    /**
     * Constructor.
     *
     * @param shortcut the shortcut of the indicator.
     */
    TimeIndicator(final String shortcut) {
        this.shortcut = shortcut;
    }

    /**
     * @return the shortcut.
     */
    public String getShortcut() {
        return shortcut;
    }

    @Override
    public String toString() {
        return Messages.getInstance().getString("TimeIndicator." + getShortcut());
    }
}
