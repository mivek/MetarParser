package io.github.mivek.command.taf;

import io.github.mivek.enums.TurbulenceIntensity;
import io.github.mivek.model.ITafGroups;
import io.github.mivek.model.Turbulence;
import io.github.mivek.utils.Regex;
import java.util.regex.Pattern;

/**
 * Handle the parsing of the Turbulence part of a TAF.
 * @author Jean-Kevin KPADEY
 */
public final class TurbulenceCommand implements Command {

  /** Regex pattern for the turbulence. */
  private static final Pattern TURBULENCE_PATTERN = Pattern.compile("^5(\\d|'X')(\\d{3})(\\d)$");

  @Override
  public void execute(final ITafGroups taf, final String part) {
    String[] matches = Regex.pregMatch(TURBULENCE_PATTERN, part);
    Turbulence turbulence = new Turbulence();
    turbulence.setIntensity(TurbulenceIntensity.get(matches[1]));
    turbulence.setBaseHeight(100 * Integer.parseInt(matches[2]));
    turbulence.setDepth(1000 * Integer.parseInt(matches[3]));
    taf.addTurbulence(turbulence);
  }

  @Override
  public boolean canParse(final String input) {
    return Regex.find(TURBULENCE_PATTERN, input);
  }
}
