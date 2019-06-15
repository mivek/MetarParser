package io.github.mivek.command;

import com.opencsv.CSVReader;
import io.github.mivek.model.Airport;
import io.github.mivek.model.Country;
import io.github.mivek.parser.AbstractParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author mivek
 */
public final class AirportSupplier implements Supplier<Optional<Airport>> {
    /** Path of airport file. */
    private final InputStream fAirportsFile = AbstractParser.class.getClassLoader().getResourceAsStream("data/airports.dat");
    /** Path of countries file. */
    private final InputStream fCountriesFile = AbstractParser.class.getClassLoader().getResourceAsStream("data/countries.dat");
    /** Map of countries. */
    private Map<String, Country> fCountries;
    /** Map of airports. */
    private Map<String, Airport> fAirports;

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
        fAirports = new HashMap<>();
        String[] line;
        try (CSVReader reader = new CSVReader(new InputStreamReader(fAirportsFile, StandardCharsets.UTF_8))) {
            while ((line = reader.readNext()) != null) {
                Airport airport = new Airport();
                airport.setName(line[1]);
                airport.setCity(line[2]);
                airport.setCountry(fCountries.get(line[3]));
                airport.setIata(line[4]);
                airport.setIcao(line[5]);
                airport.setLatitude(Double.parseDouble(line[6]));
                airport.setLongitude(Double.parseDouble(line[7]));
                airport.setAltitude(Integer.parseInt(line[8]));
                airport.setTimezone(line[9]);
                airport.setDst(line[10]);
                fAirports.put(airport.getIcao(), airport);
            }
        } catch (IOException exception) {
            throw new IllegalStateException(exception.getMessage());
        }
    }

    /**
     * Initiate countries map.
     */
    private void initCountries() {
        fCountries = new HashMap<>();
        String[] line;
        try (CSVReader reader = new CSVReader(new InputStreamReader(fCountriesFile, StandardCharsets.UTF_8))) {
            while ((line = reader.readNext()) != null) {
                Country country = new Country();
                country.setName(line[0]);
                fCountries.put(country.getName(), country);
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override public Optional<Airport> get(final String pIcao) {
        if (fAirports.containsKey(pIcao)) {
            return Optional.of(fAirports.get(pIcao));
        }
        return Optional.empty();
    }
}

