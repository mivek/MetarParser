package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class ObscurationCommand implements Command {

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public ObscurationCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] obscuration = Regex.pregMatch(RemarkParser.OBSCURATION, pRemark);
        String layer = fMessages.getString(RemarkParser.CLOUD_QUANTITY + obscuration[2]);
        int height = Integer.parseInt(obscuration[3]) * 100;
        String obscDetail = fMessages.getString("Phenomenon." + obscuration[1]);
        pStringBuilder.append(fMessages.getString("Remark.Obscuration", layer, height, obscDetail)).append(" ");
        return pRemark.replaceFirst(RemarkParser.OBSCURATION.pattern(), "").trim();
    }
}
