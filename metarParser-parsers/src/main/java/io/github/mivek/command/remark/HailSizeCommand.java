package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class HailSizeCommand implements Command {
    /** Hail size. */
    private static final Pattern HAIL_SIZE = Pattern.compile("^GR ((\\d/\\d)|((\\d) ?(\\d/\\d)?))");

    /** The message instance. */
    private final Messages messages;

    /**
     * Default constructor.
     */
    HailSizeCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] hailParts = Regex.pregMatch(HAIL_SIZE, remark);
        stringBuilder.append(messages.getString("Remark.Hail", hailParts[1])).append(" ");
        return remark.replaceFirst(HAIL_SIZE.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(HAIL_SIZE, input);
    }
}
