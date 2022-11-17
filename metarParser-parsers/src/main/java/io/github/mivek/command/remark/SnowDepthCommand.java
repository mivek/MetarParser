package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author Jean-Kevin KPADEY
 */
public final class SnowDepthCommand implements Command {
    /** Pattern for the snow depth. */
    private static final Pattern SNOW_DEPTH_PATTERN = Pattern.compile("^4/(\\d{3})");
    /** The message instance. */
    private final Messages messages;

    /**
     * Constructor.
     */
    SnowDepthCommand() {
        this.messages = Messages.getInstance();
    }
    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] snowDepth = Regex.pregMatch(SNOW_DEPTH_PATTERN, remark);
        stringBuilder.append(messages.getString("Remark.Snow.Depth", Integer.parseInt(snowDepth[1]))).append(" ");
        return remark.replaceFirst(SNOW_DEPTH_PATTERN.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(SNOW_DEPTH_PATTERN, input);
    }
}
