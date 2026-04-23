package io.github.mivek.parser;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.model.Cloud;
import io.github.mivek.model.Metar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NCDCloudTest {

    private final MetarParser parser = new MetarParser();

    @Test
    void testParseMetarWithNCD() throws Exception {
        String code = "KJFK 121151Z 24016G28KT 10SM NCD 25/10 Q1012";
        Metar metar = parser.parse(code);
        assertNotNull(metar, "METAR should parse");
        assertFalse(metar.getClouds().isEmpty(), "Should have cloud information");
        Cloud ncdCloud = metar.getClouds().get(0);
        assertEquals(CloudQuantity.NCD, ncdCloud.getQuantity(), "Cloud quantity should be NCD");
    }

    @Test
    void testParseMetarWithMixedCloudIncludingNCD() throws Exception {
        String code = "KJFK 121151Z 24016G28KT 10SM FEW050 SCT150 NCD OVC250 25/10 Q1012";
        Metar metar = parser.parse(code);
        assertNotNull(metar, "METAR should parse");
        assertTrue(metar.getClouds().stream().anyMatch(c -> c.getQuantity() == CloudQuantity.NCD), "Should have NCD cloud");
    }

    @Test
    void testParseMetarWithMultipleNCDReferences() throws Exception {
        String code = "KJFK 121151Z 24016G28KT 10SM NCD NCD 25/10 Q1012";
        Metar metar = parser.parse(code);
        assertNotNull(metar, "METAR should parse");
        assertTrue(metar.getClouds().stream().filter(c -> c.getQuantity() == CloudQuantity.NCD).count() >= 1, "Should have at least one NCD cloud");
    }

    @Test
    void testParseMetarWithCavokAndNCD() throws Exception {
        String code = "KJFK 121151Z 24016G28KT CAVOK 25/10 Q1012";
        Metar metar = parser.parse(code);
        assertNotNull(metar, "METAR with CAVOK should parse");
        assertTrue(metar.isCavok(), "CAVOK should be set");
    }
}
