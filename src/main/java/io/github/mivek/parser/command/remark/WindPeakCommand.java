package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class WindPeakCommand implements Command {

    /** The messages instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public WindPeakCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] windPeakParts = Regex.pregMatch(RemarkParser.WIND_PEAK, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.PeakWind", windPeakParts[1], windPeakParts[2], verifyString(windPeakParts[3]), windPeakParts[4]));
        pStringBuilder.append(" ");
        return pRemark.replaceFirst(RemarkParser.WIND_PEAK.pattern(), "").trim();
    }
}
