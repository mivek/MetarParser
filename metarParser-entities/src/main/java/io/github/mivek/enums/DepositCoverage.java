package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;

/**
 * Represents the percentage of the runway covered by the deposit.
 * @author mivek
 */
public enum DepositCoverage {
    /** Not reported: / . */
    NOT_REPORTED,
    /** less than 10%: 1. */
    LESS_10,
    /** 11% to 25%: 2. */
    FROM_11_TO_25,
    /** 26% to 50: 3. */
    FROM_26_TO_50,
    /** 51 to 100%: 4. */
    FROM_51_TO_100;

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
        return Messages.getInstance().getString(locale, "DepositCoverage." + name());
    }
}

