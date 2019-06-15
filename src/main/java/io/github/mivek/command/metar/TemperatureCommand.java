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

    @Override public void execute(final Metar pMetar, final String pPart) {
        String[] matches = Regex.pregMatch(TEMPERATURE_REGEX, pPart);
        pMetar.setTemperature(Converter.convertTemperature(matches[1]));
        pMetar.setDewPoint(Converter.convertTemperature(matches[2]));
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(TEMPERATURE_REGEX, pInput);
    }
}
