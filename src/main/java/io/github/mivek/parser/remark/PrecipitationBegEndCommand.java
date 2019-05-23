package io.github.mivek.parser.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class PrecipitationBegEndCommand implements Command {

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public PrecipitationBegEndCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] precipitationBegEnd = Regex.pregMatch(RemarkParser.PRECIPITATION_BEG_END, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Precipitation.Beg.End", precipitationBegEnd[2] == null ? "" : fMessages.getString("Descriptive." + precipitationBegEnd[2]),
                fMessages.getString("Phenomenon." + precipitationBegEnd[3]), verifyString(precipitationBegEnd[4]), precipitationBegEnd[5], verifyString(precipitationBegEnd[6]),
                precipitationBegEnd[7])).append(" ");
        return pRemark.replaceFirst(RemarkParser.PRECIPITATION_BEG_END.pattern(), "").trim();
    }
}
