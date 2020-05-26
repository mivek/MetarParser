package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class CeilingHeightCommand implements Command {
    /** Ceiling height. */
    private static final Pattern CEILING_HEIGHT = Pattern.compile("^CIG (\\d{3})V(\\d{3})");

    /** The message instance. */
    private final Messages messages;

    /**
     * Default constructor.
     */
    CeilingHeightCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] ceilingParts = Regex.pregMatch(CEILING_HEIGHT, remark);
        int min = Integer.parseInt(ceilingParts[1]) * 100;
        int max = Integer.parseInt(ceilingParts[2]) * 100;
        stringBuilder.append(messages.getString("Remark.Ceiling.Height", min, max)).append(" ");
        return remark.replaceFirst(CEILING_HEIGHT.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(CEILING_HEIGHT, input);
    }
}
