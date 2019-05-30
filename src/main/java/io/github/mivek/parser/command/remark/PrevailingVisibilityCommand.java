package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class PrevailingVisibilityCommand implements Command {
    /** Variable prevailing visibility. */
    private static final Pattern VARIABLE_PREVAILING_VISIBILITY = Pattern.compile("^VIS ((\\d)*( )?(\\d?/?\\d))V((\\d)*( )?(\\d?/?\\d))");
    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public PrevailingVisibilityCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] visibilityParts = Regex.pregMatch(VARIABLE_PREVAILING_VISIBILITY, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Variable.Prevailing.Visibility", visibilityParts[1], visibilityParts[5])).append(" ");
        return pRemark.replaceFirst(VARIABLE_PREVAILING_VISIBILITY.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(VARIABLE_PREVAILING_VISIBILITY, pInput);
    }
}
