package io.github.mivek.command.remark;

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
    private final Messages messages;

    /**
     * Default constructor.
     */
    ThunderStormLocationCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] thunderStormParts = Regex.pregMatch(THUNDERSTORM_LOCATION, remark);
        stringBuilder.append(messages.getString("Remark.Thunderstorm.Location", messages.getString("Converter." + thunderStormParts[1]))).append(" ");
        return remark.replaceFirst(THUNDERSTORM_LOCATION.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(THUNDERSTORM_LOCATION, input);
    }
}
