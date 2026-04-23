package io.github.mivek.parser;

import io.github.mivek.enums.ReportType;
import io.github.mivek.model.Metar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportTypeTest {

    private final MetarParser parser = new MetarParser();

    @Test
    void testParseMetarWithReportType() throws Exception {
        String code = "METAR KJFK 121151Z 24016G28KT 10SM FEW250 25/10 Q1012";
        Metar metar = parser.parse(code);
        assertEquals(ReportType.METAR, metar.getReportType(), "Should have METAR report type");
        assertEquals("KJFK", metar.getStation(), "Station should be parsed after METAR");
    }

    @Test
    void testParseSpeciWithReportType() throws Exception {
        String code = "SPECI KJFK 121151Z 24016G28KT 2SM TSRA 25/10 Q1012";
        Metar metar = parser.parse(code);
        assertEquals(ReportType.SPECI, metar.getReportType(), "Should have SPECI report type");
        assertEquals("KJFK", metar.getStation(), "Station should be parsed after SPECI");
    }

    @Test
    void testParseMetarWithoutReportType() throws Exception {
        String code = "KJFK 121151Z 24016G28KT 10SM FEW250 25/10 Q1012";
        Metar metar = parser.parse(code);
        assertNull(metar.getReportType(), "Should have no report type when not specified");
    }
}
