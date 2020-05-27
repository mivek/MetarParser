package io.github.mivek.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class MinimalVisibilityCommand implements Command {
    /** Pattern for the minimum visibility. */
    public static final Pattern MIN_VISIBILITY_REGEX = Pattern.compile("^(\\d{4}[a-z])$");

    /**
     * Protected constructor.
     */
    MinimalVisibilityCommand() {
    }

    @Override
    public boolean execute(final AbstractWeatherContainer container, final String part) {
        String[] matches = Regex.pregMatch(MIN_VISIBILITY_REGEX, part);
        container.getVisibility().setMinVisibility(Integer.parseInt(matches[1].substring(0, 4)));
        container.getVisibility().setMinDirection(matches[1].substring(4));
        return getReturnValue();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(MIN_VISIBILITY_REGEX, input);
    }
}
