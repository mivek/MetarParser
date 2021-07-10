package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author Jean-Kevin KPADEY
 */
public final class IceAccretionCommand implements Command {
    /** Pattern for the ice accretion. */
    private static final Pattern ICE_ACCRETION_PATTERN = Pattern.compile("^l(\\d)(\\d{3})\\b");
    /** The message instance. */
    private final Messages messages;

    IceAccretionCommand() {
        this.messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] matches = Regex.pregMatch(ICE_ACCRETION_PATTERN, remark);
        stringBuilder.append(messages.getString("Remark.Ice.Accretion.Amount", matches[2], matches[1]))
                .append(" ");
        return remark.replaceFirst(ICE_ACCRETION_PATTERN.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(ICE_ACCRETION_PATTERN, input);
    }
}
