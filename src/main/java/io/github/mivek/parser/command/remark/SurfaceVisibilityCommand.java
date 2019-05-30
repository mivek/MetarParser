package io.github.mivek.parser.command.remark;

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
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public SurfaceVisibilityCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] surfaceVisivilityParts = Regex.pregMatch(SURFACE_VISIBILITY, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Surface.Visibility", surfaceVisivilityParts[1])).append(" ");
        return pRemark.replaceFirst(SURFACE_VISIBILITY.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(SURFACE_VISIBILITY, pInput);
    }
}
