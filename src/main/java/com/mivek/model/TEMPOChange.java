package com.mivek.model;

import com.mivek.enums.WeatherChangeType;

/**
 * Class representing a Tempo change in a taf message.
 * @author mivek
 */
public final class TEMPOChange extends AbstractWeatherChange<Validity> {
	/**
	 * Default constructor.
	 */
	public TEMPOChange() {
		super(WeatherChangeType.TEMPO);
	}

}
