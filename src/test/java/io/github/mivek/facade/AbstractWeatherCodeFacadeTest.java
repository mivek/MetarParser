package io.github.mivek.facade;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.AbstractWeatherCode;

@Ignore
public abstract class AbstractWeatherCodeFacadeTest<T extends AbstractWeatherCode> {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    protected abstract AbstractWeatherCodeFacade<T> getSut();


    @Test
    public void testRetrieveFromAirportInvalid() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expect(hasProperty("errorCode", is(ErrorCodes.ERROR_CODE_INVALID_ICAO)));
        getSut().retrieveFromAirport("RAndomeString");
    }

    @Test
    public void testRetrieveFromAirport() throws IOException, ParseException, URISyntaxException {
        T res = getSut().retrieveFromAirport("LFPG");
        assertThat(res, notNullValue());
        assertThat(res.getAirport().getIcao(), is("LFPG"));
    }

}
