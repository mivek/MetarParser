package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author Jean-Kevin KPADEY
 */
public final class PrecipitationAmount36HourCommand implements Command {
    /** Pattern for the 3 or 6 hour precipitation amount. */
    private static final Pattern PRECIPITATION_PATTERN = Pattern.compile("^([36])(\\d{4})\\b");
    /** The message instance. */
    private final Messages messages;

    /**
     * Constructor.
     */
    PrecipitationAmount36HourCommand() {
        this.messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] precipitationAmount = Regex.pregMatch(PRECIPITATION_PATTERN, remark);
        stringBuilder.append(messages.getString("Remark.Precipitation.Amount.3.6", precipitationAmount[1], Converter.convertPrecipitationAmount(precipitationAmount[2]))).append(" ");
        return remark.replaceFirst(PRECIPITATION_PATTERN.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(PRECIPITATION_PATTERN, input);
    }
}
