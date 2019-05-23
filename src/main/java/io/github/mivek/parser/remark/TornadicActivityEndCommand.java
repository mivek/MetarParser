package io.github.mivek.parser.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class TornadicActivityEndCommand implements Command {

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public TornadicActivityEndCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] tornadicParts = Regex.pregMatch(RemarkParser.TORNADIC_ACTIVITY_ENDING, pRemark);
        pStringBuilder.append(fMessages
                .getString("Remark.Tornadic.Activity.Ending", fMessages.getString(RemarkParser.REMARK + tornadicParts[1].replace(" ", "")), verifyString(tornadicParts[3]), tornadicParts[4],
                        tornadicParts[6], fMessages.getString(RemarkParser.CONVERTER + tornadicParts[7]))).append(" ");
        return pRemark.replaceFirst(RemarkParser.TORNADIC_ACTIVITY_ENDING.pattern(), "").trim();
    }
}
