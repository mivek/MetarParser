package io.github.mivek.service;

import io.github.mivek.model.TAF;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TAFServiceTest extends AbstractWeatherCodeServiceTest<TAF> {

    @Override
    protected AbstractWeatherCodeService<TAF> getService(final FakeWeatherProvider provider) {
        return TAFService.withProvider(provider);
    }

    @Test
    void testRetrieveFromAirportWithFakeProvider() throws Exception {
        TAF result = TAFService.withProvider(new FakeWeatherProvider()).retrieveFromAirport("LFPG");
        assertNotNull(result);
        assertNotNull(result.getAirport());
    }
}
