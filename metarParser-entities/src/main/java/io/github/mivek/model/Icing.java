package io.github.mivek.model;

import io.github.mivek.enums.IcingIntensity;
import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents the icing of a TAF.
 * @author Jean-Kevin KPADEY
 */
public final class Icing {
  /** The intensity of the icing. */
  private IcingIntensity intensity;
  /** The base of the icing layer in feet. */
  private int baseHeight;
  /** The icing layer depth in feet. */
  private int depth;

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
   * Returns the base of the icing layer in feet.
   * @return the base of the icing layer in feet.
   */
  public int getBaseHeight() {
    return baseHeight;
  }

  /**
   * @param baseHeight The base height to set in feet.
   */
  public void setBaseHeight(final int baseHeight) {
    this.baseHeight = baseHeight;
  }

  /**
   * @return the icing layer depth in feet.
   * Add this to the baseHeight to determine the top limit of the icing conditions.
   */
  public int getDepth() {
    return depth;
  }

  /**
   * @param depth The depth to set in feet.
   */
  public void setDepth(final int depth) {
    this.depth = depth;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).
        append(Messages.getInstance().getString("ToString.intensity"), intensity).
        append(Messages.getInstance().getString("ToString.baseHeight"), baseHeight).
        append(Messages.getInstance().getString("ToString.depth"), depth).
        toString();
  }
}
