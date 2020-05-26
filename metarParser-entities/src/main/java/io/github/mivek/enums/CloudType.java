package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Enumeration for cloud type. The first attribute is the code used in the
 * metar. The second attribute is the meaning of the code.
 *
 * @author mivek
 */
public enum CloudType {
    /** cumulonimbus. */
    CB("CB", Messages.getInstance().getString("CloudType.CB")), //$NON-NLS-1$
    /** towering cumulus, cumulus congestus. */
    TCU("TCU", Messages.getInstance().getString("CloudType.TCU")), //$NON-NLS-1$
    /** Cirrus. */
    CI("CI", Messages.getInstance().getString("CloudType.CI")),
    /** Cirrocumulus. */
    CC("CC", Messages.getInstance().getString("CloudType.CC")),
    /** Cirrostratus. */
    CS("CS", Messages.getInstance().getString("CloudType.CS")),
    /** Altocumulus. */
    AC("AC", Messages.getInstance().getString("CloudType.AC")),
    /** Stratus. */
    ST("ST", Messages.getInstance().getString("CloudType.ST")),
    /** Cumulus. */
    CU("CU", Messages.getInstance().getString("CloudType.CU")),
    /** Astrostratus. */
    AS("AS", Messages.getInstance().getString("CloudType.AS")),
    /** Nimbostratus. */
    NS("NS", Messages.getInstance().getString("CloudType.NS")),
    /** Stratocumulus. */
    SC("SC", Messages.getInstance().getString("CloudType.SC"));

    /** The shortcut of the cloud type. */
    private final String shortcut; //$NON-NLS-1$
    /** The name of the cloud type. */
    private final String name; //$NON-NLS-1$

    /**
     * Constructor.
     *
     * @param pShortcut string for shortcut.
     * @param pName     string for name.
     */
    CloudType(final String pShortcut, final String pName) {
        shortcut = pShortcut;
        name = pName;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * returns the shortcut of the type.
     *
     * @return string shortcut.
     */
    public String getShortcut() {
        return shortcut;
    }
}
