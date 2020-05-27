package io.github.mivek.command.remark;

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
    private final Messages messages;

    /**
     * Default constructor.
     */
    WindPeakCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] windPeakParts = Regex.pregMatch(WIND_PEAK, remark);
        stringBuilder.append(messages.getString("Remark.PeakWind", windPeakParts[1], windPeakParts[2], verifyString(windPeakParts[3]), windPeakParts[4]));
        stringBuilder.append(" ");
        return remark.replaceFirst(WIND_PEAK.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(WIND_PEAK, input);
    }
}
