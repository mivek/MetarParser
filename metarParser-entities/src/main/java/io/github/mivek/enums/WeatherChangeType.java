package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;

/**
 * @author mivek
 */
public enum WeatherChangeType {
    /** From enumeration. */
    FM,
    /** Becoming enumeration. */
    BECMG,
    /** Tempo enumeration. */
    TEMPO,
    /** Probability change. */
    PROB;


    @Override
    public String toString() {
        return Messages.getInstance().getString("WeatherChangeType." + name());
    }

}
