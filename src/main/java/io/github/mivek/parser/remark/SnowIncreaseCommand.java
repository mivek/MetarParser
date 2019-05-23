package io.github.mivek.parser.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class SnowIncreaseCommand implements Command {
    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public SnowIncreaseCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] snowParts = Regex.pregMatch(RemarkParser.SNOW_INCR_RAPIDLY, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Snow.Increasing.Rapidly", snowParts[1], snowParts[2])).append(" ");
        return pRemark.replaceFirst(RemarkParser.SNOW_INCR_RAPIDLY.pattern(), "").trim();
    }
}
