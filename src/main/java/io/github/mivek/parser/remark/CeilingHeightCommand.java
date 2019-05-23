package io.github.mivek.parser.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class CeilingHeightCommand implements Command {

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public CeilingHeightCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] ceilingParts = Regex.pregMatch(RemarkParser.CEILING_HEIGHT, pRemark);
        int min = Integer.parseInt(ceilingParts[1]) * 100;
        int max = Integer.parseInt(ceilingParts[2]) * 100;
        pStringBuilder.append(fMessages.getString("Remark.Ceiling.Height", min, max)).append(" ");
        return pRemark.replaceFirst(RemarkParser.CEILING_HEIGHT.pattern(), "").trim();
    }
}
