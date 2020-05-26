package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class WindShiftFropaCommand implements Command {
    /** Wind shift fopa pattern. */
    private static final Pattern WIND_SHIFT_FROPA = Pattern.compile("^WSHFT (\\d{2})?(\\d{2}) FROPA");

    /** The messages instance. */
    private final Messages messages;

    /**
     * Default constructor.
     */
    WindShiftFropaCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] windShiftParts = Regex.pregMatch(WIND_SHIFT_FROPA, remark);
        stringBuilder.append(messages.getString("Remark.WindShift.FROPA", verifyString(windShiftParts[1]), windShiftParts[2]));
        stringBuilder.append(" ");
        return remark.replaceFirst(WIND_SHIFT_FROPA.pattern(), "");
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(WIND_SHIFT_FROPA, input);
    }
}
