package io.github.mivek.command.taf;

import io.github.mivek.model.TAF;

/**
 * Command for the TAFParser.
 * @author Jean-Kevin KPADEY
 */
public interface Command {
  /**
   * Method handling the part to parse.
   *
   * @param taf the metar object to handle.
   * @param part  the string to parse.
   */
  void execute(TAF taf, String part);

  /**
   * @param input the input string to test.
   * @return true if the input can be handled by the command.
   */
  boolean canParse(String input);
}
