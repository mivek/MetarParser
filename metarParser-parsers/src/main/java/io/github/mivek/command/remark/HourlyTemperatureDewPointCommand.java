package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;
import java.util.regex.Pattern;

/** @author Jean-Kevin KPADEY */
public final class HourlyTemperatureDewPointCommand implements Command {
  /** Pattern for the hourly temperature and dew point. */
  private static final Pattern TEMPERATURE_PATTERN =
      Pattern.compile("^T([01])(\\d{3})(([01])(\\d{3}))?");
  /** The message instance. */
  private final Messages messages;

  HourlyTemperatureDewPointCommand() {
    this.messages = Messages.getInstance();
  }

  @Override
  public String execute(final String remark, final StringBuilder stringBuilder) {
    String[] matches = Regex.pregMatch(TEMPERATURE_PATTERN, remark);
    if (matches[3] == null) {
      stringBuilder
          .append(
              messages.getString(
                  "Remark.Hourly.Temperature",
                  Converter.convertTemperature(matches[1], matches[2])))
          .append(" ");
    } else {
      stringBuilder
          .append(
              messages.getString(
                  "Remark.Hourly.Temperature.Dew.Point",
                  Converter.convertTemperature(matches[1], matches[2]),
                  Converter.convertTemperature(matches[4], matches[5])))
          .append(" ");
    }
    return remark.replaceFirst(TEMPERATURE_PATTERN.pattern(), "").trim();
  }

  @Override
  public boolean canParse(final String input) {
    return Regex.find(TEMPERATURE_PATTERN, input);
  }
}
