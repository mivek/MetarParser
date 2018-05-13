package com.mivek.enums;

import internationalization.Messages;

/**
 * Enumeration for phenomenon.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 * @author jk
 */
public enum Phenomenon {
    /**
     * Rain.
     */
    RAIN("RA", Messages.PHENOMENON_RA), //$NON-NLS-1$
    /**
     * Drizzle.
     */
    DRIZZLE("DZ", Messages.PHENOMENON_DZ), //$NON-NLS-1$
    /**
     * Snow.
     */
    SNOW("SN", Messages.PHENOMENON_SN), //$NON-NLS-1$
    /**
     * Snow grains.
     */
    SNOW_GRAINS("SG", Messages.PHENOMENON_SG), //$NON-NLS-1$
    /**
     * Ice pellets.
     */
    ICE_PELLETS("IC", Messages.PHENOMENON_IC), //$NON-NLS-1$
    /**
     * Ice crystals.
     */
    ICE_CRYSTALS("PL", Messages.PHENOMENON_PL), //$NON-NLS-1$
    /**
     * Hail.
     */
    HAIL("GR", Messages.PHENOMENON_GR), //$NON-NLS-1$
    /**
     * Small hail.
     */
    SMALL_HAIL("GS", Messages.PHENOMENON_GS), //$NON-NLS-1$
    /**
     * Unknow precipitation.
     */
    UNKNOW_PRECIPITATION("UP", Messages.PHENOMENON_UP), //$NON-NLS-1$
    /**
     * Fog.
     */
    FOG("FG", Messages.PHENOMENON_FG), //$NON-NLS-1$
    /**
     * Volcanic ashes.
     */
    VOLCANIC_ASH("VA", Messages.PHENOMENON_VA), //$NON-NLS-1$
    /**
     * Mist.
     */
    MIST("BR", Messages.PHENOMENON_BR), //$NON-NLS-1$
    /**
     * Haze.
     */
    HAZE("HZ", Messages.PHENOMENON_HZ), //$NON-NLS-1$
    /**
     * Widespread dust.
     */
    WIDESPREAD_DUST("DU", Messages.PHENOMENON_DU), //$NON-NLS-1$
    /**
     * Smoke.
     */
    SMOKE("FU", Messages.PHENOMENON_FU), //$NON-NLS-1$
    /**
     * Sand.
     */
    SAND("SA", Messages.PHENOMENON_SA), //$NON-NLS-1$
    /**
     * Spray.
     */
    SPRAY("PY", Messages.PHENOMENON_PY), //$NON-NLS-1$
    /**
     * Squall.
     */
    SQUALL("SQ", Messages.PHENOMENON_SQ), //$NON-NLS-1$
    /**
     * Sand whirl.
     */
    SAND_WHIRLS("PO", Messages.PHENOMENON_PO), //$NON-NLS-1$
    /**
     * Duststorm.
     */
    DUSTSTORM("DS", Messages.PHENOMENON_DS), //$NON-NLS-1$
    /**
     * Sandstorm.
     */
    SANDSTORM("SS", Messages.PHENOMENON_SS), //$NON-NLS-1$
    /**
     * Funnel cloud.
     */
    FUNNEL_CLOUD("FC", Messages.PHENOMENON_FC); //$NON-NLS-1$

    /**
     * Shortcut of the phenomenon.
     */
    private String fShortcut = ""; //$NON-NLS-1$
    /**
     * Name of the phenomenon.
     */
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
