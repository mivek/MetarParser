package io.github.mivek.parser.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Visibility;
import io.github.mivek.parser.AbstractParser;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public final class MainVisibilityCommand implements Command {
    @Override public boolean execute(final AbstractWeatherContainer pContainer, final String pPart) {
        String[] matches = Regex.pregMatch(AbstractParser.MAIN_VISIBILITY_REGEX, pPart);
        if (pContainer.getVisibility() == null) {
            pContainer.setVisibility(new Visibility());
        }
        pContainer.getVisibility().setMainVisibility(Converter.convertVisibility(matches[1]));
        return getReturnValue();
    }
}
