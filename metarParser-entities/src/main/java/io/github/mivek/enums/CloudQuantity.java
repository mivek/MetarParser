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
    /** Clear. */
    CLR,
    /** Sky clear. */
    SKC,
    /** Few clouds. */
    FEW,
    /** Broken ceiling. */
    BKN,
    /** Scattered. */
    SCT,
    /** Overcast. */
    OVC,
    /** No significant cloud. */
    NSC;


    @Override
    public String toString() {
        return Messages.getInstance().getString("CloudQuantity." + name());
    }
}
