package io.github.mivek.command.remark;

import io.github.mivek.command.Supplier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mivek
 */
public final class RemarkCommandSupplier implements Supplier<Command> {
    /** List of commands. */
    private final List<Command> commands;

    /** The default command to execute. */
    private final Command defaultCommand;

    /**
     * Constructor.
     */
    public RemarkCommandSupplier() {
        defaultCommand = new DefaultCommand();
        commands = buildCommandList();
    }

    @Override
    public Command get(final String string) {
        for (Command command : commands) {
            if (command.canParse(string)) {
                return command;
            }
        }
        return defaultCommand;
    }

    /**
     * Build the command list.
     *
     * @return the list
     */
    protected List<Command> buildCommandList() {
        List<Command> commandList = new ArrayList<>();
        commandList.add(new WindPeakCommand());
        commandList.add(new WindShiftFropaCommand());
        commandList.add(new WindShiftCommand());
        commandList.add(new TowerVisibilityCommand());
        commandList.add(new SurfaceVisibilityCommand());
        commandList.add(new PrevailingVisibilityCommand());
        commandList.add(new SecondLocationVisibilityCommand());
        commandList.add(new SectorVisibilityCommand());
        commandList.add(new TornadicActivityBegEndCommand());
        commandList.add(new TornadicActivityBegCommand());
        commandList.add(new TornadicActivityEndCommand());
        commandList.add(new PrecipitationBegEndCommand());
        commandList.add(new PrecipitationBegCommand());
        commandList.add(new PrecipitationEndCommand());
        commandList.add(new ThunderStormLocationMovingCommand());
        commandList.add(new ThunderStormLocationCommand());
        commandList.add(new SmallHailSizeCommand());
        commandList.add(new HailSizeCommand());
        commandList.add(new SnowPelletsCommand());
        commandList.add(new VirgaDirectionCommand());
        commandList.add(new CeilingHeightCommand());
        commandList.add(new ObscurationCommand());
        commandList.add(new VariableSkyHeightCommand());
        commandList.add(new VariableSkyCommand());
        commandList.add(new CeilingSecondLocationCommand());
        commandList.add(new SeaLevelPressureCommand());
        commandList.add(new SnowIncreaseCommand());
        commandList.add(new HourlyMaximumMinimumTemperatureCommand());
        commandList.add(new HourlyMaximumTemperatureCommand());
        commandList.add(new HourlyMinimumTemperatureCommand());
        commandList.add(new HourlyPrecipitationAmountCommand());
        commandList.add(new HourlyPressureCommand());
        commandList.add(new HourlyTemperatureDewPointCommand());
        commandList.add(new IceAccretionCommand());
        commandList.add(new PrecipitationAmount36HourCommand());
        commandList.add(new PrecipitationAmount24HourCommand());
        commandList.add(new SnowDepthCommand());
        commandList.add(new SunshineDurationCommand());
        commandList.add(new WaterEquivalentSnowCommand());
        return commandList;
    }

    /**
     * @return the default command to execute.
     */
    public Command getDefaultCommand() {
        return defaultCommand;
    }
}

