package io.github.mivek.service.provider;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

/**
 * Weather provider that retrieves data from the Aviation Weather Center API.
 * Returns the most recent available METAR and the current TAF for a given station.
 *
 * @see <a href="https://aviationweather.gov/data/api">Aviation Weather Center API</a>
 * @author mivek
 */
public final class AviationWeatherProvider extends AbstractWeatherProvider {

    /** Base URL for Aviation Weather METAR data. */
    private static final String AW_METAR_URL = "https://aviationweather.gov/api/data/metar?ids=";

    /** Query parameters appended to the Aviation Weather METAR URL. */
    private static final String AW_METAR_PARAMS = "&format=raw&hours=1";

    /** Base URL for Aviation Weather TAF data. */
    private static final String AW_TAF_URL = "https://aviationweather.gov/api/data/taf?ids=";

    /** Query parameters appended to the Aviation Weather TAF URL. */
    private static final String AW_TAF_PARAMS = "&format=raw";

    /** The "METAR " report-type prefix returned by the API. */
    private static final String METAR_PREFIX = "METAR ";

    /** The "SPECI " report-type prefix returned by the API. */
    private static final String SPECI_PREFIX = "SPECI ";

    @Override
    public String retrieveMetar(final String icao) throws ParseException, IOException, URISyntaxException, InterruptedException {
        checkIcao(icao);
        HttpResponse<Stream<String>> response = getHttpResponse(AW_METAR_URL + icao.toUpperCase() + AW_METAR_PARAMS);
        return response.body()
                .findFirst()
                .map(AviationWeatherProvider::stripReportTypePrefix)
                .orElseThrow(() -> new ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO));
    }

    @Override
    public String retrieveTaf(final String icao) throws ParseException, IOException, URISyntaxException, InterruptedException {
        checkIcao(icao);
        HttpResponse<Stream<String>> response = getHttpResponse(AW_TAF_URL + icao.toUpperCase() + AW_TAF_PARAMS);
        StringBuilder sb = new StringBuilder();
        response.body().forEach(line -> sb.append(line.replaceAll("\\s{2,}", "")).append("\n"));
        return sb.toString().trim();
    }

    /**
     * Strips the report-type prefix ("METAR " or "SPECI ") from a raw METAR line
     * if present, so that the resulting string matches the format expected by the parser.
     *
     * @param line the raw line as returned by the Aviation Weather API.
     * @return the line with the report-type prefix removed, or the original line if no prefix is present.
     */
    static String stripReportTypePrefix(final String line) {
        if (line.startsWith(METAR_PREFIX)) {
            return line.substring(METAR_PREFIX.length());
        }
        if (line.startsWith(SPECI_PREFIX)) {
            return line.substring(SPECI_PREFIX.length());
        }
        return line;
    }
}
