package io.github.mivek.service;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.service.provider.WeatherProvider;

/**
 * Test-only {@link WeatherProvider} implementation that returns hardcoded weather strings.
 * Used to avoid real HTTP calls in service-layer tests.
 */
final class FakeWeatherProvider implements WeatherProvider {

    /** Hardcoded METAR string for LFPG. */
    static final String LFPG_METAR = "LFPG 251830Z 17013KT 9999 OVC006 04/03 Q1012 NOSIG";

    /** Hardcoded TAF string for LFPG. */
    static final String LFPG_TAF = "TAF LFPG 121700Z 1218/1324 13003KT CAVOK TX09/1315Z TN00/1306Z\nTEMPO 1303/1308 4000 BR";

    @Override
    public String retrieveMetar(final String icao) throws ParseException {
        if (icao.length() != 4) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO);
        }
        if (!"LFPG".equalsIgnoreCase(icao)) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO);
        }
        return LFPG_METAR;
    }

    @Override
    public String retrieveTaf(final String icao) throws ParseException {
        if (icao.length() != 4) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO);
        }
        if (!"LFPG".equalsIgnoreCase(icao)) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO);
        }
        return LFPG_TAF;
    }
}
