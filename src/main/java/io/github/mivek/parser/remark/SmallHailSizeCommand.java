package io.github.mivek.parser.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class SmallHailSizeCommand implements Command {

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public SmallHailSizeCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] hailParts = Regex.pregMatch(RemarkParser.HAIL_SIZE_LESS_THAN, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Hail.LesserThan", hailParts[1])).append(" ");
        return pRemark.replaceFirst(RemarkParser.HAIL_SIZE_LESS_THAN.pattern(), "").trim();
    }
}
