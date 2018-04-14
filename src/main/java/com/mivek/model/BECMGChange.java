package com.mivek.model;

import com.mivek.enums.WeatherChangeType;

/**
 * Class representing a BECMG change of a TAF.
 *
 * @author mivek
 *
 */
public class BECMGChange extends AbstractWeatherChange<Validity> {

	/**
	 * Constructor.
	 */
	public BECMGChange() {
		super(WeatherChangeType.BECMG);
	}

}
