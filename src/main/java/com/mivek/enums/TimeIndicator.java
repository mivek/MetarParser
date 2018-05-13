package com.mivek.enums;

import i18n.Messages;

/**
 * Enumeration for time indicator in metar trends.
 * @author mivek
 */
public enum TimeIndicator {
    /** The AT value of the metar trend. */
    AT("AT", Messages.AT),
    /** The FM value of the metar trend. */
    FM("FM", Messages.FROM),
    /** The TL value of the metar trend. */
    TL("TL", Messages.TL);

    /**
     * Shortcut of the time indicator.
     */
    private String fShortCut = "";
    /**
     * Name of the time indicator.
     */
    private String fName = "";

    /**
     * Constructor.
     * @param pShortCut the shortcut of the indicator.
     * @param pName the name of the indicator.
     */
    TimeIndicator(final String pShortCut, final String pName) {
        fShortCut = pShortCut;
        fName = pName;
    }

    /**
     * @return the shortcut.
     */
    public String getShortcut() {
        return fShortCut;
    }

    @Override
    public String toString() {
        return fName;
    }
}
