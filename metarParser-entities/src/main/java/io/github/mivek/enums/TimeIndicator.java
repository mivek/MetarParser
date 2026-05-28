package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;

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

    /**
     * Returns the localized string using the JVM default locale.
     * @return the translated string.
     */
    @Override
    public String toString() {
        return toString(Locale.getDefault());
    }

    /**
     * Returns the localized string for the given locale.
     * @param locale the locale to use.
     * @return the translated string.
     */
    public String toString(final Locale locale) {
        return Messages.getInstance().getString(locale, "TimeIndicator." + getShortcut());
    }
}
