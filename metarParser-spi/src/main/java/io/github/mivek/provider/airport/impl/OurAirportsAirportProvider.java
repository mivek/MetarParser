package io.github.mivek.provider.airport.impl;

import io.github.mivek.model.Airport;
import io.github.mivek.model.Country;
import io.github.mivek.provider.airport.AirportProvider;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Implementation of the AirportProvider based on ourAirports.
 * To use this provider make sure you are connected to internet.
 *
 * @author mivek
 */
public final class OurAirportsAirportProvider implements AirportProvider {
    /** URI to retrieve the list of countries. */
    private static final String COUNTRIES_URI = "https://davidmegginson.github.io/ourairports-data/countries.csv";
    /** URI to retrieve the list of airports. */
    private static final String AIRPORT_URI = "https://davidmegginson.github.io/ourairports-data/airports.csv";
    /** Map of countries. */
    private final Map<String, Country> countries;
    /** Map of airports. */
    private final Map<String, Airport> airports;
    /** Common CSV format. */
    private final CSVFormat csvFormat;

    /**
     * Default constructor.
     *
     * @throws IOException            when network error
     * @throws URISyntaxException     when the URI is invalid
     */
    public OurAirportsAirportProvider() throws IOException, URISyntaxException, InterruptedException {
        countries = new HashMap<>();
        airports = new HashMap<>();
        csvFormat = CSVFormat.RFC4180;
        buildCountries();
        buildAirport();
    }

    /**
     * Connects to the countries list and build a map of {@link Country} with the name as key.
     *
     * @throws IOException            when network error
     * @throws URISyntaxException     when the URI is invalid
     */
    public void buildCountries() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(COUNTRIES_URI))
                .GET()
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.ofSeconds(5))
                .build();

        HttpResponse<InputStream> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofInputStream());
        try (CSVParser parser = csvFormat.parse(new InputStreamReader(response.body(), StandardCharsets.UTF_8))) {
            for (CSVRecord line: parser) {
                Country c = new Country();
                c.setName(line.get(2));
                countries.put(line.get(1), c);
            }
        }
    }

    /**
     * Connects to the airports list and build a map of {@link Airport} with the name as key.
     *
     * @throws IOException            when network error
     * @throws URISyntaxException     when the URI is invalid
     */
    public void buildAirport() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(AIRPORT_URI))
                .GET()
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.ofSeconds(5))
                .build();

        HttpResponse<InputStream> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofInputStream());
        try (CSVParser parser = csvFormat.parse(new InputStreamReader(response.body(), StandardCharsets.UTF_8))) {
            for (CSVRecord line : parser) {
                Airport airport = new Airport();
                airport.setIcao(line.get(1));
                airport.setName(line.get(3));
                airport.setLatitude(NumberUtils.toDouble(line.get(4), 0));
                airport.setLongitude(NumberUtils.toDouble(line.get(5), 0));
                airport.setAltitude(NumberUtils.toInt(line.get(6), 0));
                airport.setCountry(countries.get(line.get(8)));
                airport.setCity(line.get(10));
                airport.setIata(line.get(13));
                airports.put(airport.getIcao(), airport);
            }
        }
    }

    @Override
    public Map<String, Airport> getAirports() {
        return airports;
    }
}

