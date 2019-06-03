package io.github.mivek.parser.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class MinimalVisibilityCommand implements Command {
    /** Pattern for the minimum visibility. */
    public static final Pattern MIN_VISIBILITY_REGEX = Pattern.compile("^(\\d{4}[a-z])$");

    @Override public boolean execute(final AbstractWeatherContainer pContainer, final String pPart) {
        String[] matches = Regex.pregMatch(MIN_VISIBILITY_REGEX, pPart);
        pContainer.getVisibility().setMinVisibility(Integer.parseInt(matches[1].substring(0, 4)));
        pContainer.getVisibility().setMinDirection(matches[1].substring(4));
        return getReturnValue();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(MIN_VISIBILITY_REGEX, pInput);
    }
}
