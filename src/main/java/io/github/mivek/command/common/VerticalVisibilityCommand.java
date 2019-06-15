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

    @Override public boolean execute(final AbstractWeatherContainer pContainer, final String pPart) {
        String[] matches = Regex.pregMatch(VERTICAL_VISIBILITY, pPart);
        pContainer.setVerticalVisibility(100 * Integer.parseInt(matches[1]));
        return getReturnValue();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(VERTICAL_VISIBILITY, pInput);
    }
}
