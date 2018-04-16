package com.mivek.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import com.mivek.exception.InvalidIcaoException;
import com.mivek.model.TAF;
import com.mivek.parser.TAFParser;

/**
 * @author mivek
 * 
 */
public final class TAFFacade extends AbstractWeatherCodeFacade<TAF> {
	private static TAFFacade instance = new TAFFacade();

	private TAFFacade() {
		super(TAFParser.getInstance());
	}
	@Override
	public TAF decode(final String pCode) {
		return getParser().parse(pCode);
	}

	@Override
	public TAF retrieveFromAirport(final String pIcao) throws InvalidIcaoException, IOException, URISyntaxException {
		if (pIcao.length() != AbstractWeatherCodeFacade.ICAO) {
			throw new InvalidIcaoException(i18n.Messages.INVALID_ICAO); // $NON-NLS-1$
		}
		String website = "http://tgftp.nws.noaa.gov/data/forecasts/taf/stations/" + pIcao.toUpperCase() //$NON-NLS-1$
				+ ".TXT"; //$NON-NLS-1$
		URL url = new URL(website);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder sb = new StringBuilder();
		String inputLine;
		while ((inputLine = br.readLine()) != null) {
			sb.append(inputLine).append("\n");
		}
		br.close();
		return getParser().parse(sb.toString());
	}

	public static TAFFacade getInstance() {
		return instance;
	}
}
