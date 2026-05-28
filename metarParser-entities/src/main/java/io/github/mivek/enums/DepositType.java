package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import java.util.Locale;

/**
 * Represents the type of deposit on a runway.
 * @author mivek
 */
public enum DepositType {
    /** Not reported: /.  */
    NOT_REPORTED,
    /** Clear and dry: 0. */
    CLEAR_DRY,
    /** Damp: 1. */
    DAMP,
    /** Wet or water patches: 2. */
    WET_WATER_PATCHES,
    /** Rime frost covered: 3. */
    RIME_FROST_COVERED,
    /** Dry snow: 4. */
    DRY_SNOW,
    /** Wet snow: 5. */
    WET_SNOW,
    /** Slush: 6. */
    SLUSH,
    /** Ice: 7. */
    ICE,
    /** Compacted snow: 8. */
    COMPACTED_SNOW,
    /** Frozen ridges: 9. */
    FROZEN_RIDGES;

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
        return Messages.getInstance().getString(locale, "DepositType." + name());
    }
}

