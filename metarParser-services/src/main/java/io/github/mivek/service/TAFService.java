package io.github.mivek.service;

import io.github.mivek.exception.ParseException;
import io.github.mivek.model.TAF;
import io.github.mivek.parser.TAFParser;
import io.github.mivek.service.provider.NOAAWeatherProvider;
import io.github.mivek.service.provider.WeatherProvider;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Facade for TAF.
 *
 * @author mivek
 */
public final class TAFService extends AbstractWeatherCodeService<TAF> {
    /** The instance of the service. */
    private static final TAFService INSTANCE = new TAFService();

    /**
     * Private default constructor. Uses the NOAA provider.
     */
    private TAFService() {
        this(new NOAAWeatherProvider());
    }

    /**
     * Private constructor for a specific provider.
     *
     * @param provider the weather provider to use.
     */
    private TAFService(final WeatherProvider provider) {
        super(new TAFParser(), provider);
    }

    /**
     * Creates a new {@link TAFService} instance configured with the given provider.
     *
     * @param provider the weather provider to use for fetching TAF data.
     * @return a new {@link TAFService} using the specified provider.
     */
    public static TAFService withProvider(final WeatherProvider provider) {
        return new TAFService(provider);
    }

    @Override
    public TAF decode(final String code) throws ParseException {
        return getParser().parse(code);
    }

    @Override
    public TAF retrieveFromAirport(final String icao) throws IOException, ParseException, URISyntaxException, InterruptedException {
        return getParser().parse(getProvider().retrieveTaf(icao));
    }

    /**
     * @return the instance of the service.
     */
    public static TAFService getInstance() {
        return INSTANCE;
    }
}
