package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class ThunderStormLocationMovingCommand implements Command {
    /** Thunderstorm location moving. */
    private static final Pattern THUNDERSTORM_LOCATION_MOVING = Pattern.compile("^TS ([A-Z]{2}) MOV ([A-Z]{2})");

    /** The message instance. */
    private final Messages messages;

    /**
     * Default constructor.
     */
    ThunderStormLocationMovingCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] thunderStormParts = Regex.pregMatch(THUNDERSTORM_LOCATION_MOVING, pRemark);
        pStringBuilder
                .append(messages.getString("Remark.Thunderstorm.Location.Moving", messages.getString("Converter." + thunderStormParts[1]), messages.getString("Converter." + thunderStormParts[2])))
                .append(" ");
        return pRemark.replaceFirst(THUNDERSTORM_LOCATION_MOVING.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String pInput) {
        return Regex.find(THUNDERSTORM_LOCATION_MOVING, pInput);
    }
}
