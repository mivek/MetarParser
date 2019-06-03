package io.github.mivek.parser.command.common;

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

    @Override public boolean execute(final AbstractWeatherContainer pContainer, final String pPart) {
        parseExtremeWind(pContainer.getWind(), pPart);
        return getReturnValue();
    }

    /**
     * Parses the wind.
     *
     * @param pWind        the wind to update
     * @param pExtremeWind String with extreme wind information
     */
    protected void parseExtremeWind(final Wind pWind, final String pExtremeWind) {
        String[] matches = Regex.pregMatch(WIND_EXTREME_REGEX, pExtremeWind);
        pWind.setExtreme1(Integer.parseInt(matches[1]));
        pWind.setExtreme2(Integer.parseInt(matches[2]));
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(WIND_EXTREME_REGEX, pInput);
    }
}
