package io.github.mivek.model;

import io.github.mivek.enums.LengthUnit;
import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Abstract base class for weather layers with height-based properties (icing, turbulence).
 * Defines common fields and behavior for layers characterized by base height, depth, and measurement unit.
 *
 * @author Jean-Kevin KPADEY
 */
public abstract class AbstractWeatherLayer {

  /** The base limit of the layer. */
  private int baseHeight;
  /** The layer depth. */
  private int depth;
  /** The unit of the layer heights. */
  private LengthUnit unit;

  /**
   * Returns the base height of the layer.
   *
   * @return the base height.
   */
  public final int getBaseHeight() {
    return baseHeight;
  }

  /**
   * Sets the base height of the layer.
   *
   * @param baseHeight the base height to set.
   */
  public final void setBaseHeight(final int baseHeight) {
    this.baseHeight = baseHeight;
  }

  /**
   * Returns the depth of the layer.
   * Add this to the baseHeight to determine the top limit of the layer conditions.
   *
   * @return the layer depth.
   */
  public final int getDepth() {
    return depth;
  }

  /**
   * Sets the depth of the layer.
   *
   * @param depth the depth to set.
   */
  public final void setDepth(final int depth) {
    this.depth = depth;
  }

  /**
   * Returns the unit of the layer heights.
   *
   * @return the unit of the heights.
   */
  public final LengthUnit getUnit() {
    return unit;
  }

  /**
   * Sets the unit of the layer heights.
   *
   * @param unit the unit to set.
   */
  public final void setUnit(final LengthUnit unit) {
    this.unit = unit;
  }

  /**
   * Appends intensity-specific information to the toString builder.
   * Implemented by subclasses to add their intensity field.
   *
   * @param builder the ToStringBuilder to append to.
   */
  protected abstract void appendIntensity(final ToStringBuilder builder);

  @Override
  public final String toString() {
    ToStringBuilder builder = new ToStringBuilder(this);
    appendIntensity(builder);
    return builder.
        append(Messages.getInstance().getString("ToString.baseHeight"), baseHeight).
        append(Messages.getInstance().getString("ToString.depth"), depth).
        append(Messages.getInstance().getString("ToString.height.unit"), unit).
        toString();
  }
}
