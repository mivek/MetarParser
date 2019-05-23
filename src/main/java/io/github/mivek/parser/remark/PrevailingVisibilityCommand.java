package io.github.mivek.parser.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class PrevailingVisibilityCommand implements Command {
    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public PrevailingVisibilityCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] visibilityParts = Regex.pregMatch(RemarkParser.VARIABLE_PREVAILING_VISIBILITY, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Variable.Prevailing.Visibility", visibilityParts[1], visibilityParts[5])).append(" ");
        return pRemark.replaceFirst(RemarkParser.VARIABLE_PREVAILING_VISIBILITY.pattern(), "").trim();
    }
}
