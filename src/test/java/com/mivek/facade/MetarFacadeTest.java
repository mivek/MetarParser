package com.mivek.facade;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.mivek.controller.ParseController;
import com.mivek.exception.InvalidIcaoException;
import com.mivek.model.Metar;

public class MetarFacadeTest {
	@Rule
	public ExpectedException thrown;

	@Test(expected = InvalidIcaoException.class)
	public void testRetrieveFromAirportInvalid() throws Exception {
		MetarFacade.getInstance().retrieveFromAirport("RAndomeString");
	}

	@Test
	public void testDecodeValidMetar() {
		String code = "LFPG 251830Z 17013KT 9999 OVC006 04/03 Q1012 NOSIG";

		Metar res = MetarFacade.getInstance().decode(code);

		assertNotNull(res);
		assertEquals(ParseController.getInstance().getAirports().get("LFPG"), res.getAirport());
		assertEquals(25, res.getDay().intValue());
		// Time
		assertNotNull(res.getTime());
		assertEquals(18, res.getTime().getHours());
		assertEquals(30, res.getTime().getMinutes());
		// Wind
		assertNotNull(res.getWind());
		assertEquals(i18n.Messages.CONVERTER_S, res.getWind().getDirection());
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
}
