package com.mivek.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.mivek.exception.ErrorCodes;
import com.mivek.exception.ParseException;
import com.mivek.model.Metar;
import com.mivek.parser.MetarParser;

/**
 * Class representing the facade for metars.
 * @author mivek
 */
public final class MetarFacade extends AbstractWeatherCodeFacade<Metar> {
    /**
     * Instance.
     */
    private static final MetarFacade INSTANCE = new MetarFacade();

    /**
     * Private constructor.
     */
    private MetarFacade() {
        super(MetarParser.getInstance());
    }

    @Override
    public Metar decode(final String pCode) throws ParseException {
        return getParser().parse(pCode);
    }

    @Override
    public Metar retrieveFromAirport(final String pIcao) throws IOException, ParseException {
        if (pIcao.length() != AbstractWeatherCodeFacade.ICAO) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO); // $NON-NLS-1$
        }
        String website = "http://tgftp.nws.noaa.gov/data/observations/metar/stations/" + pIcao.toUpperCase() //$NON-NLS-1$
                + ".TXT"; //$NON-NLS-1$
        URL url = new URL(website);
        URLConnection urlCo = url.openConnection();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(urlCo.getInputStream()))) {
            String line = (String) br.lines().toArray()[1];
            br.close();
            return getParser().parse(line);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Returns a instance of the class.
     * @return the instance of the class.
     */
    public static MetarFacade getInstance() {
        return INSTANCE;
    }
}
