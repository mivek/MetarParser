package io.github.mivek.command;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.mivek.model.Airport;
import io.github.mivek.model.Country;
import io.github.mivek.parser.AbstractParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mivek
 */
public final class AirportSupplier implements Supplier<Airport> {
    /** Path of airport file. */
    private final InputStream airportsFile = AbstractParser.class.getClassLoader().getResourceAsStream("data/airports.dat");
    /** Path of countries file. */
    private final InputStream countriesFile = AbstractParser.class.getClassLoader().getResourceAsStream("data/countries.dat");
    /** Map of countries. */
    private Map<String, Country> countries;
    /** Map of airports. */
    private Map<String, Airport> airports;

    /**
     * Constructor.
     */
    public AirportSupplier() {
        initCountries();
        initAirports();
    }

    /**
     * Initiate airports map.
     */
    private void initAirports() {
        airports = new HashMap<>();
        String[] line;
        try (CSVReader reader = new CSVReader(new InputStreamReader(airportsFile, StandardCharsets.UTF_8))) {
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

    /**
     * Initiate countries map.
     */
    private void initCountries() {
        countries = new HashMap<>();
        String[] line;
        try (CSVReader reader = new CSVReader(new InputStreamReader(countriesFile, StandardCharsets.UTF_8))) {
            while ((line = reader.readNext()) != null) {
                Country country = new Country();
                country.setName(line[0]);
                countries.put(country.getName(), country);
            }
        } catch (IOException | CsvValidationException exception) {
            throw new IllegalStateException(exception.getMessage());
        }
    }

    @Override public Airport get(final String pIcao) {
        return airports.get(pIcao);
    }
}

