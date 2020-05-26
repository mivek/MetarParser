package io.github.mivek.command.metar;

import io.github.mivek.model.Metar;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class AltimeterCommand implements Command {

    /** Pattern of the altimeter (Pascals). */
    private static final Pattern ALTIMETER_REGEX = Pattern.compile("^Q(\\d{4})$");

    /**
     * Package private constructor.
     */
    AltimeterCommand() {
    }

    @Override
    public void execute(final Metar metar, final String part) {
        String[] matches = Regex.pregMatch(ALTIMETER_REGEX, part);
        metar.setAltimeter(Integer.parseInt(matches[1]));
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(ALTIMETER_REGEX, input);
    }
}
