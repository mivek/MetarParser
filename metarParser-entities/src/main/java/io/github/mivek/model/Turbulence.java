package io.github.mivek.model;

import io.github.mivek.enums.LengthUnit;
import io.github.mivek.enums.TurbulenceIntensity;
import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * This class represents the Turbulence of a TAF.
 * @author Jean-Kevin KPADEY
 */
public final class Turbulence {
  /** The intensity of the turbulence. */
  private TurbulenceIntensity intensity;
  /** The base limit of the turbulence layer. */
  private int baseHeight;
  /** The turbulence layer depth. */
  private int depth;
  /** The unit of the turbulence heights. */
  private LengthUnit unit;

  /**
   * @return the intensity of the turbulence.
   */
  public TurbulenceIntensity getIntensity() {
    return intensity;
  }

  /**
   * @param intensity The intensity to set.
   */
  public void setIntensity(final TurbulenceIntensity intensity) {
    this.intensity = intensity;
  }

  /**
   * @return The base height of the turbulence layer.
   */
  public int getBaseHeight() {
    return baseHeight;
  }

  /**
   * @param baseHeight The base height to set.
   */
  public void setBaseHeight(final int baseHeight) {
    this.baseHeight = baseHeight;
  }

  /**
   * @return the turbulence layer depth.
   * Add it to the base height to determine the top limit of the turbulence conditions.
   */
  public int getDepth() {
    return depth;
  }

  /**
   * @param depth The turbulence layer depth to set.
   */
  public void setDepth(final int depth) {
    this.depth = depth;
  }

  /**
   * @return the unit of the turbulence heights.
   */
  public LengthUnit getUnit() {
    return unit;
  }

  /**
   * @param unit The unit of the turbulence heights to set.
   */
  public void setUnit(final LengthUnit unit) {
    this.unit = unit;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).
        append(Messages.getInstance().getString("ToString.intensity"), intensity).
        append(Messages.getInstance().getString("ToString.baseHeight"), baseHeight).
        append(Messages.getInstance().getString("ToString.depth"), depth).
        append(Messages.getInstance().getString("ToString.height.unit"), unit).
        toString();
  }
}
