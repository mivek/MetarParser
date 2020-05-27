package io.github.mivek.model;

/**
 * Represents an airport.
 *
 * @author mivek
 */
public class Airport {
    /** Name of the airport. */
    private String name;
    /** Name of the city. */
    private String city;
    /** Country of the airport. */
    private Country country;
    /** Iata code of the airport. */
    private String iata;
    /** Icao code of the airport. */
    private String icao;
    /** Latitude of the airport. */
    private double latitude;
    /** Longitude of the airport. */
    private double longitude;
    /** Altitude of the airport. */
    private int altitude;
    /** Timezone of the airport. */
    private String timezone;
    /** DST of the airport. */
    private String dst;
    /** tzdatabase of the aiport. */
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
     * @param name name of the airport.
     */
    public void setName(final String name) {
        this.name = name;
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
     * @param city string of the name of the city.
     */
    public void setCity(final String city) {
        this.city = city;
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
     * @param country The country to set.
     */
    public void setCountry(final Country country) {
        this.country = country;
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
     * @param iata string of iata.
     */
    public void setIata(final String iata) {
        this.iata = iata;
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
     * @param icao string of icao.
     */
    public void setIcao(final String icao) {
        this.icao = icao;
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
     * Setter of latitude.
     *
     * @param latitude Latitude to set.
     */
    public void setLatitude(final double latitude) {
        this.latitude = latitude;
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
     * Setter of longitude.
     *
     * @param longitude to set.
     */
    public void setLongitude(final double longitude) {
        this.longitude = longitude;
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
     * Setter of altitude.
     *
     * @param altitude the altitude to set.
     */
    public void setAltitude(final int altitude) {
        this.altitude = altitude;
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
     * @param timezone timezone string to set.
     */
    public void setTimezone(final String timezone) {
        this.timezone = timezone;
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
     * @param dst the dst to set.
     */
    public void setDst(final String dst) {
        this.dst = dst;
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
     * @param tzDatabase The tzDatabase to set.
     */
    public void setTzDatabase(final String tzDatabase) {
        this.tzDatabase = tzDatabase;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof Airport) {
            return icao.equals(((Airport) obj).getIcao());
        }
        return false;
    }

    @Override
    public final int hashCode() {
        int result;
        result = 31 * icao.hashCode();
        return result;
    }

    @Override
    public final String toString() {
        return name + " (" + icao + ")";
    }
}
