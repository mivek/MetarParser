package io.github.mivek.model;

import io.github.mivek.enums.IcingIntensity;
import io.github.mivek.enums.LengthUnit;
import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents the icing of a TAF.
 * @author Jean-Kevin KPADEY
 */
public final class Icing {
  /** The intensity of the icing. */
  private IcingIntensity intensity;
  /** The base of the icing layer. */
  private int baseHeight;
  /** The icing layer depth. */
  private int depth;
  /** The unit of the icing heights. */
  private LengthUnit unit;

  /**
   * Returns the icing intensity.
   * @return the icing intensity.
   */
  public IcingIntensity getIntensity() {
    return intensity;
  }

  /**
   * @param intensity The intensity to set.
   */
  public void setIntensity(final IcingIntensity intensity) {
    this.intensity = intensity;
  }

  /**
   * Returns the base of the icing layer.
   * @return the base of the icing layer.
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
   * @return the icing layer depth.
   * Add this to the baseHeight to determine the top limit of the icing conditions.
   */
  public int getDepth() {
    return depth;
  }

  /**
   * @param depth The depth to set.
   */
  public void setDepth(final int depth) {
    this.depth = depth;
  }

  /**
   * @return the unit of the icing heights.
   */
  public LengthUnit getUnit() {
    return unit;
  }

  /**
   * @param unit The unit of the icing heights to set.
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
