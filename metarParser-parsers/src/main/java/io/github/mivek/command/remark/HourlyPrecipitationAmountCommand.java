package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author Jean-Kevin KPADEY
 */
public final class HourlyPrecipitationAmountCommand implements Command {
    /** Pattern for the hourly precipitation amount. */
    private static final Pattern HOURLY_PRECIPITATION_AMOUNT_PATTERN = Pattern.compile("^P(\\d{4})\\b");
    /** The message instance. */
    private final Messages messages;

    /**
     * Default constructor.
     */
    HourlyPrecipitationAmountCommand() {
        this.messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] precipitationAmount = Regex.pregMatch(HOURLY_PRECIPITATION_AMOUNT_PATTERN, remark);
        stringBuilder.append(messages.getString("Remark.Precipitation.Amount.Hourly", Integer.parseInt(precipitationAmount[1]))).append(" ");
        return remark.replaceFirst(HOURLY_PRECIPITATION_AMOUNT_PATTERN.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(HOURLY_PRECIPITATION_AMOUNT_PATTERN, input);
    }
}
