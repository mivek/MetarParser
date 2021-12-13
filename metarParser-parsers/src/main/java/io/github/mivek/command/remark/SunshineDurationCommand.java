package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;
import java.util.regex.Pattern;

/** @author Jean-Kevin KPADEY */
public final class SunshineDurationCommand implements Command {
  /** Pattern for the sunshine duration. */
  private static final Pattern SUNSHINE_DURATION_PATTERN = Pattern.compile("^98(\\d{3})");
  /** The message instance. */
  private final Messages messages;

  SunshineDurationCommand() {
    this.messages = Messages.getInstance();
  }

  @Override
  public String execute(final String remark, final StringBuilder stringBuilder) {
    String[] duration = Regex.pregMatch(SUNSHINE_DURATION_PATTERN, remark);
    stringBuilder
        .append(messages.getString("Remark.Sunshine.Duration", Integer.parseInt(duration[1])))
        .append(" ");
    return remark.replaceFirst(SUNSHINE_DURATION_PATTERN.pattern(), "").trim();
  }

  @Override
  public boolean canParse(final String input) {
    return Regex.find(SUNSHINE_DURATION_PATTERN, input);
  }
}
