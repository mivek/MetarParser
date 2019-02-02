package io.github.mivek.enums;

import internationalization.Messages;

/**
 * @author mivek
 */
public enum WeatherChangeType {
    /**
     * From enumeration.
     */
    FM("FM", Messages.getInstance().getFrom()),
    /**
     * Becoming enumeration.
     */
    BECMG("BECMG", Messages.getInstance().getBecmg()),
    /**
     * Tempo enumeration.
     */
    TEMPO("TEMPO", Messages.getInstance().getTempo()),
    /**
     * Probability change.
     */
    PROB("PROB", Messages.getInstance().getProb());
    /**
     * Shortcut attribute.
     */
    private String fShortcut = "";
    /**
     * Name of the enumeration.
     */
    private String fName = "";

    /**
     * Constructor.
     * @param pShortcut
     * the shortcut of the enumeration
     * @param pName
     * the name of the enumeration
     */
    WeatherChangeType(final String pShortcut, final String pName) {
        fShortcut = pShortcut;
        fName = pName;
    }

    /**
     * @return the shortcut.
     */
    public String getShortcut() {
        return fShortcut;
    }

    @Override
    public String toString() {
        return fName;
    }

}