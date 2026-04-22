package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import java.util.regex.Pattern;

/**
 * Enumeration for phenomenon.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 *
 * @author mivek
 */
public enum Phenomenon {
    /** Rain. */
    RAIN("RA"),
    /** Drizzle. */
    DRIZZLE("DZ"),
    /** Snow. */
    SNOW("SN"),
    /** Snow grains. */
    SNOW_GRAINS("SG"),
    /** Ice pellets. */
    ICE_PELLETS("PL"),
    /** Ice crystals. */
    ICE_CRYSTALS("IC"),
    /** Hail. */
    HAIL("GR"),
    /** Small hail. */
    SMALL_HAIL("GS"),
    /** Unknow precipitation. */
    UNKNOW_PRECIPITATION("UP"),
    /** Fog. */
    FOG("FG"),
    /** Volcanic ashes. */
    VOLCANIC_ASH("VA"),
    /** Mist. */
    MIST("BR"),
    /** Haze. */
    HAZE("HZ"),
    /** Widespread dust. */
    WIDESPREAD_DUST("DU"),
    /** Smoke. */
    SMOKE("FU"),
    /** Sand. */
    SAND("SA"),
    /** Spray. */
    SPRAY("PY"),
    /** Squall. */
    SQUALL("SQ"),
    /** Sand whirl. */
    SAND_WHIRLS("PO"),
    /** Duststorm. */
    DUSTSTORM("DS"),
    /** Sandstorm. */
    SANDSTORM("SS"),
    /** Funnel cloud. */
    FUNNEL_CLOUD("FC");

    /** Shortcut of the phenomenon. */
    private final String shortcut;
    /** Pre-compiled pattern used to match this phenomenon at the start of a weather token. */
    private final Pattern pattern;

    /**
     * Constructor.
     *
     * @param shortcut string for the shortcut.
     */
    Phenomenon(final String shortcut) {
        this.shortcut = shortcut;
        this.pattern = Pattern.compile("^" + shortcut);
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

    /**
     * Returns the pre-compiled pattern used to match this phenomenon at the start of a weather token.
     *
     * @return the compiled {@link Pattern}.
     */
    public Pattern getPattern() {
        return pattern;
    }
}
