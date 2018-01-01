package com.mivek.model;

import com.mivek.enums.WeatherChangeType;

public class FMChange extends AbstractWeatherChange {

	public FMChange() {
		super();
		fType = WeatherChangeType.FM;
	}

}
