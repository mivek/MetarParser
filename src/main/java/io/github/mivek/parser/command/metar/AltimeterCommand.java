package io.github.mivek.parser.command.metar;

import io.github.mivek.model.Metar;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class AltimeterCommand implements Command {

    /** Pattern of the altimeter (Pascals). */
    private static final Pattern ALTIMETER_REGEX = Pattern.compile("^Q(\\d{4})$");

    @Override public void execute(final Metar pMetar, final String pPart) {
        String[] matches = Regex.pregMatch(ALTIMETER_REGEX, pPart);
        pMetar.setAltimeter(Integer.parseInt(matches[1]));
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(ALTIMETER_REGEX, pInput);
    }
}
