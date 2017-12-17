package com.mivek.builder;

import com.mivek.model.Airport;
import com.mivek.model.Country;

/**
 * 
 * @author mivek
 *
 */
public class AirportBuilder {
	private String name;
	private String city;
	private Country country;
	private String iata;
	private String icao;
	private double latitude;
	private double longitude;
	private int altitude;
	private String timezone;
	private String dst;
	private String tzDatabase;

	public AirportBuilder() {
		this.icao = "icao";
	}

	public AirportBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public AirportBuilder withName() {
		this.name = "airport name";
		return this;
	}

	public AirportBuilder withCity(String city) {
		this.city = city;
		return this;
	}

	public AirportBuilder withCity() {
		this.city = "city name";
		return this;
	}

	public AirportBuilder withCountry(Country c) {
		this.country = c;
		return this;
	}

	public AirportBuilder withCountry() {
		Country c = new Country();
		c.setName("country name");
		this.country = c;
		return this;
	}

	public AirportBuilder withIata(String iata) {
		this.iata = iata;
		return this;
	}

	public AirportBuilder withIcao(String icao) {
		this.icao = icao;
		return this;
	}

	public AirportBuilder withLongitude(double longitude) {
		this.longitude = longitude;
		return this;
	}

	public AirportBuilder withLongitude() {
		this.longitude = 2.0;
		return this;
	}

	public AirportBuilder withLatitude(double latitude) {
		this.latitude = latitude;
		return this;
	}

	public AirportBuilder withLatitude() {
		this.latitude = 2.0;
		return this;
	}

	public AirportBuilder withAltitude(int altitude) {
		this.altitude = altitude;
		return this;
	}

	public AirportBuilder withAltitude() {
		this.altitude = 1;
		return this;
	}
	
	public Airport build() {
		Airport a = new Airport();
		a.setAltitude(altitude);
		a.setCity(city);
		a.setCountry(country);
		a.setDst(dst);
		a.setIata(iata);
		a.setIcao(icao);
		a.setLatitude(latitude);
		a.setLongitude(longitude);
		a.setName(name);
		a.setTimezone(timezone);
		a.setTzDatabase(tzDatabase);
		return a;
	}
}
