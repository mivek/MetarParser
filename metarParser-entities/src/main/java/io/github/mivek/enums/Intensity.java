package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Enumeration for indicator.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 *
 * @author mivek
 */
public enum Intensity {
    /** Light intensity. */
    LIGHT("-", Messages.getInstance().getString("Intensity.-")), //$NON-NLS-1$
    /** Heavy intensity. */
    HEAVY("+", Messages.getInstance().getString("Intensity.+")), //$NON-NLS-1$
    /** In vicinity. */
    IN_VICINITY("VC", Messages.getInstance().getString("Intensity.VC")); //$NON-NLS-1$

    /** The shortcut of the intensity. */
    private final String shortcut; //$NON-NLS-1$
    /** The meaning of the intensity. */
    private final String name; //$NON-NLS-1$

    /**
     * Constructor.
     *
     * @param pShortcut A String for the shortcut.
     * @param pName     A string for the meaning.
     */
    Intensity(final String pShortcut, final String pName) {
        shortcut = pShortcut;
        name = pName;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns shortcut.
     *
     * @return string.
     */
    public String getShortcut() {
        return shortcut;
    }

    /**
     * Returns the enum with the same shortcut than the value.
     *
     * @param pValue String of the intensity searched.
     * @return a intensity with the same shortcut.
     * @throws IllegalArgumentException error if not found.
     */
    public static Intensity getEnum(final String pValue) {
        for (Intensity v : values()) {
            if (v.getShortcut().equalsIgnoreCase(pValue)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
