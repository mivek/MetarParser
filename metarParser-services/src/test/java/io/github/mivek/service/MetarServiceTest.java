package io.github.mivek.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import io.github.mivek.exception.ParseException;
import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.Metar;
import org.junit.jupiter.api.Test;

public class MetarServiceTest extends AbstractWeatherCodeServiceTest<Metar> {
  @Test
  public void testDecodeValidMetar() throws ParseException {
    String code = "LFPG 251830Z 17013KT 9999 OVC006 04/03 Q1012 NOSIG";

    Metar res = MetarService.getInstance().decode(code);

    assertNotNull(res);
    assertEquals("LFPG", res.getAirport().getIcao());
    assertEquals(25, res.getDay().intValue());
    // Time
    assertNotNull(res.getTime());
    assertEquals(18, res.getTime().getHour());
    assertEquals(30, res.getTime().getMinute());
    // Wind
    assertNotNull(res.getWind());
    assertEquals(Messages.getInstance().getString("Converter.S"), res.getWind().getDirection());
    assertEquals(13, res.getWind().getSpeed());
    assertEquals("KT", res.getWind().getUnit());
    // Visibility
    assertEquals(">10km", res.getVisibility().getMainVisibility());
    // Temperatures
    assertEquals(4, res.getTemperature().intValue());
    assertEquals(3, res.getDewPoint().intValue());
    // Altimeter
    assertEquals(1012, res.getAltimeter().intValue());
    assertTrue(res.isNosig());
    // Clouds.
    assertThat(res.getClouds(), is(not(empty())));
  }

  @Override
  protected AbstractWeatherCodeService<Metar> getSut() {
    return MetarService.getInstance();
  }
}
