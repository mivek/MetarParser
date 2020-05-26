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
    private static final Pattern WIND_REGEX = Pattern.compile("(VRB|\\d{3})(\\d{2})G?(\\d{2})?(KT|MPS|KM\\/H)?");

    /**
     * Package private constructor.
     */
    WindCommand() {
    }

    /**
     * This method parses the wind part of the metar code. It parses the generic
     * part.
     *
     * @param stringWind a string with wind elements.
     * @return a Wind element with the informations.
     */
    protected Wind parseWind(final String stringWind) {
        Wind wind = new Wind();
        String[] windPart = Regex.pregMatch(WIND_REGEX, stringWind);
        setWindElements(wind, windPart[1], windPart[2], windPart[3], windPart[4]);
        return wind;
    }

    @Override
    public boolean execute(final AbstractWeatherContainer container, final String part) {
        Wind wind = parseWind(part);
        container.setWind(wind);
        return getReturnValue();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(WIND_REGEX, input);
    }
}
