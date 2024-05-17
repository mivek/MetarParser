package io.github.mivek.command.metar;

import io.github.mivek.exception.ParseException;
import io.github.mivek.model.Metar;

/**
 * Command for the metarParser.
 *
 * @author mivek
 */
public interface Command {

    /**
     * Method handling the part to parse.
     *
     * @param metar the metar object to handle.
     * @param part  the string to parse.
     * @throws ParseException when runway information cannot be parsed.
     */
    void execute(Metar metar, String part) throws ParseException;

    /**
     * @param input the input string to test.
     * @return true if the input can be handled by the command.
     */
    boolean canParse(String input);
}
