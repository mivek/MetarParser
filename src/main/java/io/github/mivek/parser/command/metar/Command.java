package io.github.mivek.parser.command.metar;

import io.github.mivek.model.Metar;

/**
 * Command for the metarParser.
 *
 * @author mivek
 */
public interface Command {

    /**
     * Methode handling the pPart to parse.
     *
     * @param pMetar the metar object to handle.
     * @param pPart  the string to parse.
     */
    void execute(Metar pMetar, String pPart);
}
