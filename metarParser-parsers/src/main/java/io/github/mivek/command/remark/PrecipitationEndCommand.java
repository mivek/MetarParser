package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;
import java.util.regex.Pattern;

/** @author Jean-Kevin KPADEY */
public final class PrecipitationEndCommand implements Command {
  /** Beginning and ending of precipitation. */
  private static final Pattern PRECIPITATION_END =
      Pattern.compile("^(([A-Z]{2})?([A-Z]{2})E(\\d{2})?(\\d{2}))");
  /** The message instance. */
  private final Messages messages;

  /** Default constructor. */
  PrecipitationEndCommand() {
    messages = Messages.getInstance();
  }

  @Override
  public String execute(final String remark, final StringBuilder stringBuilder) {
    String[] precipitationBegEnd = Regex.pregMatch(PRECIPITATION_END, remark);
    stringBuilder
        .append(
            messages.getString(
                "Remark.Precipitation.End",
                precipitationBegEnd[2] == null
                    ? ""
                    : messages.getString("Descriptive." + precipitationBegEnd[2]),
                messages.getString("Phenomenon." + precipitationBegEnd[3]),
                verifyString(precipitationBegEnd[4]),
                precipitationBegEnd[5]))
        .append(" ");
    return remark.replaceFirst(PRECIPITATION_END.pattern(), "").trim();
  }

  @Override
  public boolean canParse(final String input) {
    return Regex.find(PRECIPITATION_END, input);
  }
}
