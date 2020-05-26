package io.github.mivek.command.remark;

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
    private final Messages messages;

    /**
     * Default constructor.
     */
    WindShiftCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] windShiftParts = Regex.pregMatch(WIND_SHIFT, pRemark);
        pStringBuilder.append(messages.getString("Remark.WindShift", verifyString(windShiftParts[1]), windShiftParts[2]));
        pStringBuilder.append(" ");
        return pRemark.replaceFirst(WIND_SHIFT.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String pInput) {
        return Regex.find(WIND_SHIFT, pInput);
    }
}
