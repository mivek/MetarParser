package com.mivek.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import com.mivek.exception.InvalidIcaoException;
import com.mivek.model.TAF;

public class TAFFacadeTest {
	TAFFacade sut = TAFFacade.getInstance();

	@Test(expected = InvalidIcaoException.class)
	public void testRetrieveFromAirportInvalid() throws Exception {
		sut.retrieveFromAirport("RAndomeString");
	}

	@Test
	public void testRetrieve() throws InvalidIcaoException, IOException, URISyntaxException {
		TAF taf = sut.retrieveFromAirport("LFPG");
		assertNotNull(taf);
		assertEquals("LFPG", taf.getAirport().getIcao());
	}
}
