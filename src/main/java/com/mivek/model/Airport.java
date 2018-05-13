package com.mivek.model;

/**
 * Represents an airport.
 * @author mivek
 */
public class Airport {
	/**
	 * Name of the airport.
	 */
	private String fName;
	/**
	 * Name of the city.
	 */
	private String fCity;
	/**
	 * Country of the airport.
	 */
	private Country fCountry;
	/**
	 * Iata code of the airport.
	 */
	private String fIata;
	/**
	 * Icao code of the airport.
	 */
	private String fIcao;
	/**
	 * Latitude of the airport.
	 */
	private double fLatitude;
	/**
	 * Longitude of the airport.
	 */
	private double fLongitude;
	/**
	 * Altitude of the airport.
	 */
	private int fAltitude;
	/**
	 * Timezone of the airport.
	 */
	private String fTimezone;
	/**
	 * DST of the airport.
	 */
	private String fDst;
	/**
	 * tzdatabase of the aiport.
	 */
	private String fTzDatabase;

	/**
	 * Getter of name.
	 * @return string name.
	 */
	public String getName() {
		return fName;
	}

	/**
	 * Setter of name.
	 * @param pName name of the airport.
	 */
	public void setName(final String pName) {
		fName = pName;
	}

	/**
	 * Getter of city.
	 * @return string of city.
	 */
	public String getCity() {
		return fCity;
	}

	/**
	 * Setter of the city.
	 * @param pCity string of the name of the city.
	 */
	public void setCity(final String pCity) {
		fCity = pCity;
	}

	/**
	 * Getter of country.
	 * @return a country object.
	 */
	public Country getCountry() {
		return fCountry;
	}

	/**
	 * Setter of country.
	 * @param pCountry The country to set.
	 */
	public void setCountry(final Country pCountry) {
		fCountry = pCountry;
	}

	/**
	 * Getter of iata.
	 * @return string of iata.
	 */
	public String getIata() {
		return fIata;
	}

	/**
	 * Setter of iata code.
	 * @param pIata string of iata.
	 */
	public void setIata(final String pIata) {
		fIata = pIata;
	}

	/**
	 * Getter of Icao code.
	 * @return string icao code.
	 */
	public String getIcao() {
		return fIcao;
	}

	/**
	 * Setter of icao.
	 * @param pIcao string of icao.
	 */
	public void setIcao(final String pIcao) {
		fIcao = pIcao;
	}

	/**
	 * Getter of latitude.
	 * @return latitude.
	 */
	public double getLatitude() {
		return fLatitude;
	}

	/**
	 * Getter of longitude.
	 * @return longitude.
	 */
	public double getLongitude() {
		return fLongitude;
	}

	/**
	 * Getter of altitude.
	 * @return altitude.
	 */
	public int getAltitude() {
		return fAltitude;
	}

	/**
	 * Getter of timezone.
	 * @return string of timezone.
	 */
	public String getTimezone() {
		return fTimezone;
	}

	/**
	 * Setter of timezone.
	 * @param pTimezone timezone string to set.
	 */
	public void setTimezone(final String pTimezone) {
		fTimezone = pTimezone;
	}

	/**
	 * Getter of DST.
	 * @return string of dst.
	 */
	public String getDst() {
		return fDst;
	}

	/**
	 * Setter of DST.
	 * @param pDst the dst to set.
	 */
	public void setDst(final String pDst) {
		fDst = pDst;
	}

	/**
	 * Getter of tzDatabase.
	 * @return string of tzDatabase.
	 */
	public String getTzDatabase() {
		return fTzDatabase;
	}

	/**
	 * Setter of tzDatabase.
	 * @param pTzDatabase The tzDatabase to set.
	 */
	public void setTzDatabase(final String pTzDatabase) {
		fTzDatabase = pTzDatabase;
	}

	/**
	 * Setter of latitude.
	 * @param pLatitude Latitude to set.
	 */
	public void setLatitude(final double pLatitude) {
		fLatitude = pLatitude;
	}

	/**
	 * Setter of longitude.
	 * @param pLongitude to set.
	 */
	public void setLongitude(final double pLongitude) {
		fLongitude = pLongitude;
	}

	/**
	 * Setter of altitude.
	 * @param pAltitude the altitude to set.
	 */
	public void setAltitude(final int pAltitude) {
		fAltitude = pAltitude;
	}

	@Override
	public final boolean equals(final Object pObj) {
		if (pObj instanceof Airport) {
			return fIcao.equals(((Airport) pObj).getIcao());
		}
		return false;
	}

	@Override
	public final int hashCode() {
		int result;
		result = 31 * fIcao.hashCode();
		return result;
	}

	@Override
	public final String toString() {
		return fName + " (" + fIcao + ")";
	}
}
