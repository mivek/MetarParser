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

class AviationWeatherProviderTest {

    private HttpClient mockHttpClient;
    private HttpResponse<Stream<String>> mockResponse = mock(HttpResponse.class);
    private AviationWeatherProvider sut;

    @BeforeEach
    void setUp() {
        mockHttpClient = mock(HttpClient.class);
        mockResponse = mock(HttpResponse.class);
        sut = new AviationWeatherProvider(mockHttpClient);
    }

    @Test
    void testConstructors() {
        assertNotNull(new AviationWeatherProvider());
        assertNotNull(new AviationWeatherProvider("Custom-Agent"));
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
    void testRetrieveMetarEmptyBody() throws IOException, InterruptedException {
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(Stream.empty());
        doReturn(mockResponse).when(mockHttpClient).send(any(HttpRequest.class), any());

        ParseException e = assertThrows(ParseException.class, () -> sut.retrieveMetar("LFPG"));
        assertEquals(ErrorCodes.ERROR_CODE_INVALID_ICAO, e.getErrorCode());
    }

    @Test
    void testRetrieveMetar() throws ParseException, IOException, URISyntaxException, InterruptedException {
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(Stream.of("LFPG 251830Z 17013KT 9999 OVC006 04/03 Q1012 NOSIG"));
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
                "TAF LFPG 121700Z 1218/1324 13003KT CAVOK TX09/1315Z TN00/1306Z",
                "TEMPO 1303/1308 4000 BR"));
        doReturn(mockResponse).when(mockHttpClient).send(any(HttpRequest.class), any());

        String result = sut.retrieveTaf("LFPG");

        assertNotNull(result);
        assertTrue(result.contains("LFPG"));
    }

    @Test
    void testStripReportTypePrefixMetar() {
        String result = AviationWeatherProvider.stripReportTypePrefix("METAR LFPG 231730Z 07010KT CAVOK 21/00 Q1024 NOSIG");
        assertEquals("LFPG 231730Z 07010KT CAVOK 21/00 Q1024 NOSIG", result);
    }

    @Test
    void testStripReportTypePrefixSpeci() {
        String result = AviationWeatherProvider.stripReportTypePrefix("SPECI LFPG 231730Z 07010KT CAVOK 21/00 Q1024 NOSIG");
        assertEquals("LFPG 231730Z 07010KT CAVOK 21/00 Q1024 NOSIG", result);
    }

    @Test
    void testStripReportTypePrefixNoPrefix() {
        String raw = "LFPG 231730Z 07010KT CAVOK 21/00 Q1024 NOSIG";
        String result = AviationWeatherProvider.stripReportTypePrefix(raw);
        assertEquals(raw, result);
    }
}
