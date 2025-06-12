package io.github.mivek.command;

import io.github.mivek.model.Airport;
import io.github.mivek.provider.airport.AirportProvider;
import io.github.mivek.provider.airport.impl.DefaultAirportProvider;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author mivek
 */
public final class AirportSupplier implements Supplier<Airport> {
    /** The airport provider. */
    private final AirportProvider provider;

    /**
     * Constructor.
     */
    public AirportSupplier() {
        ServiceLoader<AirportProvider> loader = ServiceLoader.load(AirportProvider.class);
        Iterator<AirportProvider> iterator = loader.iterator();
        this.provider = iterator.hasNext() ? iterator.next() : new DefaultAirportProvider();
    }

    @Override
    public Airport get(final String string) {
        return provider.getAirports().get(string);
    }
}

