package io.github.mivek.command.taf;

import io.github.mivek.enums.IcingIntensity;
import io.github.mivek.model.ITafGroups;
import io.github.mivek.model.Icing;
import io.github.mivek.utils.Regex;
import java.util.regex.Pattern;

/**
 * @author Jean-Kevin KPADEY
 */
public final class IcingCommand implements Command {
  /** Icing pattern. */
  private static final Pattern ICING_PATTERN = Pattern.compile("^6(\\d)(\\d{3})(\\d)$");

  @Override
  public void execute(final ITafGroups taf, final String part) {
    String[] matches = Regex.pregMatch(ICING_PATTERN, part);
    Icing icing = new Icing();
    icing.setIntensity(IcingIntensity.get(matches[1]));
    icing.setBaseHeight(100 * Integer.parseInt(matches[2]));
    icing.setDepth(1000 * Integer.parseInt(matches[3]));
    taf.addIcing(icing);
  }

  @Override
  public boolean canParse(final String input) {
    return Regex.find(ICING_PATTERN, input);
  }
}
