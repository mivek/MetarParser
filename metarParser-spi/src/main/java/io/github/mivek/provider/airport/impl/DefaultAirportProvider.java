package io.github.mivek.provider.airport.impl;

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
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Default implementation of the AiportProvider using local files to build the airport map.
 *
 * @author mivek
 */
public final class DefaultAirportProvider implements AirportProvider {
    private static final String AIRPORTS_RESOURCE = "data/airports.dat";
    private static final String COUNTRIES_RESOURCE = "data/countries.dat";

    private volatile Map<String, Country> countries;
    private volatile Map<String, Airport> airports;

    /** private lock to avoid exposing the monitor. */
    private final Object loadLock = new Object();


    /**
     * Ensure the airport and country data have been loaded.
     *
     * <p>This method is safe to call from multiple threads. It performs a double-checked
     * locking pattern using {@code loadLock} to initialize the data only once.
     */
    private void ensureLoaded() {
        if (airports != null && countries != null) {
            return;
        }
        synchronized (loadLock) {
            if (airports != null && countries != null) {
                return;
            }
            loadResources();
        }
    }

    /**
     * Load countries and airports from the bundled CSV resources into memory.
     *
     * <p>The method reads two CSV resource files (COUNTRIES_RESOURCE and AIRPORTS_RESOURCE),
     * creates Country and Airport instances and populates internal maps. If a resource is not
     * found or an I/O error occurs, this method throws an IllegalStateException wrapping the
     * original cause.
     *
     * @throws IllegalStateException if a resource file cannot be found or an I/O error occurs
     *     while reading the CSV files
     */
    private void loadResources() {
        Map<String, Country> localCountries = new HashMap<>();
        Map<String, Airport> localAirports = new HashMap<>();

        try (InputStream countriesStream = getClass().getClassLoader().getResourceAsStream(COUNTRIES_RESOURCE)) {
            Objects.requireNonNull(countriesStream, COUNTRIES_RESOURCE + " not found");
            try (CSVParser parser = CSVFormat.DEFAULT.parse(new InputStreamReader(countriesStream, StandardCharsets.UTF_8))) {
                for (CSVRecord line : parser) {
                    Country country = new Country();
                    country.setName(line.get(0));
                    localCountries.put(country.getName(), country);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        try (InputStream airportsStream = getClass().getClassLoader().getResourceAsStream(AIRPORTS_RESOURCE)) {
            Objects.requireNonNull(airportsStream, AIRPORTS_RESOURCE + " not found");
            try (CSVParser parser = CSVFormat.DEFAULT.parse(new InputStreamReader(airportsStream, StandardCharsets.UTF_8))) {
                for (CSVRecord line : parser) {
                    Airport airport = new Airport();
                    airport.setName(line.get(1));
                    airport.setCity(line.get(2));
                    airport.setCountry(localCountries.get(line.get(3)));
                    airport.setIata(line.get(4));
                    airport.setIcao(line.get(5));
                    airport.setLatitude(Double.parseDouble(line.get(6)));
                    airport.setLongitude(Double.parseDouble(line.get(7)));
                    airport.setAltitude(Integer.parseInt(line.get(8)));
                    airport.setTimezone(line.get(9));
                    airport.setDst(line.get(10));
                    localAirports.put(airport.getIcao(), airport);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        this.countries = localCountries;
        this.airports = localAirports;
    }

    /**
     * Returns the map of loaded airports keyed by ICAO code.
     *
     * <p>When this method is called the first time, it triggers loading of the underlying
     * country and airport resources. Subsequent calls return the cached map. The returned
     * map is the internal map instance (not a defensive copy).
     *
     * @return the map of ICAO -> Airport
     */
    @Override
    public Map<String, Airport> getAirports() {
        ensureLoaded();
        return airports;
    }
}
