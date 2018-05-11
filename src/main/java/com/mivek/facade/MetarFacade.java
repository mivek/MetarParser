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
public final class MetarFacade extends AbstractWeatherCodeFacade<Metar> {
	/**
	 * Instance.
	 */
	private static MetarFacade instance = new MetarFacade();

	/**
	 * Private constructor.
	 */
	private MetarFacade() {
		super(MetarParser.getInstance());
	}

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
	 * @param pIcao String icao of the airport
	 * @return the decoded metar.
	 * @throws InvalidIcaoException When the icao is not valid.
	 * @throws IOException When an error occurs.
	 */
	@Override
	public Metar retrieveFromAirport(final String pIcao) throws InvalidIcaoException, IOException {
		if (pIcao.length() != AbstractWeatherCodeFacade.ICAO) {
			throw new InvalidIcaoException(i18n.Messages.INVALID_ICAO); // $NON-NLS-1$
		}
		String website = "http://tgftp.nws.noaa.gov/data/observations/metar/stations/" + pIcao.toUpperCase() //$NON-NLS-1$
				+ ".TXT"; //$NON-NLS-1$
		URL url = new URL(website);
		URLConnection urlCo = url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(urlCo.getInputStream()));
		String line = (String) br.lines().toArray()[1];
		br.close();
		return MetarParser.getInstance().parse(line);
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
