package io.github.mivek.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class VerticalVisibilityCommand implements Command {
    /** Pattern for the vertical visibility. */
    private static final Pattern VERTICAL_VISIBILITY = Pattern.compile("^VV(\\d{3})$");

    /**
     * Protected constructor.
     */
    VerticalVisibilityCommand() {
    }

    @Override
    public boolean execute(final AbstractWeatherContainer container, final String part) {
        String[] matches = Regex.pregMatch(VERTICAL_VISIBILITY, part);
        container.setVerticalVisibility(100 * Integer.parseInt(matches[1]));
        return getReturnValue();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(VERTICAL_VISIBILITY, input);
    }
}
