package io.github.mivek.parser.command.metar;

import io.github.mivek.parser.command.CommandSupplier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mivek
 */
public final class MetarParserCommandSupplier implements CommandSupplier<Command> {
    /** List of command for the metarParser. */
    private final List<Command> commands;

    /**
     * Constructor.
     */
    public MetarParserCommandSupplier() {
        commands = buildCommandList();
    }

    @Override public Command get(final String pString) {
        for (Command command : commands) {
            if (command.canParse(pString)) {
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

