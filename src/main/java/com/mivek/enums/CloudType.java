package com.mivek.enums;

import internationalization.Messages;

/**
 * Enumeration for cloud type. The first attribute is the code used in the
 * metar. The second attribute is the meaning of the code.
 * @author mivek
 */
public enum CloudType {
    /**
     * cumulonimbus.
     */
    CB("CB", Messages.CLOUD_TYPE_CB), //$NON-NLS-1$
    /**
     * towering cumulus, cumulus congestus.
     */
    TCU("TCU", Messages.CLOUD_TYPE_TCU), //$NON-NLS-1$
    /**
     * Cirrus.
     */
    CI("CI", Messages.CLOUD_TYPE_CI);

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
