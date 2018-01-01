/**
 * 
 */
package com.mivek.model;

import com.mivek.enums.WeatherChangeType;

/**
 * @author mivek
 * @param <E>
 *
 */
public final class TEMPOChange extends AbstractWeatherChange {

	/**
	 * 
	 */
	public TEMPOChange() {
		super();
		fType = WeatherChangeType.TEMPO;
	}

}
