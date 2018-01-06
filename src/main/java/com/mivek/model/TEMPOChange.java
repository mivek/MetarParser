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
public final class TEMPOChange extends AbstractWeatherChange<Validity> {
	/**
	 * 
	 */
	public TEMPOChange() {
		super();
		fType = WeatherChangeType.TEMPO;
	}

}
