package com.mivek.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import com.mivek.exception.ErrorCodes;
import com.mivek.exception.ParseException;
import com.mivek.model.TAF;
import com.mivek.parser.TAFParser;

/**
 * Facade for TAF.
 * @author mivek
 */
public final class TAFFacade extends AbstractWeatherCodeFacade<TAF> {
    /**
     * The instance of the facade.
     */
    private static TAFFacade instance = new TAFFacade();

    /**
     * Constructor.
     */
    private TAFFacade() {
        super(TAFParser.getInstance());
    }

    @Override
    public TAF decode(final String pCode) throws ParseException {
        return getParser().parse(pCode);
    }

    @Override
    public TAF retrieveFromAirport(final String pIcao) throws IOException, URISyntaxException, ParseException {
        if (pIcao.length() != AbstractWeatherCodeFacade.ICAO) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO);
        }
        String website = "http://tgftp.nws.noaa.gov/data/forecasts/taf/stations/" + pIcao.toUpperCase() //$NON-NLS-1$
                + ".TXT"; //$NON-NLS-1$
        URL url = new URL(website);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            StringBuilder sb = new StringBuilder();
            String inputLine;
            br.readLine();
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine.replaceAll("\\s{2,}", "\n"));
            }
            return getParser().parse(sb.toString());
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * @return the instance of the facade.
     */
    public static TAFFacade getInstance() {
        return instance;
    }
}
