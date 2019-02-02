package io.github.mivek.enums;

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
    RAIN("RA", Messages.getInstance().getPhenomenonRA()), //$NON-NLS-1$
    /**
     * Drizzle.
     */
    DRIZZLE("DZ", Messages.getInstance().getPhenomenonDZ()), //$NON-NLS-1$
    /**
     * Snow.
     */
    SNOW("SN", Messages.getInstance().getPhenomenonSN()), //$NON-NLS-1$
    /**
     * Snow grains.
     */
    SNOW_GRAINS("SG", Messages.getInstance().getPhenomenonSG()), //$NON-NLS-1$
    /**
     * Ice pellets.
     */
    ICE_PELLETS("IC", Messages.getInstance().getPhenomenonIC()), //$NON-NLS-1$
    /**
     * Ice crystals.
     */
    ICE_CRYSTALS("PL", Messages.getInstance().getPhenomenonPL()), //$NON-NLS-1$
    /**
     * Hail.
     */
    HAIL("GR", Messages.getInstance().getPhenomenonGR()), //$NON-NLS-1$
    /**
     * Small hail.
     */
    SMALL_HAIL("GS", Messages.getInstance().getPhenomenonGS()), //$NON-NLS-1$
    /**
     * Unknow precipitation.
     */
    UNKNOW_PRECIPITATION("UP", Messages.getInstance().getPhenomenonUP()), //$NON-NLS-1$
    /**
     * Fog.
     */
    FOG("FG", Messages.getInstance().getPhenomenonFG()), //$NON-NLS-1$
    /**
     * Volcanic ashes.
     */
    VOLCANIC_ASH("VA", Messages.getInstance().getPhenomenonVA()), //$NON-NLS-1$
    /**
     * Mist.
     */
    MIST("BR", Messages.getInstance().getPhenomenonBR()), //$NON-NLS-1$
    /**
     * Haze.
     */
    HAZE("HZ", Messages.getInstance().getPhenomenonHZ()), //$NON-NLS-1$
    /**
     * Widespread dust.
     */
    WIDESPREAD_DUST("DU", Messages.getInstance().getPhenomenonDU()), //$NON-NLS-1$
    /**
     * Smoke.
     */
    SMOKE("FU", Messages.getInstance().getPhenomenonFU()), //$NON-NLS-1$
    /**
     * Sand.
     */
    SAND("SA", Messages.getInstance().getPhenomenonSA()), //$NON-NLS-1$
    /**
     * Spray.
     */
    SPRAY("PY", Messages.getInstance().getPhenomenonPY()), //$NON-NLS-1$
    /**
     * Squall.
     */
    SQUALL("SQ", Messages.getInstance().getPhenomenonSQ()), //$NON-NLS-1$
    /**
     * Sand whirl.
     */
    SAND_WHIRLS("PO", Messages.getInstance().getPhenpmenonPO()), //$NON-NLS-1$
    /**
     * Duststorm.
     */
    DUSTSTORM("DS", Messages.getInstance().getPhenomenonDS()), //$NON-NLS-1$
    /**
     * Sandstorm.
     */
    SANDSTORM("SS", Messages.getInstance().getPhenomenonSS()), //$NON-NLS-1$
    /**
     * Funnel cloud.
     */
    FUNNEL_CLOUD("FC", Messages.getInstance().getPhenomenonFC()); //$NON-NLS-1$

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
