package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class SurfaceVisibilityCommand implements Command {

    /** The messages instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public SurfaceVisibilityCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] surfaceVisivilityParts = Regex.pregMatch(RemarkParser.SURFACE_VISIBILITY, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Surface.Visibility", surfaceVisivilityParts[1])).append(" ");
        return pRemark.replaceFirst(RemarkParser.SURFACE_VISIBILITY.pattern(), "").trim();
    }
}
