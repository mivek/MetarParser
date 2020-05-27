package io.github.mivek.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Visibility;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class MainVisibilityCommand implements Command {
    /** Pattern for the main visibility. */
    private static final Pattern MAIN_VISIBILITY_REGEX = Pattern.compile("^(\\d{4})(|NDV)$");

    /**
     * constructor.
     */
    MainVisibilityCommand() {
    }

    @Override
    public boolean execute(final AbstractWeatherContainer container, final String part) {
        String[] matches = Regex.pregMatch(MAIN_VISIBILITY_REGEX, part);
        if (container.getVisibility() == null) {
            container.setVisibility(new Visibility());
        }
        container.getVisibility().setMainVisibility(Converter.convertVisibility(matches[1]));
        return getReturnValue();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(MAIN_VISIBILITY_REGEX, input);
    }
}
