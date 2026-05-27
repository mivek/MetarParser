package io.github.mivek.service.provider;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NOAAWeatherProviderTest {

    private HttpClient mockHttpClient;
    private HttpResponse<Stream<String>> mockResponse = mock(HttpResponse.class);
    private NOAAWeatherProvider sut;

    @BeforeEach
    void setUp() {
        mockHttpClient = mock(HttpClient.class);
        mockResponse = mock(HttpResponse.class);
        sut = new NOAAWeatherProvider(mockHttpClient);
    }

    @Test
    void testConstructors() {
        assertNotNull(new NOAAWeatherProvider());
        assertNotNull(new NOAAWeatherProvider("Custom-Agent"));
    }

    @Test
    void testRetrieveMetarInvalidIcao() {
        ParseException e = assertThrows(ParseException.class, () -> sut.retrieveMetar("RandomIcao"));
        assertEquals(ErrorCodes.ERROR_CODE_INVALID_ICAO, e.getErrorCode());
    }

    @Test
    void testRetrieveMetarNotFound() throws IOException, InterruptedException {
        when(mockResponse.statusCode()).thenReturn(404);
        doReturn(mockResponse).when(mockHttpClient).send(any(HttpRequest.class), any());

        ParseException e = assertThrows(ParseException.class, () -> sut.retrieveMetar("lftm"));
        assertEquals(ErrorCodes.ERROR_CODE_INVALID_ICAO, e.getErrorCode());
    }

    @Test
    void testRetrieveMetar() throws ParseException, IOException, URISyntaxException, InterruptedException {
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(Stream.of(
                "2024/01/01 18:30",
                "LFPG 251830Z 17013KT 9999 OVC006 04/03 Q1012 NOSIG"));
        doReturn(mockResponse).when(mockHttpClient).send(any(HttpRequest.class), any());

        String result = sut.retrieveMetar("LFPG");

        assertNotNull(result);
        assertTrue(result.contains("LFPG"));
    }

    @Test
    void testRetrieveTafInvalidIcao() {
        ParseException e = assertThrows(ParseException.class, () -> sut.retrieveTaf("RandomIcao"));
        assertEquals(ErrorCodes.ERROR_CODE_INVALID_ICAO, e.getErrorCode());
    }

    @Test
    void testRetrieveTafNotFound() throws IOException, InterruptedException {
        when(mockResponse.statusCode()).thenReturn(404);
        doReturn(mockResponse).when(mockHttpClient).send(any(HttpRequest.class), any());

        ParseException e = assertThrows(ParseException.class, () -> sut.retrieveTaf("lftm"));
        assertEquals(ErrorCodes.ERROR_CODE_INVALID_ICAO, e.getErrorCode());
    }

    @Test
    void testRetrieveTaf() throws ParseException, IOException, URISyntaxException, InterruptedException {
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(Stream.of(
                "2024/01/01 18:30",
                "TAF LFPG 121700Z 1218/1324 13003KT CAVOK TX09/1315Z TN00/1306Z",
                "TEMPO 1303/1308 4000 BR"));
        doReturn(mockResponse).when(mockHttpClient).send(any(HttpRequest.class), any());

        String result = sut.retrieveTaf("LFPG");

        assertNotNull(result);
        assertTrue(result.contains("LFPG"));
    }

    @Test
    void testFormatWithAMD() throws ParseException {
        String message =
                """
                        TAF\s
                        AMD LFPG 100910Z 1009/1112 20015G25KT 9999 BKN035\s
                        TEMPO 1011/1019 26020G35KT 4000 SHRA BKN012TCU PROB30\s
                        TEMPO 1015/1019 27025G45KT 2500 TSRAGS SCT012CB\s
                        BECMG 1021/1024 27010KT PROB30\s
                        TEMPO 1105/1107 BKN014\s
                        BECMG 1109/1111 34010KT""";

        String formattedString =
                """
                        TAF AMD LFPG 100910Z 1009/1112 20015G25KT 9999 BKN035\s
                        TEMPO 1011/1019 26020G35KT 4000 SHRA BKN012TCU PROB30\s
                        TEMPO 1015/1019 27025G45KT 2500 TSRAGS SCT012CB\s
                        BECMG 1021/1024 27010KT PROB30\s
                        TEMPO 1105/1107 BKN014\s
                        BECMG 1109/1111 34010KT
                        """;

        String result = sut.format(message);

        assertNotNull(result);
        assertEquals(formattedString, result);
    }

    @Test
    void testFormatWithoutReformat() throws ParseException {
        String message = "TAF LFPG 121700Z 1218/1324 13003KT CAVOK TX09/1315Z TN00/1306Z \n" + "TEMPO 1303/1308 4000 BR";

        String result = sut.format(message);

        assertEquals(message, result);
    }

    @Test
    void testFormat() throws ParseException {
        String tafMessage = """
                TAF\s
                AMD TAF\s
                AMD LFPG 241332Z 2413/2518 01008KT 7000 BKN015 TX13/2414Z TN03/2505Z\s
                BECMG 2413/2415 BKN040\s
                BECMG 2415/2417 CAVOK\s
                BECMG 2509/2511 BKN030\s
                TEMPO 2514/2516 36015G25KT\s
                BECMG 2516/2518 CAVOK""";

        String formatted = """
                TAF AMD LFPG 241332Z 2413/2518 01008KT 7000 BKN015 TX13/2414Z TN03/2505Z\s
                BECMG 2413/2415 BKN040\s
                BECMG 2415/2417 CAVOK\s
                BECMG 2509/2511 BKN030\s
                TEMPO 2514/2516 36015G25KT\s
                BECMG 2516/2518 CAVOK
                """;

        String result = sut.format(tafMessage);

        assertNotNull(result);
        assertEquals(formatted, result);
    }

    @Test
    void testDefaultUserAgent() {
        assertEquals("MetarParser", AbstractWeatherProvider.DEFAULT_USER_AGENT);
    }
}
