package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class TornadicActivityEndCommand implements Command {
    /** Tornadic activity with ending time. */
    private static final Pattern TORNADIC_ACTIVITY_ENDING = Pattern.compile("^(TORNADO|FUNNEL CLOUD|WATERSPOUT) (E(\\d{2})?(\\d{2}))( (\\d+)? ([A-Z]{1,2})?)?");

    /** The message instance. */
    private final Messages messages;

    /**
     * Default constructor.
     */
    TornadicActivityEndCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] tornadicParts = Regex.pregMatch(TORNADIC_ACTIVITY_ENDING, remark);
        stringBuilder.append(messages
                .getString("Remark.Tornadic.Activity.Ending", messages.getString("Remark." + tornadicParts[1].replace(" ", "")), verifyString(tornadicParts[3]), tornadicParts[4], tornadicParts[6],
                        messages.getString("Converter." + tornadicParts[7]))).append(" ");
        return remark.replaceFirst(TORNADIC_ACTIVITY_ENDING.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(TORNADIC_ACTIVITY_ENDING, input);
    }
}
