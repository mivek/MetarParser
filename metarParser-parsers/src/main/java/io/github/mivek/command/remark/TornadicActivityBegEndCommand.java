package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class TornadicActivityBegEndCommand implements Command {
    /** Tornadic activity with beginning and ending time. */
    private static final Pattern TORNADIC_ACTIVITY_BEG_END = Pattern.compile("^(TORNADO|FUNNEL CLOUD|WATERSPOUT) (B(\\d{2})?(\\d{2}))(E(\\d{2})?(\\d{2}))( (\\d+)? ([A-Z]{1,2})?)?");

    /** The message instance. */
    private final Messages messages;

    /**
     * Default constructor.
     */
    TornadicActivityBegEndCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] tornadicParts = Regex.pregMatch(TORNADIC_ACTIVITY_BEG_END, remark);
        stringBuilder.append(messages.getString("Remark.Tornadic.Activity.BegEnd", messages.getString("Remark." + tornadicParts[1].replace(" ", "")), verifyString(tornadicParts[3]), tornadicParts[4],
                verifyString(tornadicParts[6]), tornadicParts[7], tornadicParts[9], messages.getString("Converter." + tornadicParts[10]))).append(" ");
        return remark.replaceFirst(TORNADIC_ACTIVITY_BEG_END.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(TORNADIC_ACTIVITY_BEG_END, input);
    }
}
