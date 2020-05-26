package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class SurfaceVisibilityCommand implements Command {
    /** Surface visibility. */
    private static final Pattern SURFACE_VISIBILITY = Pattern.compile("^SFC VIS ((\\d)*( )?(\\d?/?\\d))");

    /** The messages instance. */
    private final Messages messages;

    /**
     * Default constructor.
     */
    SurfaceVisibilityCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] surfaceVisivilityParts = Regex.pregMatch(SURFACE_VISIBILITY, remark);
        stringBuilder.append(messages.getString("Remark.Surface.Visibility", surfaceVisivilityParts[1])).append(" ");
        return remark.replaceFirst(SURFACE_VISIBILITY.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(SURFACE_VISIBILITY, input);
    }
}
