package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class ThunderStormLocationMovingCommand implements Command {

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public ThunderStormLocationMovingCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] thunderStormParts = Regex.pregMatch(RemarkParser.THUNDERSTORM_LOCATION_MOVING, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Thunderstorm.Location.Moving", fMessages.getString(RemarkParser.CONVERTER + thunderStormParts[1]),
                fMessages.getString(RemarkParser.CONVERTER + thunderStormParts[2]))).append(" ");
        return pRemark.replaceFirst(RemarkParser.THUNDERSTORM_LOCATION_MOVING.pattern(), "").trim();
    }
}
