package io.github.mivek.parser.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class WindShiftCommand implements Command {

    /** The messages instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public WindShiftCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] windShiftParts = Regex.pregMatch(RemarkParser.WIND_SHIFT, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.WindShift", verifyString(windShiftParts[1]), windShiftParts[2]));
        pStringBuilder.append(" ");
        return pRemark.replaceFirst(RemarkParser.WIND_SHIFT.pattern(), "").trim();
    }
}
