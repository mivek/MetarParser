package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author Jean-Kevin KPADEY
 */
public final class WaterEquivalentSnowCommand implements Command {
    /** Pattern for the water equivalent in snow. */
    private static final Pattern WATER_EQUIVALENT_SNOW_GROUND_PATTERN = Pattern.compile("^933(\\d{3})\\b");
    /** The message instance. */
    private final Messages messages;

    WaterEquivalentSnowCommand() {
        this.messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] precipitationAmount = Regex.pregMatch(WATER_EQUIVALENT_SNOW_GROUND_PATTERN, remark);
        stringBuilder.append(messages.getString("Remark.Water.Equivalent.Snow.Ground", Float.parseFloat(precipitationAmount[1]) / 10)).append(" ");
        return remark.replaceFirst(WATER_EQUIVALENT_SNOW_GROUND_PATTERN.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(WATER_EQUIVALENT_SNOW_GROUND_PATTERN, input);
    }
}
