package io.github.mivek.enums;

import io.github.mivekinternationalization.Messages;

/**
 * Enumeration for cloud quantity.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 * @author mivek
 */
public enum CloudQuantity {
    /**
     * Sky clear.
     */
    SKC("SKC", Messages.getInstance().getCloudQuantitySKC()), //$NON-NLS-1$
    /**
     * Few clouds.
     */
    FEW("FEW", Messages.getInstance().getCloudQuantityFEW()), //$NON-NLS-1$
    /**
     * Broken ceiling.
     */
    BKN("BKN", Messages.getInstance().getCloudQuantityBKN()), //$NON-NLS-1$
    /**
     * Scattered.
     */
    SCT("SCT", Messages.getInstance().getCloudQuantitySCT()), //$NON-NLS-1$
    /**
     * Overcast.
     */
    OVC("OVC", Messages.getInstance().getCloudQuantityOVC()), //$NON-NLS-1$
    /**
     * No significant cloud.
     */
    NSC("NSC", Messages.getInstance().getCloudQuantityNSC()); //$NON-NLS-1$
    /**
     * Shortcut of the cloud quanity.
     */
    private String fShortcut = ""; //$NON-NLS-1$
    /**
     * The name of the quantity.
     */
    private String fName = ""; //$NON-NLS-1$

    /**
     * Constructor.
     * @param pShortcut a string representing the shortcut.
     * @param pName The meaning of the shortcut.
     */
    CloudQuantity(final String pShortcut, final String pName) {
        fShortcut = pShortcut;
        fName = pName;
    }

    @Override
    public String toString() {
        return fName;
    }

    /**
     * Returns the shortcut.
     * @return a string.
     */
    public String getShortcut() {
        return fShortcut;
    }

}
