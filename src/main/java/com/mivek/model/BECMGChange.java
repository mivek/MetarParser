package com.mivek.model;

import com.mivek.enums.WeatherChangeType;

/**
 * 
 * @author mivek
 *
 */
public class BECMGChange extends AbstractWeatherChange<Validity> {

	public BECMGChange() {
		super();
		fType = WeatherChangeType.BECMG;
	}

}
