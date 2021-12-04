package io.github.mivek.service;

import io.github.mivek.exception.ParseException;
import io.github.mivek.model.AbstractWeatherCode;
import java.io.IOException;

/**
 * Interface for service.
 *
 * @param <T> a concrete sub-class of {@link AbstractWeatherCode}
 * @author mivek
 */
public interface IWeatherCodeFacade<T extends AbstractWeatherCode> {
  /**
   * Decode method.
   *
   * @param code the code to decode.
   * @return the decoded object corresponding to the message.
   * @throws ParseException when an error occurs during the parsing.
   */
  T decode(String code) throws ParseException;

  /**
   * Retrieve code and decoded object from airport with icao.
   *
   * @param icao the icao of the airport
   * @return the decoded object
   * @throws IOException When an error occurs
   * @throws ParseException when an error occurs during the parsing.
   */
  T retrieveFromAirport(String icao) throws ParseException, IOException;
}
