package io.github.mivek.model;

import io.github.mivek.enums.IcingIntensity;
import io.github.mivek.internationalization.Messages;
import java.util.Locale;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents the icing of a TAF.
 *
 * @author Jean-Kevin KPADEY
 */
public final class Icing extends AbstractWeatherLayer {
  /** The intensity of the icing. */
  private IcingIntensity intensity;

  /**
   * Returns the icing intensity.
   *
   * @return the icing intensity.
   */
  public IcingIntensity getIntensity() {
    return intensity;
  }

  /**
   * Sets the icing intensity.
   *
   * @param intensity The intensity to set.
   */
  public void setIntensity(final IcingIntensity intensity) {
    this.intensity = intensity;
  }

  @Override
  protected void appendIntensity(final ToStringBuilder builder) {
    appendIntensity(builder, Locale.getDefault());
  }

  @Override
  protected void appendIntensity(final ToStringBuilder builder, final Locale locale) {
    builder.append(Messages.getInstance().getString(locale, "ToString.intensity"),
        intensity != null ? intensity.toString(locale) : null);
  }
}
