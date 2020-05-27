package io.github.mivek.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.WindShear;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class WindShearCommand implements BaseWindCommand {
    /** Pattern regex for windshear. */
    private static final Pattern WIND_SHEAR_REGEX = Pattern.compile("WS(\\d{3})\\/(\\w{3})(\\d{2})G?(\\d{2})?(KT|MPS|KM\\/H)");

    /**
     * Package private constructor.
     */
    WindShearCommand() {
    }

    @Override
    public boolean execute(final AbstractWeatherContainer container, final String part) {
        WindShear windShear = parseWindShear(part);
        container.setWindShear(windShear);
        return getReturnValue();
    }

    /**
     * Parses the wind shear part.
     *
     * @param stringWindShear the string to parse
     * @return a wind shear object.
     */
    protected WindShear parseWindShear(final String stringWindShear) {
        WindShear wind = new WindShear();
        String[] windPart = Regex.pregMatch(WIND_SHEAR_REGEX, stringWindShear);
        wind.setHeight(100 * Integer.parseInt(windPart[1]));
        setWindElements(wind, windPart[2], windPart[3], windPart[4], windPart[5]);
        return wind;
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(WIND_SHEAR_REGEX, input);
    }
}
