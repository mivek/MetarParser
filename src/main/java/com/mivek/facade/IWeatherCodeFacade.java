package com.mivek.facade;

import java.io.IOException;
import java.net.URISyntaxException;

import com.mivek.exception.InvalidIcaoException;
import com.mivek.model.AbstractWeatherCode;

/**
 * Interface for facade.
 * @author mivek
 * @param <T> a concrete sub-class of {@link AbstractWeatherCode}
 */
public interface IWeatherCodeFacade<T extends AbstractWeatherCode> {
    /**
     * Decode method.
     * @param pCode the code to decode.
     * @return the decoded object corresponding to the message.
     */
    T decode(String pCode);

    /**
     * Retrieve code and decoded object from airport with icao.
     * @param pIcao the icao of the airport
     * @return the decoded object
     * @throws InvalidIcaoException when the icao is invalid.
     * @throws IOException When an error occurs
     * @throws URISyntaxException When an error occurs.
     */
    T retrieveFromAirport(String pIcao) throws InvalidIcaoException, IOException, URISyntaxException;
}
