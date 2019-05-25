package io.github.mivek.parser.command.metar;

import io.github.mivek.model.Metar;
import io.github.mivek.parser.MetarParser;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class TemperatureCommand implements Command {

    @Override public final void execute(final Metar pMetar, final String pPart) {
        String[] matches = Regex.pregMatch(MetarParser.TEMPERATURE_REGEX, pPart);
        pMetar.setTemperature(Converter.convertTemperature(matches[1]));
        pMetar.setDewPoint(Converter.convertTemperature(matches[2]));
    }
}
