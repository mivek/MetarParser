package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author Jean-Kevin KPADEY
 */
public final class PrecipitationAmount24HourCommand implements Command {
    /** Pattern for the 24h precipitation amount. */
    private static final Pattern PRECIPITATION_PATTERN = Pattern.compile("^7(\\d{4})\\b");
    /** The message instance. */
    private final Messages messages;

    PrecipitationAmount24HourCommand() {
        this.messages = Messages.getInstance();
    }
    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] precipitationAmount = Regex.pregMatch(PRECIPITATION_PATTERN, remark);
        stringBuilder.append(messages.getString("Remark.Precipitation.Amount.24", Converter.convertPrecipitationAmount(precipitationAmount[1]))).append(" ");
        return remark.replaceFirst(PRECIPITATION_PATTERN.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(PRECIPITATION_PATTERN, input);
    }
}
