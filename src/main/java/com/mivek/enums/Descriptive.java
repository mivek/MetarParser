package com.mivek.enums;

import internationalization.Messages;

/**
 * Enumeration for descriptive. The first attribute is the code used in the
 * metar. The second attribute is the meaning of the code.
 * @author mivek
 */
public enum Descriptive {
    /**
     * Showers.
     */
    SHOWERS("SH", Messages.getInstance().getDescriptiveSH()), //$NON-NLS-1$
    /**
     * Shallow.
     */
    SHALLOW("MI", Messages.getInstance().getDescriptiveMI()), //$NON-NLS-1$
    /**
     * Patches.
     */
    PATCHES("BC", Messages.getInstance().getDescriptiveBC()), //$NON-NLS-1$
    /**
     * Partial.
     */
    PARTIAL("PR", Messages.getInstance().getDescriptivePR()), //$NON-NLS-1$
    /**
     * Low drifting.
     */
    DRIFTING("DR", Messages.getInstance().getDescriptiveDR()), //$NON-NLS-1$
    /**
     * Thunderstorm.
     */
    THUNDERSTORM("TS", Messages.getInstance().getDescriptiveTS()), //$NON-NLS-1$
    /**
     * blowing.
     */
    BLOWING("BL", Messages.getInstance().getDescriptiveBL()), //$NON-NLS-1$
    /**
     * Freezing.
     */
    FREEZING("FZ", Messages.getInstance().getDescriptiveFZ()); //$NON-NLS-1$

    /**
     * Shortcut of the descriptive.
     */
    private String fShortcut = ""; //$NON-NLS-1$
    /**
     * Meaning of the descriptive.
     */
    private String fName = ""; //$NON-NLS-1$

    /**
     * Connstructor.
     * @param pShortcut
     * A string for the shorcut.
     * @param pName
     * a string for the meaning.
     */
    Descriptive(final String pShortcut, final String pName) {
        fShortcut = pShortcut;
        fName = pName;
    }

    @Override
    public String toString() {
        return fName;
    }

    /**
     * return shortcut.
     * @return string.
     */
    public String getShortcut() {
        return fShortcut;
    }
}
