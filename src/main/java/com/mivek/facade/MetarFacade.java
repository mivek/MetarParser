/**
 * 
 */
package com.mivek.facade;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.net.URLConnection;

import com.mivek.controller.ParseController;
import com.mivek.exception.InvalidIcaoException;
import com.mivek.model.Metar;

/**
 * @author mivek
 *
 */
public class MetarFacade {
	private static MetarFacade instance = null;

	/**
	 * Method to parse a metar.
	 * 
	 * @param pCode,
	 *            the metar to decode.
	 * @return a metar object.
	 */
	Metar decode(String pCode) {
		return ParseController.getInstance().parseMetarAction(pCode);
	}

	/**
	 * 
	 * @param icao
	 * @return
	 * @throws Exception
	 */
	Metar retrieveFromAirport(String icao) throws Exception {
		if (icao.length() == 4) {
			String website = "http://tgftp.nws.noaa.gov/data/observations/metar/stations/" + icao.toUpperCase() //$NON-NLS-1$
					+ ".TXT"; //$NON-NLS-1$
			URL url = new URL(website);
			URLConnection urlCo = url.openConnection();
			LineNumberReader in = new LineNumberReader(new InputStreamReader(urlCo.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (in.getLineNumber() == 2) {
					return ParseController.getInstance().parseMetarAction(inputLine);
				}
			}
			in.close();
		}
		throw new InvalidIcaoException(i18n.Messages.INVALID_ICAO); // $NON-NLS-1$
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
		if (instance == null) {
			instance = new MetarFacade();
		}
		return instance;
	}
}
