package io.github.mivek.parser.command.common;

import io.github.mivek.model.AbstractWeatherContainer;

/**
 * @author mivek
 */
public interface Command {

    /**
     * Handles the pPart and updates the pContainer.
     *
     * @param pContainer the container to update.
     * @param pPart      the string to parse.
     * @return true if the part has been properly handled false otherwise
     */
    boolean execute(AbstractWeatherContainer pContainer, String pPart);

    /**
     * @return the default return value of a command.
     */
    default boolean getReturnValue() {
        return true;
    }
}
