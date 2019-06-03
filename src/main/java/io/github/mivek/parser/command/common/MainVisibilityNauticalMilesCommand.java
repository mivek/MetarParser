package io.github.mivek.parser.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Visibility;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class MainVisibilityNauticalMilesCommand implements Command {
    /** Pattern for the main visibility in SM. */
    private static final Pattern MAIN_VISIBILITY_SM_REGEX = Pattern.compile("^(\\d)*(\\s)?((\\d\\/\\d)?SM)$");


    @Override public boolean execute(final AbstractWeatherContainer pContainer, final String pPart) {
        String[] matches = Regex.pregMatch(MAIN_VISIBILITY_SM_REGEX, pPart);
        if (pContainer.getVisibility() == null) {
            pContainer.setVisibility(new Visibility());
        }
        pContainer.getVisibility().setMainVisibility(matches[0]);
        return getReturnValue();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(MAIN_VISIBILITY_SM_REGEX, pInput);
    }
}
