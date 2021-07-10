package io.github.mivek.service;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.AbstractWeatherCode;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@Ignore
public abstract class AbstractWeatherCodeServiceTest<T extends AbstractWeatherCode> {
    protected abstract AbstractWeatherCodeService<T> getSut();

    @Test
    public void testRetrieveFromAirportInvalid() {
        ParseException e = assertThrows(ParseException.class, () -> getSut().retrieveFromAirport("RandomIcao"));
        assertEquals(ErrorCodes.ERROR_CODE_INVALID_ICAO, e.getErrorCode());
    }

    @Test
    public void testRetrieveFromAirport() throws IOException, ParseException {
        T res = getSut().retrieveFromAirport("LFPG");
        assertThat(res, notNullValue());
        assertThat(res.getAirport().getIcao(), is("LFPG"));
    }

}
