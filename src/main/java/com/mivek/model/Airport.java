package com.mivek.model;


public class Airport{
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
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		this.iata = iata;
	}
	public String getIcao() {
		return icao;
	}
	public void setIcao(String icao) {
		this.icao = icao;
	}

	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public int getAltitude() {
		return altitude;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
	}
	public String getTzDatabase() {
		return tzDatabase;
	}
	public void setTzDatabase(String tzDatabase) {
		this.tzDatabase = tzDatabase;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}
		
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}else if(obj instanceof Airport) {
			Airport o =(Airport)obj;
			if(this.icao.equals(o.getIcao())) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	@Override
	public String toString() {
		return this.name + " (" + this.icao + ")";
	}
}
