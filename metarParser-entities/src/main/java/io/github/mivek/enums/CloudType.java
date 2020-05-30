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
    CB, //$NON-NLS-1$
    /** towering cumulus, cumulus congestus. */
    TCU, //$NON-NLS-1$
    /** Cirrus. */
    CI,
    /** Cirrocumulus. */
    CC,
    /** Cirrostratus. */
    CS,
    /** Altocumulus. */
    AC,
    /** Stratus. */
    ST,
    /** Cumulus. */
    CU,
    /** Astrostratus. */
    AS,
    /** Nimbostratus. */
    NS,
    /** Stratocumulus. */
    SC;

    @Override
    public String toString() {
        return Messages.getInstance().getString("CloudType." + name());
    }


}
