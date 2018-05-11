/**
 * 
 */
package com.mivek.facade;

import com.mivek.model.WeatherCode;
import com.mivek.parser.AbstractParser;

/**
 * Abstract facade.
 * @author mivek
 * @param <T> a concrete sub-class of {@link WeatherCode}.
 */
public abstract class AbstractWeatherCodeFacade<T extends WeatherCode> implements IWeatherCodeFacade<T> {

	/**
	 * Const for icao length.
	 */
	public static final int ICAO = 4;

	/**
	 * The parser.
	 */
	private AbstractParser<T> fParser;

	/**
	 * Constructor.
	 * @param pParser the parser to set.
	 */
	protected AbstractWeatherCodeFacade(AbstractParser<T> pParser) {
		fParser = pParser;
	}

}
