package io.github.mivek.parser.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.parser.AbstractParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public final class VerticalVisibilityCommand implements Command {
    @Override public boolean execute(final AbstractWeatherContainer pContainer, final String pPart) {
        String[] matches = Regex.pregMatch(AbstractParser.VERTICAL_VISIBILITY, pPart);
        pContainer.setVerticalVisibility(100 * Integer.parseInt(matches[1]));
        return getReturnValue();
    }
}
