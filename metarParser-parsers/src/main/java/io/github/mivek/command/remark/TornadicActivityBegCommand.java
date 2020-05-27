package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class TornadicActivityBegCommand implements Command {
    /** Tornadic activity with beginning time. */
    private static final Pattern TORNADIC_ACTIVITY_BEGINNING = Pattern.compile("^(TORNADO|FUNNEL CLOUD|WATERSPOUT) (B(\\d{2})?(\\d{2}))( (\\d+)? ([A-Z]{1,2})?)?");
    /** The message instance. */
    private final Messages messages;

    /**
     * Default constructor.
     */
    TornadicActivityBegCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] tornadicParts = Regex.pregMatch(TORNADIC_ACTIVITY_BEGINNING, remark);
        stringBuilder.append(messages
                .getString("Remark.Tornadic.Activity.Beginning", messages.getString("Remark." + tornadicParts[1].replace(" ", "")), verifyString(tornadicParts[3]), tornadicParts[4], tornadicParts[6],
                        messages.getString("Converter." + tornadicParts[7]))).append(" ");
        return remark.replaceFirst(TORNADIC_ACTIVITY_BEGINNING.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(TORNADIC_ACTIVITY_BEGINNING, input);
    }
}
