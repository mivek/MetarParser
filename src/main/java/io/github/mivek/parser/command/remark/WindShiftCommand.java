package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class WindShiftCommand implements Command {

    /** Wind shift pattern. */
    private static final Pattern WIND_SHIFT = Pattern.compile("^WSHFT (\\d{2})?(\\d{2})");

    /** The messages instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    WindShiftCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] windShiftParts = Regex.pregMatch(WIND_SHIFT, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.WindShift", verifyString(windShiftParts[1]), windShiftParts[2]));
        pStringBuilder.append(" ");
        return pRemark.replaceFirst(WIND_SHIFT.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(WIND_SHIFT, pInput);
    }
}
