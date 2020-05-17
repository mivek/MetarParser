package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Enumeration for cloud quantity.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 * @author mivek
 */
public enum CloudQuantity {
    /** Sky clear. */
    SKC("SKC", Messages.getInstance().getString("CloudQuantity.SKC")), //$NON-NLS-1$
    /** Few clouds. */
    FEW("FEW", Messages.getInstance().getString("CloudQuantity.FEW")), //$NON-NLS-1$
    /** Broken ceiling. */
    BKN("BKN", Messages.getInstance().getString("CloudQuantity.BKN")), //$NON-NLS-1$
    /** Scattered. */
    SCT("SCT", Messages.getInstance().getString("CloudQuantity.SCT")), //$NON-NLS-1$
    /** Overcast. */
    OVC("OVC", Messages.getInstance().getString("CloudQuantity.OVC")), //$NON-NLS-1$
    /** No significant cloud. */
    NSC("NSC", Messages.getInstance().getString("CloudQuantity.NSC")); //$NON-NLS-1$

    /** Shortcut of the cloud quanity. */
    private final String shortcut; //$NON-NLS-1$
    /** The name of the quantity. */
    private final String name; //$NON-NLS-1$

    /**
     * Constructor.
     * @param pShortcut a string representing the shortcut.
     * @param pName The meaning of the shortcut.
     */
    CloudQuantity(final String pShortcut, final String pName) {
        shortcut = pShortcut;
        name = pName;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns the shortcut.
     * @return a string.
     */
    public String getShortcut() {
        return shortcut;
    }

}
