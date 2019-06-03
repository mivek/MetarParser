package io.github.mivek.parser.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class ThunderStormLocationCommand implements Command {

    /** Thunderstorm location. */
    private static final Pattern THUNDERSTORM_LOCATION = Pattern.compile("^TS ([A-Z]{2})");

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    ThunderStormLocationCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] thunderStormParts = Regex.pregMatch(THUNDERSTORM_LOCATION, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.Thunderstorm.Location", fMessages.getString("Converter." + thunderStormParts[1]))).append(" ");
        return pRemark.replaceFirst(THUNDERSTORM_LOCATION.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(THUNDERSTORM_LOCATION, pInput);
    }
}
