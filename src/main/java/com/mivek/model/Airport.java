package com.mivek.model;

/**
 * Represents an airport.
 *
 * @author mivek
 *
 */
public class Airport {
	/**
	 * Name of the airport.
	 */
	private String name;
	/**
	 * Name of the city.
	 */
	private String city;
	/**
	 * Country of the airport.
	 */
	private Country country;
	/**
	 * Iata code of the airport.
	 */
	private String iata;
	/**
	 * Icao code of the airport.
	 */
	private String icao;
	/**
	 * Latitude of the airport.
	 */
	private double latitude;
	/**
	 * Longitude of the airport.
	 */
	private double longitude;
	/**
	 * Altitude of the airport.
	 */
	private int altitude;
	/**
	 * Timezone of the airport.
	 */
	private String timezone;
	/**
	 * DST of the airport.
	 */
	private String dst;
	/**
	 * tzdatabase of the aiport.
	 */
	private String tzDatabase;

	/**
	 * Getter of name.
	 *
	 * @return string name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of name.
	 *
	 * @param pName
	 *            name of the airport.
	 */
	public void setName(final String pName) {
		this.name = pName;
	}

	/**
	 * Getter of city.
	 *
	 * @return string of city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Setter of the city.
	 *
	 * @param pCity
	 *            string of the name of the city.
	 */
	public void setCity(final String pCity) {
		this.city = pCity;
	}

	/**
	 * Getter of country.
	 *
	 * @return a country object.
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * Setter of country.
	 *
	 * @param pCountry
	 *            The country to set.
	 */
	public void setCountry(final Country pCountry) {
		this.country = pCountry;
	}

	/**
	 * Getter of iata.
	 *
	 * @return string of iata.
	 */
	public String getIata() {
		return iata;
	}

	/**
	 * Setter of iata code.
	 *
	 * @param pIata
	 *            string of iata.
	 */
	public void setIata(final String pIata) {
		this.iata = pIata;
	}

	/**
	 * Getter of Icao code.
	 *
	 * @return string icao code.
	 */
	public String getIcao() {
		return icao;
	}

	/**
	 * Setter of icao.
	 *
	 * @param pIcao
	 *            string of icao.
	 */
	public void setIcao(final String pIcao) {
		this.icao = pIcao;
	}

	/**
	 * Getter of latitude.
	 *
	 * @return latitude.
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Getter of longitude.
	 *
	 * @return longitude.
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Getter of altitude.
	 *
	 * @return altitude.
	 */
	public int getAltitude() {
		return altitude;
	}

	/**
	 * Getter of timezone.
	 *
	 * @return string of timezone.
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 * Setter of timezone.
	 *
	 * @param pTimezone
	 *            timezone string to set.
	 */
	public void setTimezone(final String pTimezone) {
		this.timezone = pTimezone;
	}

	/**
	 * Getter of DST.
	 *
	 * @return string of dst.
	 */
	public String getDst() {
		return dst;
	}

	/**
	 * Setter of DST.
	 *
	 * @param pDst
	 *            the dst to set.
	 */
	public void setDst(final String pDst) {
		this.dst = pDst;
	}

	/**
	 * Getter of tzDatabase.
	 *
	 * @return string of tzDatabase.
	 */
	public String getTzDatabase() {
		return tzDatabase;
	}

	/**
	 * Setter of tzDatabase.
	 *
	 * @param pTzDatabase
	 *            The tzDatabase to set.
	 */
	public void setTzDatabase(final String pTzDatabase) {
		this.tzDatabase = pTzDatabase;
	}

	/**
	 * Setter of latitude.
	 *
	 * @param pLatitude
	 *            Latitude to set.
	 */
	public void setLatitude(final double pLatitude) {
		this.latitude = pLatitude;
	}

	/**
	 * Setter of longitude.
	 *
	 * @param pLongitude
	 *            to set.
	 */
	public void setLongitude(final double pLongitude) {
		this.longitude = pLongitude;
	}

	/**
	 * Setter of altitude.
	 *
	 * @param pAltitude
	 *            the altitude to set.
	 */
	public void setAltitude(final int pAltitude) {
		this.altitude = pAltitude;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Airport) {
			return this.icao.equals(((Airport) obj).getIcao());
		}
		return false;
	}

	@Override
	public final String toString() {
		return this.name + " (" + this.icao + ")";
	}
}
