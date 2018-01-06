package com.mivek.model;

import com.mivek.enums.WeatherChangeType;

public class FMChange extends AbstractWeatherChange<BeginningValidity> {

	public FMChange() {
		super();
		fType = WeatherChangeType.FM;
	}

}
