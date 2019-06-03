package io.github.mivek.parser.command.common;

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

    @Override public boolean execute(final AbstractWeatherContainer pContainer, final String pPart) {
        String[] matches = Regex.pregMatch(MAIN_VISIBILITY_REGEX, pPart);
        if (pContainer.getVisibility() == null) {
            pContainer.setVisibility(new Visibility());
        }
        pContainer.getVisibility().setMainVisibility(Converter.convertVisibility(matches[1]));
        return getReturnValue();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(MAIN_VISIBILITY_REGEX, pInput);
    }
}
