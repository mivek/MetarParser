package io.github.mivek.service.provider;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Weather provider that retrieves data from NOAA servers.
 *
 * @see <a href="https://tgftp.nws.noaa.gov">NOAA Text File Server</a>
 * @author mivek
 */
public final class NOAAWeatherProvider extends AbstractWeatherProvider {

    /** Base URL for NOAA METAR data. */
    private static final String NOAA_METAR_URL = "https://tgftp.nws.noaa.gov/data/observations/metar/stations/";

    /** Base URL for NOAA TAF data. */
    private static final String NOAA_TAF_URL = "https://tgftp.nws.noaa.gov/data/forecasts/taf/stations/";

    /** File extension used by NOAA text files. */
    private static final String NOAA_FILE_EXTENSION = ".TXT";

    /** The TAF token used as a standalone first line in NOAA TAF responses. */
    private static final String TAF_TOKEN = "TAF";

    /** The AMD TAF token that appears as a second line in amended NOAA TAF responses. */
    private static final String AMD_TAF_TOKEN = "AMD TAF";

    @Override
    public String retrieveMetar(final String icao) throws ParseException, IOException, URISyntaxException, InterruptedException {
        checkIcao(icao);
        HttpResponse<Stream<String>> response = getHttpResponse(NOAA_METAR_URL + icao.toUpperCase() + NOAA_FILE_EXTENSION);
        return response.body().skip(1).collect(Collectors.joining());
    }

    @Override
    public String retrieveTaf(final String icao) throws ParseException, IOException, URISyntaxException, InterruptedException {
        checkIcao(icao);
        HttpResponse<Stream<String>> response = getHttpResponse(NOAA_TAF_URL + icao.toUpperCase() + NOAA_FILE_EXTENSION);
        StringBuilder sb = new StringBuilder();
        response.body().skip(1).forEach(line -> sb.append(line.replaceAll("\\s{2,}", "")).append("\n"));
        return format(sb.toString());
    }

    /**
     * Reformats the raw TAF code received from NOAA.
     * When the first line is only the token "TAF", it is merged with the subsequent content line.
     * When the second line is "AMD TAF", it is removed before merging.
     *
     * @param code the raw TAF string as received from NOAA.
     * @return the reformatted TAF string ready for parsing.
     * @throws ParseException when the code cannot be reformatted due to an invalid structure.
     */
    String format(final String code) throws ParseException {
        String[] lines = code.split("\n");
        if (!TAF_TOKEN.equals(lines[0].trim())) {
            return code;
        }
        if (AMD_TAF_TOKEN.equals(lines[1].trim())) {
            List<String> list = new ArrayList<>(Arrays.asList(lines));
            list.remove(1);
            lines = list.toArray(new String[0]);
        }
        return Arrays.stream(lines)
                .reduce((x, y) -> x + y + "\n")
                .orElseThrow(() -> new ParseException(ErrorCodes.ERROR_CODE_INVALID_MESSAGE));
    }
}
