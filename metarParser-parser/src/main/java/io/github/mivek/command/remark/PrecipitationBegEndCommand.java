package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class PrecipitationBegEndCommand implements Command {
    /** Beginning and ending of precipitation. */
    private static final Pattern PRECIPITATION_BEG_END = Pattern.compile("^(([A-Z]{2})?([A-Z]{2})B(\\d{2})?(\\d{2})E(\\d{2})?(\\d{2}))");

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    PrecipitationBegEndCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] precipitationBegEnd = Regex.pregMatch(PRECIPITATION_BEG_END, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Precipitation.Beg.End", precipitationBegEnd[2] == null ? "" : fMessages.getString("Descriptive." + precipitationBegEnd[2]),
                fMessages.getString("Phenomenon." + precipitationBegEnd[3]), verifyString(precipitationBegEnd[4]), precipitationBegEnd[5], verifyString(precipitationBegEnd[6]),
                precipitationBegEnd[7])).append(" ");
        return pRemark.replaceFirst(PRECIPITATION_BEG_END.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(PRECIPITATION_BEG_END, pInput);
    }
}
