package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;

/**
 * Enumeration for cloud quantity.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 *
 * @author mivek
 */
public enum CloudQuantity {
    /** Clear. */
    CLR,
    /** Sky clear. */
    SKC,
    /** Few clouds. */
    FEW,
    /** Broken ceiling. */
    BKN,
    /** Scattered. */
    SCT,
    /** Overcast. */
    OVC,
    /** No significant cloud. */
    NSC,
    /** No cloud detected. */
    NCD;

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
        return Messages.getInstance().getString(locale, "CloudQuantity." + name());
    }
}
