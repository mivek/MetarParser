package io.github.mivek.model;

import io.github.mivek.enums.TurbulenceIntensity;
import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * This class represents the Turbulence of a TAF.
 *
 * @author mivek
 */
public final class Turbulence extends AbstractWeatherLayer {
  /** The intensity of the turbulence. */
  private TurbulenceIntensity intensity;

  /**
   * Returns the intensity of the turbulence.
   *
   * @return the intensity of the turbulence.
   */
  public TurbulenceIntensity getIntensity() {
    return intensity;
  }

  /**
   * Sets the intensity of the turbulence.
   *
   * @param intensity The intensity to set.
   */
  public void setIntensity(final TurbulenceIntensity intensity) {
    this.intensity = intensity;
  }

  @Override
  protected void appendIntensity(final ToStringBuilder builder) {
    builder.append(Messages.getInstance().getString("ToString.intensity"), intensity);
  }
}
