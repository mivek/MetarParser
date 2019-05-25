package io.github.mivek.parser.command.metar;

import io.github.mivek.model.Metar;
import io.github.mivek.parser.MetarParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class AltimeterCommand implements Command {

    @Override public final void execute(final Metar pMetar, final String pPart) {
        String[] matches = Regex.pregMatch(MetarParser.ALTIMETER_REGEX, pPart);
        pMetar.setAltimeter(Integer.parseInt(matches[1]));
    }
}
