package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class ObscurationCommand implements Command {
    /** Obscuration pattern. */
    private static final Pattern OBSCURATION = Pattern.compile("^([A-Z]{2}) ([A-Z]{3})(\\d{3})");

    /** The message instance. */
    private final Messages messages;

    /**
     * Default constructor.
     */
    ObscurationCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] obscuration = Regex.pregMatch(OBSCURATION, remark);
        String layer = messages.getString("CloudQuantity." + obscuration[2]);
        int height = Integer.parseInt(obscuration[3]) * 100;
        String obscDetail = messages.getString("Phenomenon." + obscuration[1]);
        stringBuilder.append(messages.getString("Remark.Obscuration", layer, height, obscDetail)).append(" ");
        return remark.replaceFirst(OBSCURATION.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(OBSCURATION, input);
    }
}
