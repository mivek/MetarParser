package io.github.mivek.model;

import io.github.mivek.enums.LengthUnit;
import io.github.mivek.internationalization.Messages;
import java.util.Locale;
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
   * Appends intensity-specific information to the toString builder using the default locale.
   * Implemented by subclasses to add their intensity field.
   *
   * @param builder the ToStringBuilder to append to.
   */
  protected abstract void appendIntensity(final ToStringBuilder builder);

  /**
   * Appends intensity-specific information to the toString builder for a given locale.
   * The default implementation delegates to {@link #appendIntensity(ToStringBuilder)}.
   *
   * @param builder the ToStringBuilder to append to.
   * @param locale the locale to use.
   */
  protected void appendIntensity(final ToStringBuilder builder, final Locale locale) {
    appendIntensity(builder);
  }

  @Override
  public final String toString() {
    return toString(Locale.getDefault());
  }

  /**
   * Returns a locale-aware string representation.
   * @param locale the locale to use for labels and sub-objects.
   * @return the string representation.
   */
  public String toString(final Locale locale) {
    ToStringBuilder builder = new ToStringBuilder(this);
    appendIntensity(builder, locale);
    return builder.
        append(Messages.getInstance().getString(locale, "ToString.baseHeight"), baseHeight).
        append(Messages.getInstance().getString(locale, "ToString.depth"), depth).
        append(Messages.getInstance().getString(locale, "ToString.height.unit"), unit).
        toString();
  }
}
