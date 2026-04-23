package io.github.mivek.service;

import io.github.mivek.exception.ParseException;
import io.github.mivek.model.TAF;
import io.github.mivek.service.provider.AviationWeatherProvider;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TAFServiceTest extends AbstractWeatherCodeServiceTest<TAF> {

    private final TAFService sut = TAFService.getInstance();

    @Override
    protected AbstractWeatherCodeService<TAF> getService() {
        return sut;
    }

    @Test
    void testRetrieveFromAirportWithAviationWeatherProvider() throws ParseException, IOException, URISyntaxException, InterruptedException {
        TAF result = TAFService.withProvider(new AviationWeatherProvider()).retrieveFromAirport("LFPG");
        assertNotNull(result);
        assertNotNull(result.getAirport());
    }
}
