package com.mivek.facade;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.mivek.exception.InvalidIcaoException;
import com.mivek.exception.Messages;

public class MetarFacadeTest {
	@Rule
	public ExpectedException thrown;

	@Test
	public void testRetrieveFromAirportInvalid() throws Exception {
		thrown.expect(InvalidIcaoException.class);
		thrown.expectMessage(Messages.INVALID_ICAO);
		MetarFacade.getInstance().retrieveFromAirport("RAndomeString");
	}
}
