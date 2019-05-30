package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class VirgaDirectionCommand implements Command {
    /** Virga with direction. */
    private static final Pattern VIRGA_DIRECTION = Pattern.compile("^VIRGA ([A-Z]{2})");

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    public VirgaDirectionCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] virgaDirection = Regex.pregMatch(VIRGA_DIRECTION, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Virga.Direction", fMessages.getString("Converter." + virgaDirection[1]))).append(" ");
        return pRemark.replaceFirst(VIRGA_DIRECTION.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(VIRGA_DIRECTION, pInput);
    }
}
