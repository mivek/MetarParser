package com.mivek.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.mivek.exception.InvalidIcaoException;
import com.mivek.model.Metar;
import com.mivek.parser.MetarParser;

/**
 * Class representing the facade for metars.
 *
 * @author mivek
 */
public final class MetarFacade implements IWeatherCodeFacade<Metar> {
	/**
	 * Instance.
	 */
	private static MetarFacade instance = new MetarFacade();

	/**
	 * Method to parse a metar.
	 *
	 * @param pCode
	 *            the metar to decode.
	 * @return a metar object.
	 */
	@Override
	public Metar decode(final String pCode) {
		return MetarParser.getInstance().parse(pCode);
	}

	/**
	 * Gets the metar of an airport.
	 * @param icao String icao of the airport
	 * @return the decoded metar.
	 * @throws InvalidIcaoException When the icao is not valid.
	 * @throws IOException When an error occurs.
	 */
	@Override
	public Metar retrieveFromAirport(final String icao) throws InvalidIcaoException, IOException {
		if (icao.length() != IWeatherCodeFacade.ICAO) {
			throw new InvalidIcaoException(i18n.Messages.INVALID_ICAO); // $NON-NLS-1$
		}
		String website = "http://tgftp.nws.noaa.gov/data/observations/metar/stations/" + icao.toUpperCase() //$NON-NLS-1$
				+ ".TXT"; //$NON-NLS-1$
		URL url = new URL(website);
		URLConnection urlCo = url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(urlCo.getInputStream()));
		String[] lines = (String[]) br.lines().toArray();
		br.close();
		return MetarParser.getInstance().parse(lines[1]);
	}

	/**
	 * Private constructor.
	 */
	private MetarFacade() {
	}

	/**
	 * Returns a instance of the class.
	 *
	 * @return the instance of the class.
	 */
	public static MetarFacade getInstance() {
		return instance;
	}
}
