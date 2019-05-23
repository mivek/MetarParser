package io.github.mivek.parser.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class ThunderStormLocationCommand implements Command {

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public ThunderStormLocationCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] thunderStormParts = Regex.pregMatch(RemarkParser.THUNDERSTORM_LOCATION, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Thunderstorm.Location", fMessages.getString(RemarkParser.CONVERTER + thunderStormParts[1]))).append(" ");
        return pRemark.replaceFirst(RemarkParser.THUNDERSTORM_LOCATION.pattern(), "").trim();
    }
}
