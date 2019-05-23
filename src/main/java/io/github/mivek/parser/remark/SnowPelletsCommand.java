package io.github.mivek.parser.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class SnowPelletsCommand implements Command {

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public SnowPelletsCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] intensityParts = Regex.pregMatch(RemarkParser.SNOW_PELLETS_INTENSITY, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Snow.Pellets", fMessages.getString(RemarkParser.REMARK + intensityParts[1]))).append(" ");
        return pRemark.replaceFirst(RemarkParser.SNOW_PELLETS_INTENSITY.pattern(), "").trim();
    }
}
