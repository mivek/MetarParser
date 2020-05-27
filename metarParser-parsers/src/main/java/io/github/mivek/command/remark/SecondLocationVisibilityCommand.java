package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class SecondLocationVisibilityCommand implements Command {

    /** Visibility at second location. */
    private static final Pattern SECOND_LOCATION_VISIBILITY = Pattern.compile("^VIS ((\\d)*( )?(\\d?/?\\d)) (\\w+)");

    /** The message instance. */
    private final Messages messages;

    /**
     * Default constructor.
     */
    SecondLocationVisibilityCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] secondLocationVisibilityParts = Regex.pregMatch(SECOND_LOCATION_VISIBILITY, remark);
        stringBuilder.append(messages.getString("Remark.Second.Location.Visibility", secondLocationVisibilityParts[1], secondLocationVisibilityParts[5])).append(" ");
        return remark.replaceFirst(SECOND_LOCATION_VISIBILITY.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(SECOND_LOCATION_VISIBILITY, input);
    }
}
