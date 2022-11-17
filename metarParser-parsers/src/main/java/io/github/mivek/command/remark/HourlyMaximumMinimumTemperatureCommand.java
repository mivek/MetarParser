package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;


/**
 * @author Jean-Kevin KPADEY
 */
public final class HourlyMaximumMinimumTemperatureCommand implements Command {
    /** Pattern for the hourly maximum and minimum temperature. */
    private static final Pattern TEMPERATURE_PATTERN = Pattern.compile("^4([01])(\\d{3})([01])(\\d{3})\\b");
    /** The message instance. */
    private final Messages messages;

    /**
     * Constructor.
     */
    HourlyMaximumMinimumTemperatureCommand() {
        this.messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] matches = Regex.pregMatch(TEMPERATURE_PATTERN, remark);
        stringBuilder.append(messages.getString("Remark.Hourly.Maximum.Minimum.Temperature", Converter.convertTemperature(matches[1], matches[2]), Converter.convertTemperature(matches[3], matches[4]))).append(" ");
        return remark.replaceFirst(TEMPERATURE_PATTERN.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(TEMPERATURE_PATTERN, input);
    }
}
