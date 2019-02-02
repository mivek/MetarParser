package io.github.mivek.enums;

import io.github.mivekinternationalization.Messages;

/**
 * Enumeration for cloud type. The first attribute is the code used in the
 * metar. The second attribute is the meaning of the code.
 * @author mivek
 */
public enum CloudType {
    /**
     * cumulonimbus.
     */
    CB("CB", Messages.getInstance().getCloudTypeCB()), //$NON-NLS-1$
    /**
     * towering cumulus, cumulus congestus.
     */
    TCU("TCU", Messages.getInstance().getCloudTypeTCU()), //$NON-NLS-1$
    /**
     * Cirrus.
     */
    CI("CI", Messages.getInstance().getCloudTypeCI()),
    /**
     * Cirrocumulus.
     */
    CC("CC", Messages.getInstance().getCloudTypeCC()),
    /**
     * Cirrostratus.
     */
    CS("CS", Messages.getInstance().getCloudTypeCS()),
    /**
     * Altocumulus.
     */
    AC("AC", Messages.getInstance().getCloudTypeAC()),
    /**
     * Stratus.
     */
    ST("ST", Messages.getInstance().getCloudTypeST()),
    /**
     * Cumulus.
     */
    CU("CU", Messages.getInstance().getCloudTypeCU()),
    /**
     * Astrostratus.
     */
    AS("AS", Messages.getInstance().getCloudTypeAS()),
    /**
     * Nimbostratus.
     */
    NS("NS", Messages.getInstance().getCloudTypeNS()),
    /**
     * Stratocumulus.
     */
    SC("SC", Messages.getInstance().getCloudTypeSC());

    /**
     * The shortcut of the cloud type.
     */
    private String fShortcut = ""; //$NON-NLS-1$
    /**
     * The name of the cloud type.
     */
    private String fName = ""; //$NON-NLS-1$

    /**
     * Constructor.
     * @param pShortcut
     * string for shortcut.
     * @param pName
     * string for name.
     */
    CloudType(final String pShortcut, final String pName) {
        fShortcut = pShortcut;
        fName = pName;
    }

    @Override
    public String toString() {
        return fName;
    }

    /**
     * returns the shortcut of the type.
     * @return string shortcut.
     */
    public String getShortcut() {
        return fShortcut;
    }
}
