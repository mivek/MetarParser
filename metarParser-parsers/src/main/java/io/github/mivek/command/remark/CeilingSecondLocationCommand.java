package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class CeilingSecondLocationCommand implements Command {
    /** Ceiling height second location. */
    private static final Pattern CEILING_SECOND_LOCATION = Pattern.compile("^CIG (\\d{3}) (\\w+)");

    /** The messages instance. */
    private final Messages messages;

    /**
     * Default constructor.
     */
    CeilingSecondLocationCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] ceilingParts = Regex.pregMatch(CEILING_SECOND_LOCATION, remark);
        int height = 100 * Integer.parseInt(ceilingParts[1]);
        String location = ceilingParts[2];
        stringBuilder.append(messages.getString("Remark.Ceiling.Second.Location", height, location)).append(" ");
        return remark.replaceFirst(CEILING_SECOND_LOCATION.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(CEILING_SECOND_LOCATION, input);
    }
}
