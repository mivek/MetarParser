package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class HailSizeCommand implements Command {
    /** Hail size. */
    private static final Pattern HAIL_SIZE = Pattern.compile("^GR ((\\d/\\d)|((\\d) ?(\\d/\\d)?))");

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    HailSizeCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] hailParts = Regex.pregMatch(HAIL_SIZE, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Hail", hailParts[1])).append(" ");
        return pRemark.replaceFirst(HAIL_SIZE.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(HAIL_SIZE, pInput);
    }
}
