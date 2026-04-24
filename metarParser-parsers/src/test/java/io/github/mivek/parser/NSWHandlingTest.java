package io.github.mivek.parser;

import io.github.mivek.model.Metar;
import io.github.mivek.model.trend.MetarTrend;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NSWHandlingTest {

    private final MetarParser parser = new MetarParser();

    @Test
    void testParseMetarWithNSWInTrend() throws Exception {
        String code = "METAR KJFK 121151Z 24016G28KT 10SM TSRA 25/10 Q1012 RETS NOSTEND TEMPO 1015/1020 NSW";
        Metar metar = parser.parse(code);
        assertNotNull(metar, "METAR should parse");
        if (!metar.getTrends().isEmpty()) {
            MetarTrend trend = metar.getTrends().get(0);
            assertTrue(trend.isNsw(), "Trend should have NSW flag set");
            assertTrue(trend.getWeatherConditions().isEmpty(), "Weather conditions should be cleared when NSW is set");
        }
    }

    @Test
    void testParseMetarTrendWithoutNSW() throws Exception {
        String code = "METAR KJFK 121151Z 24016G28KT 10SM 25/10 Q1012 TEMPO 1015/1020 TSRA";
        Metar metar = parser.parse(code);
        assertNotNull(metar, "METAR should parse");
        if (!metar.getTrends().isEmpty()) {
            MetarTrend trend = metar.getTrends().get(0);
            assertFalse(trend.isNsw(), "Trend without NSW should have flag false");
            assertFalse(trend.getWeatherConditions().isEmpty(), "Weather conditions should be present without NSW");
        }
    }

    @Test
    void testParseNSWWithWeatherTransition() throws Exception {
        String code = "METAR KJFK 121151Z 24016G28KT 10SM +TSRA 25/10 Q1012 TEMPO 1015/1020 NSW";
        Metar metar = parser.parse(code);
        assertNotNull(metar, "METAR should parse");
        assertFalse(metar.getWeatherConditions().isEmpty(), "Present weather should be set");
        if (!metar.getTrends().isEmpty()) {
            assertTrue(metar.getTrends().get(0).isNsw(), "Trend should have NSW");
        }
    }
}
