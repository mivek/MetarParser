package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class WindPeakCommand implements Command {

    /** Wind peak pattern. */
    private static final Pattern WIND_PEAK = Pattern.compile("^PK WND (\\d{3})(\\d{2,3})/(\\d{2})?(\\d{2})");

    /** The messages instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    WindPeakCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] windPeakParts = Regex.pregMatch(WIND_PEAK, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.PeakWind", windPeakParts[1], windPeakParts[2], verifyString(windPeakParts[3]), windPeakParts[4]));
        pStringBuilder.append(" ");
        return pRemark.replaceFirst(WIND_PEAK.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(WIND_PEAK, pInput);
    }
}
