package io.github.mivek.parser.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.WindShear;
import io.github.mivek.parser.AbstractParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public final class WindShearCommand implements BaseWindCommand {
    @Override public boolean execute(final AbstractWeatherContainer pContainer, final String pPart) {
        WindShear windShear = parseWindShear(pPart);
        pContainer.setWindShear(windShear);
        return getReturnValue();
    }

    /**
     * Parses the wind shear part.
     *
     * @param pStringWindShear the string to parse
     * @return a wind shear object.
     */
    protected WindShear parseWindShear(final String pStringWindShear) {
        WindShear wind = new WindShear();
        String[] windPart = Regex.pregMatch(AbstractParser.WIND_SHEAR_REGEX, pStringWindShear);
        wind.setHeight(100 * Integer.parseInt(windPart[1]));
        setWindElements(wind, windPart[2], windPart[3], windPart[4], windPart[5]);
        return wind;
    }
}
