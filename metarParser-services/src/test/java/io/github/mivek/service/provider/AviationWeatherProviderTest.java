package io.github.mivek.service.provider;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AviationWeatherProviderTest {

    private final AviationWeatherProvider sut = new AviationWeatherProvider();

    @Test
    void testRetrieveMetarInvalidIcao() {
        ParseException e = assertThrows(ParseException.class, () -> sut.retrieveMetar("RandomIcao"));
        assertEquals(ErrorCodes.ERROR_CODE_INVALID_ICAO, e.getErrorCode());
    }

    @Test
    void testRetrieveMetarNotFound() {
        ParseException e = assertThrows(ParseException.class, () -> sut.retrieveMetar("lftm"));
        assertEquals(ErrorCodes.ERROR_CODE_INVALID_ICAO, e.getErrorCode());
    }

    @Test
    void testRetrieveMetar() throws ParseException, IOException, URISyntaxException, InterruptedException {
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
    void testRetrieveTafNotFound() {
        ParseException e = assertThrows(ParseException.class, () -> sut.retrieveTaf("lftm"));
        assertEquals(ErrorCodes.ERROR_CODE_INVALID_ICAO, e.getErrorCode());
    }

    @Test
    void testRetrieveTaf() throws ParseException, IOException, URISyntaxException, InterruptedException {
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
