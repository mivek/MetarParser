package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import java.time.LocalTime;
import org.apache.commons.lang3.builder.ToStringBuilder;

/** @author mivek Parent class of {@link Metar} and {@link TAF}. */
public abstract class AbstractWeatherCode extends AbstractWeatherContainer {

  /** Integer for the day of the metar. */
  private Integer day;
  /** Time of the metar. */
  private LocalTime time;
  /** Airport of the metar. */
  private Airport airport;
  /** Original message of the metar. */
  private String message;
  /** The identifier of the station. */
  private String station;

  /** @return the day */
  public Integer getDay() {
    return day;
  }

  /** @param day the day to set */
  public void setDay(final Integer day) {
    this.day = day;
  }

  /** @return the time */
  public LocalTime getTime() {
    return time;
  }

  /** @param time the time to set */
  public void setTime(final LocalTime time) {
    this.time = time;
  }

  /** @return the airport */
  public Airport getAirport() {
    return airport;
  }

  /** @param airport the airport to set */
  public void setAirport(final Airport airport) {
    this.airport = airport;
  }

  /** @return the message */
  public String getMessage() {
    return message;
  }

  /** @param message the message to set */
  public void setMessage(final String message) {
    this.message = message;
  }

  /** @return The station. the icao. */
  public String getStation() {
    return station;
  }

  /** @param station The identifier of the station. */
  public void setStation(final String station) {
    this.station = station;
  }

  /** @return a description of the object. */
  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append(Messages.getInstance().getString("ToString.day.month"), day)
        .append(Messages.getInstance().getString("ToString.report.time"), time)
        .append(Messages.getInstance().getString("ToString.airport"), airport)
        .appendSuper(super.toString())
        .append(Messages.getInstance().getString("ToString.message"), message)
        .toString();
  }
}
