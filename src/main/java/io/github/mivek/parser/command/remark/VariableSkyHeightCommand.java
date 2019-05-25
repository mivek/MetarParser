package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class VariableSkyHeightCommand implements Command {

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public VariableSkyHeightCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] variableSky = Regex.pregMatch(RemarkParser.VARIABLE_SKY_HEIGHT, pRemark);
        String layer1 = fMessages.getString(RemarkParser.CLOUD_QUANTITY + variableSky[1]);
        int height = Integer.parseInt(variableSky[2]) * 100;
        String layer2 = fMessages.getString(RemarkParser.CLOUD_QUANTITY + variableSky[3]);
        pStringBuilder.append(fMessages.getString("Remark.Variable.Sky.Condition.Height", height, layer1, layer2)).append(" ");
        return pRemark.replaceFirst(RemarkParser.VARIABLE_SKY_HEIGHT.pattern(), "").trim();
    }
}
