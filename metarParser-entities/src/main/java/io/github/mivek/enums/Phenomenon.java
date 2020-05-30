package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Enumeration for phenomenon.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 *
 * @author mivek
 */
public enum Phenomenon {
    /** Rain. */
    RAIN("RA"), //$NON-NLS-1$
    /** Drizzle. */
    DRIZZLE("DZ"), //$NON-NLS-1$
    /** Snow. */
    SNOW("SN"), //$NON-NLS-1$
    /** Snow grains. */
    SNOW_GRAINS("SG"), //$NON-NLS-1$
    /** Ice pellets. */
    ICE_PELLETS("PL"), //$NON-NLS-1$
    /** Ice crystals. */
    ICE_CRYSTALS("IC"), //$NON-NLS-1$
    /** Hail. */
    HAIL("GR"), //$NON-NLS-1$
    /** Small hail. */
    SMALL_HAIL("GS"), //$NON-NLS-1$
    /** Unknow precipitation. */
    UNKNOW_PRECIPITATION("UP"), //$NON-NLS-1$
    /** Fog. */
    FOG("FG"), //$NON-NLS-1$
    /** Volcanic ashes. */
    VOLCANIC_ASH("VA"), //$NON-NLS-1$
    /** Mist. */
    MIST("BR"), //$NON-NLS-1$
    /** Haze. */
    HAZE("HZ"), //$NON-NLS-1$
    /** Widespread dust. */
    WIDESPREAD_DUST("DU"), //$NON-NLS-1$
    /** Smoke. */
    SMOKE("FU"), //$NON-NLS-1$
    /** Sand. */
    SAND("SA"), //$NON-NLS-1$
    /** Spray. */
    SPRAY("PY"), //$NON-NLS-1$
    /** Squall. */
    SQUALL("SQ"), //$NON-NLS-1$
    /** Sand whirl. */
    SAND_WHIRLS("PO"), //$NON-NLS-1$
    /** Duststorm. */
    DUSTSTORM("DS"), //$NON-NLS-1$
    /** Sandstorm. */
    SANDSTORM("SS"), //$NON-NLS-1$
    /** Funnel cloud. */
    FUNNEL_CLOUD("FC"); //$NON-NLS-1$

    /** Shortcut of the phenomenon. */
    private final String shortcut;

    /**
     * Constructor.
     *
     * @param shortcut string for the shortcut.
     */
    Phenomenon(final String shortcut) {
        this.shortcut = shortcut;
    }

    @Override
    public String toString() {
        return Messages.getInstance().getString("Phenomenon." + getShortcut());
    }

    /**
     * Returns the shortcut.
     *
     * @return string.
     */
    public String getShortcut() {
        return shortcut;
    }
}
