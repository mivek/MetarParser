package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class SnowIncreaseCommand implements Command {
    /** Snow increasing rapidly. */
    private static final Pattern SNOW_INCR_RAPIDLY = Pattern.compile("^SNINCR (\\d+)/(\\d+)");

    /** The message instance. */
    private final Messages messages;

    /**
     * Default constructor.
     */
    SnowIncreaseCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] snowParts = Regex.pregMatch(SNOW_INCR_RAPIDLY, remark);
        stringBuilder.append(messages.getString("Remark.Snow.Increasing.Rapidly", snowParts[1], snowParts[2])).append(" ");
        return remark.replaceFirst(SNOW_INCR_RAPIDLY.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(SNOW_INCR_RAPIDLY, input);
    }
}
