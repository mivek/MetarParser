package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;

/**
 * Enumeration for cloud type. The first attribute is the code used in the
 * metar. The second attribute is the meaning of the code.
 *
 * @author mivek
 */
public enum CloudType {
    /** cumulonimbus. */
    CB,
    /** towering cumulus, cumulus congestus. */
    TCU,
    /** Cirrus. */
    CI,
    /** Cirrocumulus. */
    CC,
    /** Cirrostratus. */
    CS,
    /** Altocumulus. */
    AC,
    /** Stratus. */
    ST,
    /** Cumulus. */
    CU,
    /** Astrostratus. */
    AS,
    /** Nimbostratus. */
    NS,
    /** Stratocumulus. */
    SC;

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
        return Messages.getInstance().getString(locale, "CloudType." + name());
    }

}
