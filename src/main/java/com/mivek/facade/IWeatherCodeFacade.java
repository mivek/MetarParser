package com.mivek.facade;

import com.mivek.model.WeatherCode;

public interface IWeatherCodeFacade<T extends WeatherCode> {

	T decode(String pCode);

	T retrieveFromAirport(String pIcao) throws Exception;
}
