/**
 * 
 */
package com.mivek.facade;

import com.mivek.model.WeatherCode;
import com.mivek.parser.AbstractParser;

/**
 * @author mivek
 *
 */
public abstract class AbstractWeatherCodeFacade<T extends WeatherCode> implements IWeatherCodeFacade<T> {

	/**
	 * Const for icao length.
	 */
	public static final int ICAO = 4;

	private AbstractParser<T> fParser;

	protected AbstractWeatherCodeFacade(AbstractParser<T> pParser) {
		fParser = pParser;
	}

	/**
	 * @return the parser.
	 */
	protected AbstractParser<T> getParser() {
		return fParser;
	}

}
