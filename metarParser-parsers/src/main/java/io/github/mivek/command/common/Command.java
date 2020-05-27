package io.github.mivek.command.common;

import io.github.mivek.model.AbstractWeatherContainer;

/**
 * @author mivek
 */
public interface Command {

    /**
     * Handles the pqrt and updates the container.
     *
     * @param container the container to update.
     * @param part      the string to parse.
     * @return true if the part has been properly handled false otherwise
     */
    boolean execute(AbstractWeatherContainer container, String part);

    /**
     * @return the default return value of a command.
     */
    default boolean getReturnValue() {
        return true;
    }

    /**
     * @param input the input string to test.
     * @return true if the input can be handled by the command.
     */
    boolean canParse(String input);
}
