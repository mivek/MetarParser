package io.github.mivek.parser.command.metar;

import io.github.mivek.model.Metar;
import io.github.mivek.parser.MetarParser;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class AltimeterMecuryCommand implements Command {

    @Override public final void execute(final Metar pMetar, final String pPart) {
        String[] matches = Regex.pregMatch(MetarParser.ALTIMETER_MERCURY_REGEX, pPart);
        double mercury = Double.parseDouble(matches[1]) / 100;
        pMetar.setAltimeter((int) Converter.inchesMercuryToHPascal(mercury));
    }
}
