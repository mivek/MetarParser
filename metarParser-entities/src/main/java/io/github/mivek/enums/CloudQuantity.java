package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Enumeration for cloud quantity.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 *
 * @author mivek
 */
public enum CloudQuantity {
    /** Sky clear. */
    SKC, //$NON-NLS-1$
    /** Few clouds. */
    FEW, //$NON-NLS-1$
    /** Broken ceiling. */
    BKN, //$NON-NLS-1$
    /** Scattered. */
    SCT, //$NON-NLS-1$
    /** Overcast. */
    OVC, //$NON-NLS-1$
    /** No significant cloud. */
    NSC; //$NON-NLS-1$


    @Override
    public String toString() {
        return Messages.getInstance().getString("CloudQuantity." + name());
    }
}
