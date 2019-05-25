package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class HailSizeCommand implements Command {
    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public HailSizeCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] hailParts = Regex.pregMatch(RemarkParser.HAIL_SIZE, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Hail", hailParts[1])).append(" ");
        return pRemark.replaceFirst(RemarkParser.HAIL_SIZE.pattern(), "").trim();
    }
}
