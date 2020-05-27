package io.github.mivek.command.metar;

import io.github.mivek.model.Metar;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class TemperatureCommand implements Command {
    /** Pattern of the temperature block. */
    private static final Pattern TEMPERATURE_REGEX = Pattern.compile("^(M?\\d{2})/(M?\\d{2})$");

    /**
     * Package private constructor.
     */
    TemperatureCommand() {
    }

    @Override
    public void execute(final Metar metar, final String part) {
        String[] matches = Regex.pregMatch(TEMPERATURE_REGEX, part);
        metar.setTemperature(Converter.convertTemperature(matches[1]));
        metar.setDewPoint(Converter.convertTemperature(matches[2]));
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(TEMPERATURE_REGEX, input);
    }
}
