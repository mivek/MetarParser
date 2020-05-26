package io.github.mivek.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Wind;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class WindExtremeCommand implements Command {
    /** Pattern regex for extreme winds. */
    private static final Pattern WIND_EXTREME_REGEX = Pattern.compile("^(\\d{3})V(\\d{3})");

    /**
     * Protected constructor.
     */
    WindExtremeCommand() {
    }

    @Override
    public boolean execute(final AbstractWeatherContainer container, final String part) {
        parseWindVariation(container.getWind(), part);
        return getReturnValue();
    }

    /**
     * Parses the wind.
     *
     * @param wind          the wind to update
     * @param windVariation String with wind variation information
     */
    protected void parseWindVariation(final Wind wind, final String windVariation) {
        String[] matches = Regex.pregMatch(WIND_EXTREME_REGEX, windVariation);
        wind.setMinVariation(Integer.parseInt(matches[1]));
        wind.setMaxVariation(Integer.parseInt(matches[2]));
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(WIND_EXTREME_REGEX, input);
    }
}
