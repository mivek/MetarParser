package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;
import java.util.regex.Pattern;

/** @author Jean-Kevin KPADEY */
public final class HourlyMaximumTemperatureCommand implements Command {
  /** Pattern for the hourly maximum temperature. */
  private static final Pattern TEMPERATURE_PATTERN = Pattern.compile("^1([01])(\\d{3})\\b");
  /** The message instance. */
  private final Messages messages;

  /** Default constructor. */
  HourlyMaximumTemperatureCommand() {
    this.messages = Messages.getInstance();
  }

  @Override
  public String execute(final String remark, final StringBuilder stringBuilder) {
    String[] matches = Regex.pregMatch(TEMPERATURE_PATTERN, remark);
    stringBuilder
        .append(
            messages.getString(
                "Remark.Hourly.Maximum.Temperature",
                Converter.convertTemperature(matches[1], matches[2])))
        .append(" ");
    return remark.replaceFirst(TEMPERATURE_PATTERN.pattern(), "").trim();
  }

  @Override
  public boolean canParse(final String input) {
    return Regex.find(TEMPERATURE_PATTERN, input);
  }
}
