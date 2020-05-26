package io.github.mivek.command.metar;

import io.github.mivek.model.Metar;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class AltimeterMecuryCommand implements Command {

    /** Pattern for the altimeter in inches of mercury. */
    private static final Pattern ALTIMETER_MERCURY_REGEX = Pattern.compile("^A(\\d{4})$");

    /**
     * Package private constructor.
     */
    AltimeterMecuryCommand() {
    }

    @Override
    public void execute(final Metar metar, final String part) {
        String[] matches = Regex.pregMatch(ALTIMETER_MERCURY_REGEX, part);
        double mercury = Double.parseDouble(matches[1]) / 100;
        metar.setAltimeter((int) Converter.inchesMercuryToHPascal(mercury));
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(ALTIMETER_MERCURY_REGEX, input);
    }
}
