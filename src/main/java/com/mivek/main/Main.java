package com.mivek.main;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.mivek.model.Airport;
import com.mivek.model.Country;
import com.opencsv.CSVReader;

public class Main {
	
	private static final String AIRPORT_FILE = "data/airports.dat";
	private static final String COUNTRIES_FILE = "data/countries.dat";
	private static Map<String, Airport> airports;
	private static Map<String, Country> countries;
	
	public static void main(String[] args) {

		initCountries();
		
		System.out.println("Map of countries initialized.");
		
		initAirports();
		System.out.println("Map of airports initialized.");
	}

	public static void initAirports() {
		airports = new HashMap<>();
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(AIRPORT_FILE));
			String[] line;
			while((line = reader.readNext())!=null) {
				Airport airport = new Airport();
				airport.setName(line[1]);
				airport.setCity(line[2]);
				airport.setCountry(countries.get(line[3]));
				airport.setIata(line[4]);
				airport.setIcao(line[5]);
				airport.setLatitude(Double.parseDouble(line[6]));
				airport.setLongitude(Double.parseDouble(line[7]));
				airport.setAltitude(Integer.parseInt(line[8]));
				airport.setTimezone(line[9]);
				airport.setDst(line[10]);
				airports.put(airport.getIcao(), airport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initCountries() {
		countries = new HashMap<>();
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(COUNTRIES_FILE));
			String[] line;
			while((line = reader.readNext())!=null) {
				Country country = new Country();
				country.setName(line[0]);
				countries.put(country.getName(), country);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the airports
	 */
	public static Map<String, Airport> getAirports() {
		return airports;
	}

	/**
	 * @return the countries
	 */
	public static Map<String, Country> getCountries() {
		return countries;
	}	
}