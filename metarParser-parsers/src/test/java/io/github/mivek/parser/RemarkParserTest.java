package io.github.mivek.parser;

import io.github.mivek.internationalization.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RemarkParserTest {
    static final String CLOUD_QUANTITY_BKN = "CloudQuantity.BKN";
    static final String REMARK_PRECIPITATION_BEG_END = "Remark.Precipitation.Beg.End";
    static final String CONVERTER_NE = "Converter.NE";
    static final String REMARK_SEA_LEVEL_PRESSURE = "Remark.Sea.Level.Pressure";
    private RemarkParser parser;

    @BeforeEach
    void setUp() {
        parser = RemarkParser.getInstance();
    }

    @Test
    void testParseWithAO1() {
        // GIVEN a RMK with AO1 token.
        String code = "Token AO1 End of remark";
        // WHEN parsing the remark.
        String remark = parser.parse(code);
        // THEN the token is parsed and translated
        assertNotNull(remark);
        assertThat(remark, containsString(Messages.getInstance().getString("Remark.AO1")));
    }

    @Test
    void testParseWithAO2() {
        // GIVEN a RMK with AO2 token
        String code = "Token AO2 End of remark";
        // WHEN parsing the remark.
        String remark = parser.parse(code);
        // THEN the token is parsed and translated
        assertNotNull(remark);
        assertThat(remark, containsString(Messages.getInstance().getString("Remark.AO2")));
    }

    @Test
    void testParseWithWindPeakAtTheHour() {
        // GIVEN a RMK with Peak wind at the hour.
        String code = "AO1 PK WND 28045/15";
        // WHEN parsing the remark.
        String remark = parser.parse(code);
        // THEN the token is parsed and translated
        assertNotNull(remark);
        String rmk = Messages.getInstance().getString("Remark.PeakWind", "280", "45", "", "15");
        assertThat(remark, containsString(rmk));
    }

    @Test
    void testParseWithWindPeakAtAnotherHour() {
        // GIVEN a RMK with Peak wind at the hour.
        String code = "AO1 PK WND 28045/1515";
        // WHEN parsing the remark.
        String remark = parser.parse(code);
        // THEN the token is parsed and translated
        assertNotNull(remark);
        String rmk = Messages.getInstance().getString("Remark.PeakWind", "280", "45", "15", "15");
        assertThat(remark, containsString(rmk));
    }

    @Test
    void testParseWindShiftAtTheHour() {
        // GIVEN a RMK with Wind shift at the hour
        String code = "AO1 WSHFT 30";
        // WHEN parsing the remark.
        String remark = parser.parse(code);
        // THEN the remark contains the decoded wind shift
        assertNotNull(remark);
        String expectedRmk = Messages.getInstance().getString("Remark.WindShift", "", "30");
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParseWindShift() {
        // GIVEN a RMK with Wind shift at the hour
        String code = "AO1 WSHFT 1530";
        // WHEN parsing the remark.
        String remark = parser.parse(code);
        // THEN the remark contains the decoded wind shift
        assertNotNull(remark);
        String expectedRmk = Messages.getInstance().getString("Remark.WindShift", "15", "30");
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParseWindShiftWithFrontal() {
        // GIVEN a RMK with wind shift with frontal passage
        String code = "AO1 WSHFT 1530 FROPA";
        // WHEN parsing the remark
        String remark = parser.parse(code);
        // THEN the remark contains the decoded wind shift fropa
        assertNotNull(remark);
        String expectedRmk = Messages.getInstance().getString("Remark.WindShift.FROPA", "15", "30");
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParseWindShiftWithFrontalAtTheHour() {
        // GIVEN a RMK with wind shift with frontal passage
        String code = "AO1 WSHFT 30 FROPA";
        // WHEN parsing the remark
        String remark = parser.parse(code);
        // THEN the remark contains the decoded wind shift fropa
        assertNotNull(remark);
        String expectedRmk = Messages.getInstance().getString("Remark.WindShift.FROPA", "", "30");
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParseTowerVisibility() {
        // GIVEN a rmk with tower visibility
        String code = "AO1 TWR VIS 16 1/2";
        // WHEN parsing the remark
        String remark = parser.parse(code);
        // THEN the tower visibility is decoded
        String expectedRmk = Messages.getInstance().getString("Remark.Tower.Visibility", "16 1/2");
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParseSurfaceVisibility() {
        // GIVEN a rmk with surface visibility
        String code = "AO1 SFC VIS 16 1/2";
        // WHEN parsing the remark
        String remark = parser.parse(code);
        // THEN the surface visibility is decoded
        String expectedRmk = Messages.getInstance().getString("Remark.Surface.Visibility", "16 1/2");
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParsePrevailingVisibility() {
        // GIVEN a rmk with variable prevailing visibility
        String code = "AO1 VIS 1/2V2";
        // WHEN parsing the remark
        String remark = parser.parse(code);
        // THEN the variable prevailing visibility is decoded
        String expectedRmk = Messages.getInstance().getString("Remark.Variable.Prevailing.Visibility", "1/2", "2");
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParseSectorVisibility() {
        // GIVEN a rmk with sector visibility
        String code = "AO1 VIS NE 2 1/2";
        // WHEN parsing the remark
        String remark = parser.parse(code);
        // THEN the sector visibility is decoded
        String expectedRmk = Messages.getInstance().getString("Remark.Sector.Visibility", Messages.getInstance().getString(CONVERTER_NE), "2 1/2");
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParseSecondLocationVisibility() {
        // GIVEN a rmk with visibility at second location
        String code = "AO1 VIS 2 1/2 RWY11";
        // WHEN parsing the remark
        String remark = parser.parse(code);
        // THEN the visibility at second location is decoded
        String expectedRmk = Messages.getInstance().getString("Remark.Second.Location.Visibility", "2 1/2", "RWY11");
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParseTornadicActivityWithTornado() {
        String code = "AO1 TORNADO B13 6 NE";
        String remark = parser.parse(code);
        String expectedRmk = Messages.getInstance()
                .getString("Remark.Tornadic.Activity.Beginning", Messages.getInstance().getString("Remark.TORNADO"), "", "13", "6", Messages.getInstance().getString(CONVERTER_NE));
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParseTornadicActivityWithTornadoAndHour() {
        String code = "AO1 TORNADO B1513 6 NE";
        String remark = parser.parse(code);
        String expectedRmk = Messages.getInstance()
                .getString("Remark.Tornadic.Activity.Beginning", Messages.getInstance().getString("Remark.TORNADO"), "15", "13", "6", Messages.getInstance().getString(CONVERTER_NE));
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParseTornadicActivityWithFunnelCloud() {
        String code = "AO1 FUNNEL CLOUD B1513E1630 6 NE";
        String remark = parser.parse(code);
        String expectedRmk = Messages.getInstance()
                .getString("Remark.Tornadic.Activity.BegEnd", Messages.getInstance().getString("Remark.FUNNELCLOUD"), "15", "13", "16", "30", "6", Messages.getInstance().getString(CONVERTER_NE));
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParseTornadicActivityWithFunnelCloudAndHourEnd() {
        String code = "AO1 FUNNEL CLOUD B13E1630 6 NE";
        String remark = parser.parse(code);
        String expectedRmk = Messages.getInstance()
                .getString("Remark.Tornadic.Activity.BegEnd", Messages.getInstance().getString("Remark.FUNNELCLOUD"), "", "13", "16", "30", "6", Messages.getInstance().getString(CONVERTER_NE));
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParseTornadicActivityWithWaterSproutAndEndingTimeOnlyMinutes() {
        String code = "AO1 WATERSPOUT E16 12 NE";
        String remark = parser.parse(code);
        String expectedRmk = Messages.getInstance()
                .getString("Remark.Tornadic.Activity.Ending", Messages.getInstance().getString("Remark.WATERSPOUT"), "", "16", "12", Messages.getInstance().getString(CONVERTER_NE));
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParseTornadicActivityWithWaterSproutAndEndingTime() {
        String code = "AO1 WATERSPOUT E1516 12 NE";
        String remark = parser.parse(code);
        String expectedRmk = Messages.getInstance()
                .getString("Remark.Tornadic.Activity.Ending", Messages.getInstance().getString("Remark.WATERSPOUT"), "15", "16", "12", Messages.getInstance().getString(CONVERTER_NE));
        assertThat(remark, containsString(expectedRmk));
    }

    @Test
    void testParseBeginningEndPrecipitation() {
        String code = "AO1 RAB05E30SNB1520E1655";
        String remark = parser.parse(code);
        String expectedRmk1 = Messages.getInstance().getString(REMARK_PRECIPITATION_BEG_END, "", Messages.getInstance().getString("Phenomenon.RA"), "", "05", "", "30");
        String expectedRmk2 = Messages.getInstance().getString(REMARK_PRECIPITATION_BEG_END, "", Messages.getInstance().getString("Phenomenon.SN"), "15", "20", "16", "55");
        assertThat(remark, containsString(expectedRmk1));
        assertThat(remark, containsString(expectedRmk2));
    }

    @Test
    void testParseBeginningEndPrecipitationWithDescriptive() {
        String code = "AO1 SHRAB05E30SHSNB20E55";
        String remark = parser.parse(code);
        String expectedRmk1 = Messages.getInstance()
                .getString(REMARK_PRECIPITATION_BEG_END, Messages.getInstance().getString("Descriptive.SH"), Messages.getInstance().getString("Phenomenon.RA"), "", "05", "", "30");
        String expectedRmk2 = Messages.getInstance()
                .getString(REMARK_PRECIPITATION_BEG_END, Messages.getInstance().getString("Descriptive.SH"), Messages.getInstance().getString("Phenomenon.SN"), "", "20", "", "55");
        assertThat(remark, containsString(expectedRmk1));
        assertThat(remark, containsString(expectedRmk2));
    }

    @Test
    void testParseBeginningThunderstorm() {
        String code = "AO1 TSB0159E30";
        String remark = parser.parse(code);
        String expectedRmk1 = Messages.getInstance().getString(REMARK_PRECIPITATION_BEG_END, "", Messages.getInstance().getString("Phenomenon.TS"), "01", "59", "", "30");
        assertThat(remark, containsString(expectedRmk1));
    }

    @Test
    void testParseThunderStormLocation() {
        String code = "AO1 TS SE";
        String remark = parser.parse(code);
        String expected = Messages.getInstance().getString("Remark.Thunderstorm.Location", Messages.getInstance().getString("Converter.SE"));
        assertThat(remark, containsString(expected));
    }

    @Test
    void testParseThunderStormLocationWithMoving() {
        String code = "AO1 TS SE MOV NE";
        String remark = parser.parse(code);
        String expected = Messages.getInstance().getString("Remark.Thunderstorm.Location.Moving", Messages.getInstance().getString("Converter.SE"), Messages.getInstance().getString(CONVERTER_NE));
        assertThat(remark, containsString(expected));
    }

    @Test
    void testParseHailSize() {
        String code = "AO1 GR 1 3/4";
        String remark = parser.parse(code);
        String expected = Messages.getInstance().getString("Remark.Hail", "1 3/4");
        assertThat(remark, containsString(expected));
    }

    @Test
    void testParseHailSizeWithLesserThan() {
        String code = "AO1 GR LESS THAN 1/4";
        String remark = parser.parse(code);
        String expected = Messages.getInstance().getString("Remark.Hail.LesserThan", "1/4");
        assertThat(remark, containsString(expected));
    }

    @Test
    void testParseSnowPellets() {
        String code = "AO1 GS MOD";
        String remark = parser.parse(code);
        String expected = Messages.getInstance().getString("Remark.Snow.Pellets", Messages.getInstance().getString("Remark.MOD"));
        assertThat(remark, containsString(expected));
    }

    @Test
    void testParseVirgaWithDirection() {
        String code = "AO1 VIRGA SW";
        String remark = parser.parse(code);
        String expected = Messages.getInstance().getString("Remark.Virga.Direction", Messages.getInstance().getString("Converter.SW"));
        assertThat(remark, containsString(expected));
    }

    @Test
    void testParseVirga() {
        String code = "AO1 VIRGA";
        String remark = parser.parse(code);
        assertThat(remark, containsString(Messages.getInstance().getString("Remark.VIRGA")));
    }

    @Test
    void testParseCeilingHeight() {
        String code = "AO1 CIG 005V010";
        String remark = parser.parse(code);
        String expected = Messages.getInstance().getString("Remark.Ceiling.Height", 500, 1000);
        assertThat(remark, containsString(expected));
    }

    @Test
    void testParseObscurations() {
        String code = "AO1 FU BKN020";
        String remark = parser.parse(code);
        String expected = Messages.getInstance().getString("Remark.Obscuration", Messages.getInstance().getString(CLOUD_QUANTITY_BKN), 2000, Messages.getInstance().getString("Phenomenon.FU"));
        assertThat(remark, containsString(expected));
    }

    @Test
    void testParseVariableSkyConditionWithoutLayer() {
        String code = "BKN V OVC";
        String remark = parser.parse(code);
        String expected = Messages.getInstance()
                .getString("Remark.Variable.Sky.Condition", Messages.getInstance().getString(CLOUD_QUANTITY_BKN), Messages.getInstance().getString("CloudQuantity.OVC"));
        assertThat(remark, containsString(expected));
    }

    @Test
    void testParseVariableSkyCondition() {
        String code = "BKN014 V OVC";
        String remark = parser.parse(code);
        String expected = Messages.getInstance()
                .getString("Remark.Variable.Sky.Condition.Height", 1400, Messages.getInstance().getString(CLOUD_QUANTITY_BKN), Messages.getInstance().getString("CloudQuantity.OVC"));
        assertThat(remark, containsString(expected));
    }

    @Test
    void testParseCeilingSecondLocation() {
        String code = "CIG 002 RWY11";
        String remark = parser.parse(code);
        String expected = Messages.getInstance().getString("Remark.Ceiling.Second.Location", 200, "RWY11");
        assertThat(remark, containsString(expected));
    }

    @Test
    void testParseSealLevelPressure() {
        String code = "AO1 SLP134";
        String remark = parser.parse(code);
        String expected = Messages.getInstance().getString(REMARK_SEA_LEVEL_PRESSURE, "1013.4");
        assertThat(remark, containsString(expected));
    }

    @Test
    void testParseSealLevelPressure2() {
        String code = "AO1 SLP982";
        String remark = parser.parse(code);
        String expected = Messages.getInstance().getString(REMARK_SEA_LEVEL_PRESSURE, "998.2");
        assertThat(remark, containsString(expected));
    }

    @Test
    void testParseSnowIncreasingRapidly() {
        String code = "AO1 SNINCR 2/10";
        String remark = parser.parse(code);
        String expected = Messages.getInstance().getString("Remark.Snow.Increasing.Rapidly", 2, 10);
        assertThat(remark, containsString(expected));
    }

    @Test
    void testParseWithRmkSlp() {
        String code = "CF1AC8 CF TR SLP091 DENSITY ALT 200FT";

        String remark = parser.parse(code);
        assertThat(remark, containsString("CF1AC8 CF TR"));
        assertThat(remark, containsString(Messages.getInstance().getString(REMARK_SEA_LEVEL_PRESSURE, "1009.1")));
    }

    @Test
    void testParseWithDistantLightning() {
        String code = "AO2 PK WND 13029/2000 LTG DSNT ALQDS P0020";
        String remark = parser.parse(code);

        assertThat(remark, containsString(Messages.getInstance().getString("Remark.LTG")));
        assertThat(remark, containsString(Messages.getInstance().getString("Remark.DSNT")));
        assertThat(remark, containsString(Messages.getInstance().getString("Remark.ALQDS")));
    }
}
