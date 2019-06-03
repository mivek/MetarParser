package io.github.mivek.parser.command.remark;

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
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    TornadicActivityBegEndCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] tornadicParts = Regex.pregMatch(TORNADIC_ACTIVITY_BEG_END, pRemark);
        pStringBuilder.append(fMessages
                .getString("Remark.Tornadic.Activity.BegEnd", fMessages.getString("Remark." + tornadicParts[1].replace(" ", "")), verifyString(tornadicParts[3]), tornadicParts[4],
                        verifyString(tornadicParts[6]), tornadicParts[7], tornadicParts[9], fMessages.getString("Converter." + tornadicParts[10]))).append(" ");
        return pRemark.replaceFirst(TORNADIC_ACTIVITY_BEG_END.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(TORNADIC_ACTIVITY_BEG_END, pInput);
    }
}
