package io.github.mivek.command.metar;

import io.github.mivek.command.Supplier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mivek
 */
public final class MetarParserCommandSupplier implements Supplier<Command> {
    /** List of command for the metarParser. */
    private final List<Command> commands;

    /**
     * Constructor.
     */
    public MetarParserCommandSupplier() {
        commands = buildCommandList();
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
     * @return The list of commands used by this parser.
     */
    protected List<Command> buildCommandList() {
        List<Command> commandList = new ArrayList<>();
        commandList.add(new RunwayCommand());
        commandList.add(new TemperatureCommand());
        commandList.add(new AltimeterCommand());
        commandList.add(new AltimeterMecuryCommand());

        return commandList;
    }
}

