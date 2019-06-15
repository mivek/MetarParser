package io.github.mivek.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Wind;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class WindCommand implements BaseWindCommand {
    /** Pattern regex for wind. */
    private static final Pattern WIND_REGEX = Pattern.compile("(\\w{3})(\\d{2})G?(\\d{2})?(KT|MPS|KM\\/H)");
    /**
     * This method parses the wind part of the metar code. It parses the generic
     * part.
     *
     * @param pStringWind a string with wind elements.
     * @return a Wind element with the informations.
     */
    protected Wind parseWind(final String pStringWind) {
        Wind wind = new Wind();
        String[] windPart = Regex.pregMatch(WIND_REGEX, pStringWind);
        setWindElements(wind, windPart[1], windPart[2], windPart[3], windPart[4]);
        return wind;
    }

    @Override public boolean execute(final AbstractWeatherContainer pContainer, final String pPart) {
        Wind wind = parseWind(pPart);
        pContainer.setWind(wind);
        return getReturnValue();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(WIND_REGEX, pInput);
    }
}
