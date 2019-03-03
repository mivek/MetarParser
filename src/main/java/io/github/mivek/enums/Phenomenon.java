package io.github.mivek.enums;

import io.github.mivekinternationalization.Messages;

/**
 * Enumeration for phenomenon.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 * @author mivek
 */
public enum Phenomenon {
    /** Rain. */
    RAIN("RA", Messages.getInstance().getString("Phenomenon.RA")), //$NON-NLS-1$
    /** Drizzle. */
    DRIZZLE("DZ", Messages.getInstance().getString("Phenomenon.DZ")), //$NON-NLS-1$
    /** Snow. */
    SNOW("SN", Messages.getInstance().getString("Phenomenon.SN")), //$NON-NLS-1$
    /** Snow grains. */
    SNOW_GRAINS("SG", Messages.getInstance().getString("Phenomenon.SG")), //$NON-NLS-1$
    /** Ice pellets. */
    ICE_PELLETS("IC", Messages.getInstance().getString("Phenomenon.IC")), //$NON-NLS-1$
    /** Ice crystals. */
    ICE_CRYSTALS("PL", Messages.getInstance().getString("Phenomenon.PL")), //$NON-NLS-1$
    /** Hail. */
    HAIL("GR", Messages.getInstance().getString("Phenomenon.GR")), //$NON-NLS-1$
    /** Small hail. */
    SMALL_HAIL("GS", Messages.getInstance().getString("Phenomenon.GS")), //$NON-NLS-1$
    /** Unknow precipitation. */
    UNKNOW_PRECIPITATION("UP", Messages.getInstance().getString("Phenomenon.UP")), //$NON-NLS-1$
    /** Fog. */
    FOG("FG", Messages.getInstance().getString("Phenomenon.FG")), //$NON-NLS-1$
    /** Volcanic ashes. */
    VOLCANIC_ASH("VA", Messages.getInstance().getString("Phenomenon.VA")), //$NON-NLS-1$
    /** Mist. */
    MIST("BR", Messages.getInstance().getString("Phenomenon.BR")), //$NON-NLS-1$
    /** Haze. */
    HAZE("HZ", Messages.getInstance().getString("Phenomenon.HZ")), //$NON-NLS-1$
    /** Widespread dust. */
    WIDESPREAD_DUST("DU", Messages.getInstance().getString("Phenomenon.DU")), //$NON-NLS-1$
    /** Smoke. */
    SMOKE("FU", Messages.getInstance().getString("Phenomenon.FU")), //$NON-NLS-1$
    /** Sand. */
    SAND("SA", Messages.getInstance().getString("Phenomenon.SA")), //$NON-NLS-1$
    /** Spray. */
    SPRAY("PY", Messages.getInstance().getString("Phenomenon.PY")), //$NON-NLS-1$
    /** Squall. */
    SQUALL("SQ", Messages.getInstance().getString("Phenomenon.SQ")), //$NON-NLS-1$
    /** Sand whirl. */
    SAND_WHIRLS("PO", Messages.getInstance().getString("Phenomenon.PO")), //$NON-NLS-1$
    /** Duststorm. */
    DUSTSTORM("DS", Messages.getInstance().getString("Phenomenon.DS")), //$NON-NLS-1$
    /** Sandstorm. */
    SANDSTORM("SS", Messages.getInstance().getString("Phenomenon.SS")), //$NON-NLS-1$
    /** Funnel cloud. */
    FUNNEL_CLOUD("FC", Messages.getInstance().getString("Phenomenon.FC")); //$NON-NLS-1$

    /** Shortcut of the phenomenon. */
    private String fShortcut = ""; //$NON-NLS-1$
    /** Name of the phenomenon. */
    private String fName = ""; //$NON-NLS-1$

    /**
     * Constructor.
     * @param pShortcut
     * string for the shortcut.
     * @param pName
     * string for the name.
     */
    Phenomenon(final String pShortcut, final String pName) {
        fShortcut = pShortcut;
        fName = pName;
    }

    @Override
    public String toString() {
        return fName;
    }

    /**
     * Returns the shortcut.
     * @return string.
     */
    public String getShortcut() {
        return fShortcut;
    }
}
