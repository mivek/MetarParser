package io.github.mivek.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.Metar;
import io.github.mivek.parser.MetarParser;

/**
 * Class representing the service for metar.
 *
 * @author mivek
 */
public final class MetarService extends AbstractWeatherCodeService<Metar> {
    /** URL to retrieve the metar from. */
    private static final String NOAA_METAR_URL = "https://tgftp.nws.noaa.gov/data/observations/metar/stations/";
    /**
     * Instance.
     */
    private static final MetarService INSTANCE = new MetarService();

    /**
     * Private constructor.
     */
    private MetarService() {
        super(MetarParser.getInstance());
    }

    @Override
    public Metar decode(final String code) throws ParseException {
        return getParser().parse(code);
    }

    @Override
    public Metar retrieveFromAirport(final String icao) throws ParseException, IOException {
        if (icao.length() != AbstractWeatherCodeService.ICAO) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO); // $NON-NLS-1$
        }
        String website = NOAA_METAR_URL + icao.toUpperCase() // $NON-NLS-1$
                + ".TXT"; //$NON-NLS-1$
        URL url = new URL(website);
        URLConnection urlCo = url.openConnection();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(urlCo.getInputStream(), StandardCharsets.UTF_8))) {
            String line = br.lines().toArray(String[]::new)[1];
            return getParser().parse(line);
        }
    }

    /**
     * Returns a instance of the class.
     *
     * @return the instance of the class.
     */
    public static MetarService getInstance() {
        return INSTANCE;
    }
}
