package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.RemarkParser;
import io.github.mivek.utils.Regex;

/**
 * @author mivek
 */
public class VirgaDirectionCommand implements Command {
    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public VirgaDirectionCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public final String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] virgaDirection = Regex.pregMatch(RemarkParser.VIRGA_DIRECTION, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Virga.Direction", fMessages.getString(RemarkParser.CONVERTER + virgaDirection[1]))).append(" ");
        return pRemark.replaceFirst(RemarkParser.VIRGA_DIRECTION.pattern(), "").trim();
    }
}
