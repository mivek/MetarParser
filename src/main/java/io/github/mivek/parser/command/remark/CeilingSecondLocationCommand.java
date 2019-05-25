package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class CeilingSecondLocationCommand implements Command {
    /** The messages instance. */
    private Messages fMessages;

    /**
     * Default constructor.
     */
    public CeilingSecondLocationCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] ceilingParts = Regex.pregMatch(RemarkParser.CEILING_SECOND_LOCATION, pRemark);
        int height = 100 * Integer.parseInt(ceilingParts[1]);
        String location = ceilingParts[2];
        pStringBuilder.append(fMessages.getString("Remark.Ceiling.Second.Location", height, location)).append(" ");
        return pRemark.replaceFirst(RemarkParser.CEILING_SECOND_LOCATION.pattern(), "").trim();
    }
}
