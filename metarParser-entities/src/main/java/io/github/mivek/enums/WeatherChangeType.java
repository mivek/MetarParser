package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;

/**
 * @author mivek
 */
public enum WeatherChangeType {
    /** From enumeration. */
    FM,
    /** Becoming enumeration. */
    BECMG,
    /** Tempo enumeration. */
    TEMPO,
    /** INTER enumeration. */
    INTER,
    /** Probability change. */
    PROB;

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
        return Messages.getInstance().getString(locale, "WeatherChangeType." + name());
    }

}
