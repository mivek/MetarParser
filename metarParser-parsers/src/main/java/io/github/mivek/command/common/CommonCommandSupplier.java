package io.github.mivek.command.common;

import io.github.mivek.command.Supplier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mivek
 */
public final class CommonCommandSupplier implements Supplier<Command> {
    /** The list of commands. */
    private final List<Command> commands;

    /**
     * Default constructor.
     */
    public CommonCommandSupplier() {
        commands = buildCommands();
    }

    @Override
    public Command get(final String string) {
        for (Command command : commands) {
            if (command.canParse(string)) {
                return command;
            }
        }
        return null;
    }

    /**
     * Builds list of commands.
     *
     * @return the list of commands.
     */
    protected List<Command> buildCommands() {
        List<Command> commandList = new ArrayList<>();
        commandList.add(new WindShearCommand());
        commandList.add(new WindCommand());
        commandList.add(new WindExtremeCommand());
        commandList.add(new MainVisibilityCommand());
        commandList.add(new MainVisibilityNauticalMilesCommand());
        commandList.add(new MinimalVisibilityCommand());
        commandList.add(new VerticalVisibilityCommand());
        commandList.add(new CloudCommand());
        return commandList;
    }
}

