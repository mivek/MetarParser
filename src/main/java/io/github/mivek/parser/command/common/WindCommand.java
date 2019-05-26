package io.github.mivek.parser.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Wind;
import io.github.mivek.parser.AbstractParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public final class WindCommand implements BaseWindCommand {

    /**
     * This method parses the wind part of the metar code. It parses the generic
     * part.
     *
     * @param pStringWind a string with wind elements.
     * @return a Wind element with the informations.
     */
    protected Wind parseWind(final String pStringWind) {
        Wind wind = new Wind();
        String[] windPart = Regex.pregMatch(AbstractParser.WIND_REGEX, pStringWind);
        setWindElements(wind, windPart[1], windPart[2], windPart[3], windPart[4]);
        return wind;
    }

    @Override public boolean execute(final AbstractWeatherContainer pContainer, final String pPart) {
        Wind wind = parseWind(pPart);
        pContainer.setWind(wind);
        return getReturnValue();
    }
}
