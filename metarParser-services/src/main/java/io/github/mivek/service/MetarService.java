package io.github.mivek.service;

import io.github.mivek.exception.ParseException;
import io.github.mivek.model.Metar;
import io.github.mivek.parser.MetarParser;
import io.github.mivek.service.provider.NOAAWeatherProvider;
import io.github.mivek.service.provider.WeatherProvider;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Class representing the service for metar.
 *
 * @author mivek
 */
public final class MetarService extends AbstractWeatherCodeService<Metar> {
    /** Instance. */
    private static final MetarService INSTANCE = new MetarService();

    /**
     * Private default constructor. Uses the NOAA provider.
     */
    private MetarService() {
        this(new NOAAWeatherProvider());
    }

    /**
     * Private constructor for a specific provider.
     *
     * @param provider the weather provider to use.
     */
    private MetarService(final WeatherProvider provider) {
        super(new MetarParser(), provider);
    }

    /**
     * Creates a new {@link MetarService} instance configured with the given provider.
     *
     * @param provider the weather provider to use for fetching METAR data.
     * @return a new {@link MetarService} using the specified provider.
     */
    public static MetarService withProvider(final WeatherProvider provider) {
        return new MetarService(provider);
    }

    @Override
    public Metar decode(final String code) throws ParseException {
        return getParser().parse(code);
    }

    @Override
    public Metar retrieveFromAirport(final String icao) throws ParseException, IOException, URISyntaxException, InterruptedException {
        return getParser().parse(getProvider().retrieveMetar(icao));
    }

    /**
     * Returns a instance of the class.
     *
     * @return the instance of the class.
     */
    public static MetarService getInstance() {
        return INSTANCE;
    }
}
