package io.github.mivek.parser.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Wind;
import io.github.mivek.parser.AbstractParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public final class WindExtremeCommand implements Command {
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
        String[] matches = Regex.pregMatch(AbstractParser.WIND_EXTREME_REGEX, pExtremeWind);
        pWind.setExtreme1(Integer.parseInt(matches[1]));
        pWind.setExtreme2(Integer.parseInt(matches[2]));
    }
}
