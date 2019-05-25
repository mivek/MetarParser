package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class VariableSkyCommand implements Command {
    /** The messages instance. */
    private Messages fMessages;

    /**
     * Default constructor.
     */
    public VariableSkyCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] variableSky = Regex.pregMatch(RemarkParser.VARIABLE_SKY, pRemark);
        String layer1 = fMessages.getString(RemarkParser.CLOUD_QUANTITY + variableSky[1]);
        String layer2 = fMessages.getString(RemarkParser.CLOUD_QUANTITY + variableSky[2]);
        pStringBuilder.append(fMessages.getString("Remark.Variable.Sky.Condition", layer1, layer2)).append(" ");
        return pRemark.replaceFirst(RemarkParser.VARIABLE_SKY.pattern(), "").trim();
    }
}

