package com.mivek.builder;

import com.mivek.model.Airport;
import com.mivek.model.Country;

/**
 * Builder for {@link Airport} class.
 * @author mivek
 */
public class AirportBuilder {
	/**
	 * The name of the airport.
	 */
	private String fName;
	/**
	 * The city of the airport.
	 */
	private String fCity;
	/**
	 * The country of the airport.
	 */
	private Country fCountry;
	/**
	 * The iata code of the airport.
	 */
	private String fIata;
	/**
	 * The icao code of the airport.
	 */
	private String fIcao;
	/**
	 * The latitude of the airport.
	 */
	private double fLatitude;
	/**
	 * The longitude of the airport.
	 */
	private double fLongitude;
	/**
	 * The altitude of the airport.
	 */
	private int fAltitude;
	/**
	 * The timezone of the airport.
	 */
	private String fTimezone;
	/**
	 * The dst of the airport.
	 */
	private String fDst;
	/**
	 * The tzDatabase of the aiport.
	 */
	private String fTzDatabase;

	/**
	 * Constructor. Adds a default icao code.
	 */
	public AirportBuilder() {
		fIcao = "icao";
	}

	/**
	 * Adds a name to the airport builder.
	 *
	 * @param pName
	 *            String for the name of the airport.
	 * @return the instance of the builder.
	 */
	public AirportBuilder withName(final String pName) {
		fName = pName;
		return this;
	}

	/**
	 * Adds a default name to the aiport builder.
	 *
	 * @return the instance of the builder.
	 */
	public AirportBuilder withName() {
		fName = "airport name";
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
		fCity = pCity;
		return this;
	}

	/**
	 * Adds a default city name to the builder.
	 *
	 * @return the instance of the builder.
	 */
	public AirportBuilder withCity() {
		fCity = "city name";
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
		fCountry = pCountry;
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
		fCountry = c;
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
		fIata = pIata;
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
		fIcao = pIcao;
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
		fLongitude = pLongitude;
		return this;
	}

	/**
	 * Adds a default longitude to the builder.
	 *
	 * @return the instance of the builder.
	 */
	public AirportBuilder withLongitude() {
		fLongitude = 2.0;
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
		fLatitude = pLatitude;
		return this;
	}

	/**
	 * Adds a default latitude to the builder.
	 *
	 * @return the instance of the builder.
	 */
	public AirportBuilder withLatitude() {
		fLatitude = 2.0;
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
		fAltitude = pAltitude;
		return this;
	}

	/**
	 * Adds a default altitude to the builder.
	 *
	 * @return an instance of the builder.
	 */
	public AirportBuilder withAltitude() {
		fAltitude = 1;
		return this;
	}

	/**
	 * Builds an airport with the attributes of the builder.
	 *
	 * @return an airport object.
	 */
	public Airport build() {
		Airport a = new Airport();
		a.setAltitude(fAltitude);
		a.setCity(fCity);
		a.setCountry(fCountry);
		a.setDst(fDst);
		a.setIata(fIata);
		a.setIcao(fIcao);
		a.setLatitude(fLatitude);
		a.setLongitude(fLongitude);
		a.setName(fName);
		a.setTimezone(fTimezone);
		a.setTzDatabase(fTzDatabase);
		return a;
	}
}
