package io.github.mivek.parser;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.command.remark.CeilingHeightCommand;
import io.github.mivek.parser.command.remark.CeilingSecondLocationCommand;
import io.github.mivek.parser.command.remark.Command;
import io.github.mivek.parser.command.remark.HailSizeCommand;
import io.github.mivek.parser.command.remark.ObscurationCommand;
import io.github.mivek.parser.command.remark.PrecipitationBegEndCommand;
import io.github.mivek.parser.command.remark.PrevailingVisibilityCommand;
import io.github.mivek.parser.command.remark.SeaLevelPressureCommand;
import io.github.mivek.parser.command.remark.SecondLocationVisibilityCommand;
import io.github.mivek.parser.command.remark.SectorVisibilityCommand;
import io.github.mivek.parser.command.remark.SmallHailSizeCommand;
import io.github.mivek.parser.command.remark.SnowIncreaseCommand;
import io.github.mivek.parser.command.remark.SnowPelletsCommand;
import io.github.mivek.parser.command.remark.SurfaceVisibilityCommand;
import io.github.mivek.parser.command.remark.ThunderStormLocationCommand;
import io.github.mivek.parser.command.remark.ThunderStormLocationMovingCommand;
import io.github.mivek.parser.command.remark.TornadicActivityBegCommand;
import io.github.mivek.parser.command.remark.TornadicActivityBegEndCommand;
import io.github.mivek.parser.command.remark.TornadicActivityEndCommand;
import io.github.mivek.parser.command.remark.TowerVisibilityCommand;
import io.github.mivek.parser.command.remark.VariableSkyCommand;
import io.github.mivek.parser.command.remark.VariableSkyHeightCommand;
import io.github.mivek.parser.command.remark.VirgaDirectionCommand;
import io.github.mivek.parser.command.remark.WindPeakCommand;
import io.github.mivek.parser.command.remark.WindShiftCommand;
import io.github.mivek.parser.command.remark.WindShiftFropaCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.logging.Logger;

/**
 * @author mivek
 */
public final class RemarkParser {

    /** Constant for Remark. */
    private static final String REMARK = "Remark.";
    /** The instance of the parser. */
    private static final RemarkParser INSTANCE = new RemarkParser();
    /** The logger instance. */
    private static final Logger LOGGER = Logger.getLogger(RemarkParser.class.getName());

    /** Message instance. */
    private final Messages fMessages;

    /** List of commands. */
    private final List<Command> commands;

    /***
     * Private constructor.
     */
    private RemarkParser() {
        fMessages = Messages.getInstance();
        commands = buildCommandList();
    }

    /**
     * @param pRemark the remark to parse.
     * @return the remark string
     */
    public String parse(final String pRemark) {
        String rmk = pRemark;
        StringBuilder sb = new StringBuilder();
        while (!rmk.equals("")) {
            boolean found = false;
            for (Command command : commands) {
                if (command.canParse(rmk)) {
                    found = true;
                    try {
                        rmk = command.execute(rmk, sb);
                    } catch (MissingResourceException e) {
                        rmk = defaultRemark(rmk, sb);
                    }
                    break;
                }
            }
            if (!found) {
                rmk = defaultRemark(rmk, sb);
            }
        }
        return sb.toString();
    }

    /**
     * Handle the default behavior when a remark token is not recognized by regex.
     *
     * @param pRmk the remark string
     * @param pSb  The string builder containing the parsed message of the remark
     * @return the remark string.
     */
    private String defaultRemark(final String pRmk, final StringBuilder pSb) {
        String rmk = pRmk;
        String[] strSlit = rmk.split(" ", 2);
        String token = strSlit[0];
        try {
            token = fMessages.getString(REMARK + token);
        } catch (MissingResourceException e) {
            LOGGER.info("Remark token \"" + token + "\" is unknown.");
        }
        pSb.append(token).append(" ");
        rmk = strSlit.length == 1 ? "" : strSlit[1];
        return rmk;
    }

    /**
     * Build the command list.
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
        return commandList;
    }

    /**
     * @return the instance of the parser.
     */
    public static RemarkParser getInstance() {
        return INSTANCE;
    }
}
