package com.mivek.builder;

import com.mivek.model.Airport;
import com.mivek.model.Country;

/**
 *
 * @author mivek
 *
 */
public class AirportBuilder {
	/**
	 * The name of the airport.
	 */
	private String name;
	/**
	 * The city of the airport.
	 */
	private String city;
	/**
	 * The country of the airport.
	 */
	private Country country;
	/**
	 * The iata code of the airport.
	 */
	private String iata;
	/**
	 * The icao code of the airport.
	 */
	private String icao;
	/**
	 * The latitude of the airport.
	 */
	private double latitude;
	/**
	 * The longitude of the airport.
	 */
	private double longitude;
	/**
	 * The altitude of the airport.
	 */
	private int altitude;
	/**
	 * The timezone of the airport.
	 */
	private String timezone;
	/**
	 * The dst of the airport.
	 */
	private String dst;
	/**
	 * The tzDatabase of the aiport.
	 */
	private String tzDatabase;

	/**
	 * Constructor. Adds a default icao code.
	 */
	public AirportBuilder() {
		this.icao = "icao";
	}

	/**
	 * Adds a name to the airport builder.
	 *
	 * @param pName
	 *            String for the name of the airport.
	 * @return the instance of the builder.
	 */
	public AirportBuilder withName(final String pName) {
		this.name = pName;
		return this;
	}

	/**
	 * Adds a default name to the aiport builder.
	 *
	 * @return the instance of the builder.
	 */
	public AirportBuilder withName() {
		this.name = "airport name";
		return this;
	}

	/**
	 * Adds a city name to the builder.
	 *
	 * @param pCity
	 *            the name of the city.
	 * @return the instance of the builder.
	 */
	public AirportBuilder withCity(final String pCity) {
		this.city = pCity;
		return this;
	}

	/**
	 * Adds a default city name to the builder.
	 *
	 * @return the instance of the builder.
	 */
	public AirportBuilder withCity() {
		this.city = "city name";
		return this;
	}

	/**
	 * Adds a country to the builder.
	 *
	 * @param pCountry
	 *            a country.
	 * @return the instance of the builder.
	 */
	public AirportBuilder withCountry(final Country pCountry) {
		this.country = pCountry;
		return this;
	}

	/**
	 * Adds a default country to the builder.
	 *
	 * @return the instance of the builder.
	 */
	public AirportBuilder withCountry() {
		Country c = new Country();
		c.setName("country name");
		this.country = c;
		return this;
	}

	/**
	 * Adds a iata code to the builder.
	 *
	 * @param pIata
	 *            the iata code.
	 * @return the instance of the builder.
	 */
	public AirportBuilder withIata(final String pIata) {
		this.iata = pIata;
		return this;
	}

	/**
	 * Adds a icao code to the builder.
	 *
	 * @param pIcao
	 *            the icao code.
	 * @return an instance of the builder.
	 */
	public AirportBuilder withIcao(final String pIcao) {
		this.icao = pIcao;
		return this;
	}

	/**
	 * Adds a longitude to the builder.
	 *
	 * @param pLongitude
	 *            the longitude to add.
	 * @return the instance of the builder.
	 */
	public AirportBuilder withLongitude(final double pLongitude) {
		this.longitude = pLongitude;
		return this;
	}

	/**
	 * Adds a default longitude to the builder.
	 *
	 * @return the instance of the builder.
	 */
	public AirportBuilder withLongitude() {
		this.longitude = 2.0;
		return this;
	}

	/**
	 * Adds a latitude to the builder.
	 *
	 * @param pLatitude
	 *            the latitude to add.
	 * @return the instance of the builder.
	 */
	public AirportBuilder withLatitude(final double pLatitude) {
		this.latitude = pLatitude;
		return this;
	}

	/**
	 * Adds a default latitude to the builder.
	 *
	 * @return the instance of the builder.
	 */
	public AirportBuilder withLatitude() {
		this.latitude = 2.0;
		return this;
	}

	/**
	 * Adds an altitude to the builder.
	 *
	 * @param pAltitude
	 *            the altitude to add.
	 * @return an instance of the builder.
	 */
	public AirportBuilder withAltitude(final int pAltitude) {
		this.altitude = pAltitude;
		return this;
	}

	/**
	 * Adds a default altitude to the builder.
	 *
	 * @return an instance of the builder.
	 */
	public AirportBuilder withAltitude() {
		this.altitude = 1;
		return this;
	}

	/**
	 * Builds an airport with the attributes of the builder.
	 *
	 * @return an airport object.
	 */
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
