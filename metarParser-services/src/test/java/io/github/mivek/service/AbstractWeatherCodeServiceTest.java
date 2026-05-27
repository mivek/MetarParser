package io.github.mivek.service;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.AbstractWeatherCode;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class AbstractWeatherCodeServiceTest<T extends AbstractWeatherCode> {
    protected abstract AbstractWeatherCodeService<T> getService(FakeWeatherProvider provider);

    @Test
    void testRetrieveFromAirportInvalid() {
        ParseException e = assertThrows(ParseException.class, () -> getService(new FakeWeatherProvider()).retrieveFromAirport("RandomIcao"));
        assertEquals(ErrorCodes.ERROR_CODE_INVALID_ICAO, e.getErrorCode());
    }

    @Test
    void testRetrieveFromAirport() throws Exception {
        T res = getService(new FakeWeatherProvider()).retrieveFromAirport("LFPG");
        assertThat(res, notNullValue());
        assertThat(res.getAirport().getIcao(), is("LFPG"));
    }

    @Test
    void testRetrieveFromAirportNotFound() {
        ParseException e = assertThrows(ParseException.class, () -> getService(new FakeWeatherProvider()).retrieveFromAirport("lftm"));
        assertEquals(ErrorCodes.ERROR_CODE_INVALID_ICAO, e.getErrorCode());
    }
}
