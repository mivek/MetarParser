package io.github.mivek.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.AbstractWeatherCode;
import java.io.IOException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public abstract class AbstractWeatherCodeServiceTest<T extends AbstractWeatherCode> {
  protected abstract AbstractWeatherCodeService<T> getSut();

  @Test
  public void testRetrieveFromAirportInvalid() {
    ParseException e =
        assertThrows(ParseException.class, () -> getSut().retrieveFromAirport("RandomIcao"));
    assertEquals(ErrorCodes.ERROR_CODE_INVALID_ICAO, e.getErrorCode());
  }

  @Test
  public void testRetrieveFromAirport() throws IOException, ParseException {
    T res = getSut().retrieveFromAirport("LFPG");
    assertThat(res, notNullValue());
    assertThat(res.getAirport().getIcao(), is("LFPG"));
  }
}
