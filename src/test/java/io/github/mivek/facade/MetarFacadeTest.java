package io.github.mivek.facade;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.github.mivek.exception.ParseException;
import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.Metar;

public class MetarFacadeTest extends AbstractWeatherCodeFacadeTest<Metar> {
    @Test
    public void testDecodeValidMetar() throws ParseException {
        String code = "LFPG 251830Z 17013KT 9999 OVC006 04/03 Q1012 NOSIG";

        Metar res = MetarFacade.getInstance().decode(code);

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
    protected AbstractWeatherCodeFacade<Metar> getSut() {
        return MetarFacade.getInstance();
    }
}
