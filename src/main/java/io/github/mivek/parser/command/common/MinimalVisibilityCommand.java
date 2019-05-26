package io.github.mivek.parser.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.parser.AbstractParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public final class MinimalVisibilityCommand implements Command {
    @Override public boolean execute(final AbstractWeatherContainer pContainer, final String pPart) {
        String[] matches = Regex.pregMatch(AbstractParser.MIN_VISIBILITY_REGEX, pPart);
        pContainer.getVisibility().setMinVisibility(Integer.parseInt(matches[1].substring(0, 4)));
        pContainer.getVisibility().setMinDirection(matches[1].substring(4));
        return getReturnValue();
    }
}
