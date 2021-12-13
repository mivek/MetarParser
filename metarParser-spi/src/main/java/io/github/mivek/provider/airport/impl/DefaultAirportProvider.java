package io.github.mivek.provider.airport.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import io.github.mivek.model.Airport;
import io.github.mivek.model.Country;
import io.github.mivek.provider.airport.AirportProvider;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Default implementation of the AiportProvider using local files to build the airport map.
 *
 * @author mivek
 */
public final class DefaultAirportProvider implements AirportProvider {
  /** Path of airport file. */
  private final InputStream airportsFile =
      DefaultAirportProvider.class.getClassLoader().getResourceAsStream("data/airports.dat");
  /** Path of countries file. */
  private final InputStream countriesFile =
      DefaultAirportProvider.class.getClassLoader().getResourceAsStream("data/countries.dat");
  /** Map of countries. */
  private Map<String, Country> countries;
  /** Map of airports. */
  private Map<String, Airport> airports;

  /** Default constructor. */
  public DefaultAirportProvider() {
    initCountries();
    initAirports();
  }

  /** Initiate countries map. */
  private void initCountries() {
    Objects.requireNonNull(countriesFile);
    countries = new HashMap<>();
    String[] line;
    try (CSVReader reader =
        new CSVReaderBuilder(new InputStreamReader(countriesFile, StandardCharsets.UTF_8))
            .withCSVParser(new CSVParser())
            .withSkipLines(0)
            .build()) {
      while ((line = reader.readNext()) != null) {
        Country country = new Country();
        country.setName(line[0]);
        countries.put(country.getName(), country);
      }
    } catch (IOException | CsvValidationException exception) {
      throw new IllegalStateException(exception.getMessage());
    }
  }

  /** Initiate airports map. */
  private void initAirports() {
    Objects.requireNonNull(airportsFile);
    airports = new HashMap<>();
    String[] line;
    try (CSVReader reader =
        new CSVReaderBuilder(new InputStreamReader(airportsFile, StandardCharsets.UTF_8))
            .withCSVParser(new CSVParser())
            .withSkipLines(0)
            .build()) {
      while ((line = reader.readNext()) != null) {
        Airport airport = new Airport();
        airport.setName(line[1]);
        airport.setCity(line[2]);
        airport.setCountry(countries.get(line[3]));
        airport.setIata(line[4]);
        airport.setIcao(line[5]);
        airport.setLatitude(Double.parseDouble(line[6]));
        airport.setLongitude(Double.parseDouble(line[7]));
        airport.setAltitude(Integer.parseInt(line[8]));
        airport.setTimezone(line[9]);
        airport.setDst(line[10]);
        airports.put(airport.getIcao(), airport);
      }
    } catch (IOException | CsvValidationException exception) {
      throw new IllegalStateException(exception.getMessage());
    }
  }

  @Override
  public Map<String, Airport> getAirports() {
    return airports;
  }
}
