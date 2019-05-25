package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class TornadicActivityBegEndCommand implements Command {
    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public TornadicActivityBegEndCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] tornadicParts = Regex.pregMatch(RemarkParser.TORNADIC_ACTIVITY_BEG_END, pRemark);
        pStringBuilder.append(fMessages
                .getString("Remark.Tornadic.Activity.BegEnd", fMessages.getString(RemarkParser.REMARK + tornadicParts[1].replace(" ", "")), verifyString(tornadicParts[3]), tornadicParts[4],
                        verifyString(tornadicParts[6]), tornadicParts[7], tornadicParts[9], fMessages.getString(RemarkParser.CONVERTER + tornadicParts[10]))).append(" ");
        return pRemark.replaceFirst(RemarkParser.TORNADIC_ACTIVITY_BEG_END.pattern(), "").trim();
    }
}
