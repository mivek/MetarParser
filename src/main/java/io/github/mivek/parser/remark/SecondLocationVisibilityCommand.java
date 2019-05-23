package io.github.mivek.parser.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class SecondLocationVisibilityCommand implements Command {

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public SecondLocationVisibilityCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] secondLocationVisibilityParts = Regex.pregMatch(RemarkParser.SECOND_LOCATION_VISIBILITY, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Second.Location.Visibility", secondLocationVisibilityParts[1], secondLocationVisibilityParts[5])).append(" ");
        return pRemark.replaceFirst(RemarkParser.SECOND_LOCATION_VISIBILITY.pattern(), "").trim();
    }
}
