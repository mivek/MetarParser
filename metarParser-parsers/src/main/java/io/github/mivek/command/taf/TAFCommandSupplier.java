package io.github.mivek.command.taf;

import io.github.mivek.command.Supplier;
import java.util.List;

/**
 * Command supplier for the TAFParser.
 * @author Jean-Kevin KPADEY
 */
public final class TAFCommandSupplier implements Supplier<Command> {
  /** List of commands. */
  private final List<Command> commands;

  /**
   * Constructor.
   */
  public TAFCommandSupplier() {
    commands = buildCommandList();
  }
  @Override
  public Command get(final String string) {
    for (Command command: commands) {
      if (command.canParse(string)) {
        return command;
      }
    }
    return null;
  }

  /**
   * @return List of specific commands used by the TAFParser.
   */
  List<Command> buildCommandList() {
    return List.of(new TurbulenceCommand(), new IcingCommand());
  }
}
