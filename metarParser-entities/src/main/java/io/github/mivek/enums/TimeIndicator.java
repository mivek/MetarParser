package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * Enumeration for time indicator in metar trends.
 *
 * @author mivek
 */
public enum TimeIndicator {
    /** The AT value of the metar trend. */
    AT("AT", Messages.getInstance().getString("TimeIndicator.AT")),
    /** The FM value of the metar trend. */
    FM("FM", Messages.getInstance().getString("WeatherChangeType.FM")),
    /** The TL value of the metar trend. */
    TL("TL", Messages.getInstance().getString("TimeIndicator.TL"));

    /**
     * Shortcut of the time indicator.
     */
    private final String shortCut;
    /**
     * Name of the time indicator.
     */
    private final String name;

    /**
     * Constructor.
     *
     * @param shortCut the shortcut of the indicator.
     * @param name     the name of the indicator.
     */
    TimeIndicator(final String shortCut, final String name) {
        this.shortCut = shortCut;
        this.name = name;
    }

    /**
     * @return the shortcut.
     */
    public String getShortcut() {
        return shortCut;
    }

    @Override
    public String toString() {
        return name;
    }
}
