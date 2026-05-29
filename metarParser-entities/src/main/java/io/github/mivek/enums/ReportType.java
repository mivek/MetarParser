package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;

/**
 * Enumeration class for weather code report types.
 *
 * @author mivek
 */
public enum ReportType {
    /** Routine report. */
    METAR,
    /** Special report. */
    SPECI;

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
        return Messages.getInstance().getString(locale, "ReportType." + name());
    }
}
