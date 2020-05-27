package io.github.mivek.command.remark;

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
    private final Messages messages;

    /**
     * Default constructor.
     */
    PrevailingVisibilityCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] visibilityParts = Regex.pregMatch(VARIABLE_PREVAILING_VISIBILITY, remark);
        stringBuilder.append(messages.getString("Remark.Variable.Prevailing.Visibility", visibilityParts[1], visibilityParts[5])).append(" ");
        return remark.replaceFirst(VARIABLE_PREVAILING_VISIBILITY.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(VARIABLE_PREVAILING_VISIBILITY, input);
    }
}
