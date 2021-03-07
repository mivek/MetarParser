package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Represent the thickness of the deposit on the runway
 * @author mivek
 */
public enum DepositThickness {
    /** Not reported // . */
    NOT_REPORTED,
    /** Less than 1mm: 00*/
    LESS_1_MM,
    /** 10 cm.: 92 */
    THICKNESS_10,
    /** 15 cm.: 93 */
    THICKNESS_15,
    /** 20 cm. 94 */
    THICKNESS_20,
    /** 25 cm. 95*/
    THICKNESS_25,
    /** 30 cm. 96 */
    THICKNESS_30,
    /** 35 cm. 97 */
    THICKNESS_35,
    /** 40 cm or more. 98 */
    THICKNESS_40,
    /** closed: 99. */
    CLOSED;

    @Override
    public String toString() {
        return Messages.getInstance().getString("DepositThickness." + name());
    }
}

