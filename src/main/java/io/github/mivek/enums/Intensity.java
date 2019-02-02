package io.github.mivek.enums;

import io.github.mivekinternationalization.Messages;

/**
 * Enumeration for indicator.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 * @author mivek
 */
public enum Intensity {
    /**
     * Light intensity.
     */
    LIGHT("-", Messages.getInstance().getIntensityLight()), //$NON-NLS-1$
    /**
     * Heavy intensity.
     */
    HEAVY("+", Messages.getInstance().getIntensityHeavy()), //$NON-NLS-1$
    /**
     * In vicinity.
     */
    IN_VICINITY("VC", Messages.getInstance().getIntensityVC()); //$NON-NLS-1$

    /**
     * The shortcut of the intensity.
     */
    private String fShortcut = ""; //$NON-NLS-1$
    /**
     * The meaning of the intensity.
     */
    private String fName = ""; //$NON-NLS-1$

    /**
     * Constructor.
     * @param pShortcut
     * A String for the shortcut.
     * @param pName
     * A string for the meaning.
     */
    Intensity(final String pShortcut, final String pName) {
        fShortcut = pShortcut;
        fName = pName;
    }

    @Override
    public String toString() {
        return fName;
    }

    /**
     * Returns shortcut.
     * @return string.
     */
    public String getShortcut() {
        return fShortcut;
    }

    /**
     * Returns the enum with the same shortcut than the value.
     * @param pValue
     * String of the intensity searched.
     * @return a intensity with the same shortcut.
     * @throws IllegalArgumentException
     * error if not found.
     */
    public static Intensity getEnum(final String pValue) throws IllegalArgumentException {
        for (Intensity v : values()) {
            if (v.getShortcut().equalsIgnoreCase(pValue)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
