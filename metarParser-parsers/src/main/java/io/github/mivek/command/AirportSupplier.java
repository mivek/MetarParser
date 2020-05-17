package io.github.mivek.command;

import io.github.mivek.model.Airport;
import io.github.mivek.provider.airport.AirportProvider;
import io.github.mivek.provider.airport.impl.DefaultAirportProvider;

import java.util.ServiceLoader;

/**
 * @author mivek
 */
public final class AirportSupplier implements Supplier<Airport> {
    /** The service loader for the airport provider. */
    private final ServiceLoader<AirportProvider> airportLoader;

    /**
     * Constructor.
     */
    public AirportSupplier() {
        airportLoader = ServiceLoader.load(AirportProvider.class);
    }

    @Override
    public Airport get(final String pIcao) {
        AirportProvider provider;
        if (airportLoader.iterator().hasNext()) {
            provider = airportLoader.iterator().next();
        } else {
            provider = new DefaultAirportProvider();
        }
        return provider.getAirports().get(pIcao);
    }
}

