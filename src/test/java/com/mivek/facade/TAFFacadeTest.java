package com.mivek.facade;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import com.mivek.exception.InvalidIcaoException;

public class TAFFacadeTest {
	TAFFacade sut = TAFFacade.getInstance();

	@Test(expected = InvalidIcaoException.class)
	public void testRetrieveFromAirportInvalid() throws Exception {
		sut.retrieveFromAirport("RAndomeString");
	}

	@Test
	public void testRetrieve() throws InvalidIcaoException, IOException, URISyntaxException {
		sut.retrieveFromAirport("LFPG");
	}
}
