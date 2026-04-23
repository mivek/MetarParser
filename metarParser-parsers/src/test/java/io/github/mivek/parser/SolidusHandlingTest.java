package io.github.mivek.parser;

import io.github.mivek.model.Metar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolidusHandlingTest {

    private final MetarParser parser = new MetarParser();

    @Test
    void testParseMetarWithSolidusTemperature() throws Exception {
        String code = "KJFK 121151Z 24016G28KT 10SM ////10 Q1012";
        Metar metar = parser.parse(code);
        assertNull(metar.getTemperature(), "Temperature should be null when missing");
        assertEquals(10, metar.getDewPoint().intValue(), "Dew point should be parsed");
    }

    @Test
    void testParseMetarWithSolidusDewPoint() throws Exception {
        String code = "KJFK 121151Z 24016G28KT 10SM 25//// Q1012";
        Metar metar = parser.parse(code);
        assertEquals(25, metar.getTemperature().intValue(), "Temperature should be parsed");
        assertNull(metar.getDewPoint(), "Dew point should be null when missing");
    }

    @Test
    void testParseMetarWithSolidusAltimeter() throws Exception {
        String code = "KJFK 121151Z 24016G28KT 10SM 25/10 Q////";
        Metar metar = parser.parse(code);
        assertEquals(25, metar.getTemperature().intValue(), "Temperature should be parsed");
        assertNull(metar.getAltimeter(), "Altimeter should be null when missing");
    }

    @Test
    void testParseMetarWithSolidusVisibility() throws Exception {
        String code = "KJFK 121151Z 24016G28KT //// 25/10 Q1012";
        Metar metar = parser.parse(code);
        assertNotNull(metar, "METAR should parse");
    }

    @Test
    void testParseMetarWithSolidusWind() throws Exception {
        String code = "KJFK 121151Z /////KT 10SM 25/10 Q1012";
        Metar metar = parser.parse(code);
        assertNotNull(metar, "METAR with solidus wind should parse");
    }

    @Test
    void testParseMetarWithP99Wind() throws Exception {
        String code = "KJFK 121151Z 280P99KT 10SM 25/10 Q1012";
        Metar metar = parser.parse(code);
        assertNotNull(metar.getWind(), "Wind should be parsed");
        assertEquals(100, metar.getWind().getSpeed(), "Wind speed P99 should be converted to 100");
    }

    @Test
    void testParseMetarWithP99WindAndGust() throws Exception {
        String code = "KJFK 121151Z 280P99G50KT 10SM 25/10 Q1012";
        Metar metar = parser.parse(code);
        assertNotNull(metar.getWind(), "Wind should be parsed");
        assertEquals(100, metar.getWind().getSpeed(), "Wind speed P99 should be 100");
        assertEquals(50, metar.getWind().getGust(), "Wind gust should be 50");
    }

    @Test
    void testParseMetarWithP99GustOnly() throws Exception {
        String code = "KJFK 121151Z 28050GP99KT 10SM 25/10 Q1012";
        Metar metar = parser.parse(code);
        assertNotNull(metar.getWind(), "Wind should be parsed");
        assertEquals(100, metar.getWind().getGust(), "Wind gust P99 should be converted to 100");
    }

    @Test
    void testParseMetarWithNegativeTemperature() throws Exception {
        String code = "KJFK 121151Z 24016G28KT 10SM M25/M30 Q1012";
        Metar metar = parser.parse(code);
        assertEquals(-25, metar.getTemperature().intValue(), "Temperature should be -25");
        assertEquals(-30, metar.getDewPoint().intValue(), "Dew point should be -30");
    }

    @Test
    void testParseMetarWithBothTemperaturesNull() throws Exception {
        String code = "KJFK 121151Z 24016G28KT 10SM /////// Q1012";
        Metar metar = parser.parse(code);
        assertNull(metar.getTemperature(), "Temperature should be null");
        assertNull(metar.getDewPoint(), "Dew point should be null");
    }

    @Test
    void testParseMetarWithAllSolidusValues() throws Exception {
        String code = "KJFK 121151Z 000////KT //// /////// Q////";
        Metar metar = parser.parse(code);
        assertNotNull(metar, "METAR with all solidus values should parse");
        assertNull(metar.getAltimeter(), "Altimeter should be null");
    }

    @Test
    void testParseMetarWithMixedNormalAndSolidus() throws Exception {
        String code = "KJFK 121151Z 24016G28KT 10SM 25//// Q1012";
        Metar metar = parser.parse(code);
        assertEquals(25, metar.getTemperature().intValue(), "Temperature should be 25");
        assertNull(metar.getDewPoint(), "Dew point should be null");
        assertEquals(1012, metar.getAltimeter().intValue(), "Altimeter should be 1012");
    }

    @Test
    void testParseMetarWithNegativeSolidusTemp() throws Exception {
        String code = "KJFK 121151Z 24016G28KT 10SM M05//// Q1012";
        Metar metar = parser.parse(code);
        assertEquals(-5, metar.getTemperature().intValue(), "Negative temperature should be parsed");
        assertNull(metar.getDewPoint(), "Dew point should be null");
    }
}
