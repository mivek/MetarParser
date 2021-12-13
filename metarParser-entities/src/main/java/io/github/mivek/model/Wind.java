package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Wind class.
 *
 * @author mivek
 */
public class Wind {
  /** Speed of the wind. */
  private int speed;
  /** Direction of the wind. */
  private String direction;
  /** Direction of the wind. */
  private Integer directionDegrees;
  /** The speed of the gust. */
  private int gust;
  /** The minimal variation of the wind. */
  private int minVariation;
  /** The maximum variation of the wind. */
  private int maxVariation;
  /** The unit of the speed. */
  private String unit;

  /**
   * Getter of the speed.
   *
   * @return the speed.
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * Setter of the speed.
   *
   * @param speed the speed to set.
   */
  public void setSpeed(final int speed) {
    this.speed = speed;
  }

  /**
   * Getter of the direction.
   *
   * @return The Direction of the wind.
   */
  public String getDirection() {
    return direction;
  }

  /**
   * Setter of the direction of the wind.
   *
   * @param direction the direction to set.
   */
  public void setDirection(final String direction) {
    this.direction = direction;
  }

  /**
   * Getter of the gust.
   *
   * @return the gust.
   */
  public int getGust() {
    return gust;
  }

  /**
   * Setter of the gust.
   *
   * @param gust the gust to set.
   */
  public void setGust(final int gust) {
    this.gust = gust;
  }

  /**
   * Getter of the minimal variation of the wind.
   *
   * @return the minimal variation of the wind.
   */
  public int getMinVariation() {
    return minVariation;
  }

  /** @param minVariation the minimal wind variation to set. */
  public void setMinVariation(final int minVariation) {
    this.minVariation = minVariation;
  }

  /** @return the wind max variation. */
  public int getMaxVariation() {
    return maxVariation;
  }

  /** @param maxVariation the wind max variation to set. */
  public void setMaxVariation(final int maxVariation) {
    this.maxVariation = maxVariation;
  }

  /**
   * Getter of the unit.
   *
   * @return the unit.
   */
  public String getUnit() {
    return unit;
  }

  /**
   * Setter.
   *
   * @param unit The unit to set.
   */
  public void setUnit(final String unit) {
    this.unit = unit;
  }

  /** @return the directionDegrees. */
  public Integer getDirectionDegrees() {
    return directionDegrees;
  }

  /** @param directionDegrees the directionDegrees to set. */
  public void setDirectionDegrees(final Integer directionDegrees) {
    this.directionDegrees = directionDegrees;
  }

  /** @return a description of the wind component. */
  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append(Messages.getInstance().getString("ToString.wind.speed"), speed)
        .append(Messages.getInstance().getString("ToString.wind.unit"), unit)
        .append(Messages.getInstance().getString("ToString.wind.direction"), direction)
        .append(
            Messages.getInstance().getString("ToString.wind.direction.degrees"), directionDegrees)
        .append(Messages.getInstance().getString("ToString.wind.gusts"), gust)
        .append(Messages.getInstance().getString("ToString.wind.min.variation"), minVariation)
        .append(Messages.getInstance().getString("ToString.wind.max.variation"), maxVariation)
        .toString();
  }
}
