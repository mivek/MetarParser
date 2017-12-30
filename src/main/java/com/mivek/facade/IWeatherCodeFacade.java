package com.mivek.facade;

import java.io.IOException;

import com.mivek.exception.InvalidIcaoException;
import com.mivek.model.WeatherCode;

public interface IWeatherCodeFacade<T extends WeatherCode> {

	T decode(String pCode);

	T retrieveFromAirport(String pIcao) throws InvalidIcaoException, IOException;
}
