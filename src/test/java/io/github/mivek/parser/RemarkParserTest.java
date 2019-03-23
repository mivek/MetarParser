package io.github.mivek.parser;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import io.github.mivekinternationalization.Messages;

public class RemarkParserTest {
    RemarkParser fSut;

    @Before
    public void setUp() {
        fSut = RemarkParser.getInstance();
        Messages.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test
    public void testParseWithAO1() {
        // GIVEN a RMK with AO1 token.
        String code = "Token AO1 End of remark";
        // WHEN parsing the remark.
        String remark = fSut.parse(code);
        // THEN the token is parsed and translated
        assertNotNull(remark);
        assertThat(remark, containsString(Messages.getInstance().getString("Remark.AO1")));
    }

    @Test
    public void testParseWithAO2() {
        // GIVEN a RMK with AO2 token
        String code = "Token AO2 End of remark";
        // WHEN parsing the remark.
        String remark = fSut.parse(code);
        // THEN the token is parsed and translated
        assertNotNull(remark);
        assertThat(remark, containsString(Messages.getInstance().getString("Remark.AO2")));
    }

    @Test
    public void testParseWithWindPeakAtTheHour() {
        // GIVEN a RMK with Peak wind at the hour.
        String code = "AO1 PK WND 28045/15";
        // WHEN parsing the remark.
        String remark = fSut.parse(code);
        // THEN the token is parsed and translated
        assertNotNull(remark);
        assertThat(remark, containsString("peak wind of 45 knots from 280 degrees at :15"));
    }

    @Test
    public void testParseWithWindPeakAtAnotherHour() {
        // GIVEN a RMK with Peak wind at the hour.
        String code = "AO1 PK WND 28045/1515";
        // WHEN parsing the remark.
        String remark = fSut.parse(code);
        // THEN the token is parsed and translated
        assertNotNull(remark);
        assertThat(remark, containsString("peak wind of 45 knots from 280 degrees at 15:15"));
    }

    @Test
    public void testParseWindShiftAtTheHour() {
        // GIVEN a RMK with Wind shift at the hour
        String code = "AO1 WSHFT 30";
        // WHEN parsing the remark.
        String remark = fSut.parse(code);
        // THEN the remark contains the decoded wind shift
        assertNotNull(remark);
        assertThat(remark, containsString("wind shift at :30"));
    }

    @Test
    public void testParseWindShift() {
        // GIVEN a RMK with Wind shift at the hour
        String code = "AO1 WSHFT 1530";
        // WHEN parsing the remark.
        String remark = fSut.parse(code);
        // THEN the remark contains the decoded wind shift
        assertNotNull(remark);
        assertThat(remark, containsString("wind shift at 15:30"));
    }

    @Test
    public void testParseWindShiftWithFrontal() {
        // GIVEN a RMK with wind shift with frontal passage
        String code = "AO1 WSHFT 1530 FROPA";
        // WHEN parsing the remark
        String remark = fSut.parse(code);
        // THEN the remark contains the decoded wind shift fropa
        assertNotNull(remark);
        assertThat(remark, containsString("wind shift accompanied by frontal passage at 15:30"));
    }

    @Test
    public void testParseWindShiftWithFrontalAtTheHour() {
        // GIVEN a RMK with wind shift with frontal passage
        String code = "AO1 WSHFT 30 FROPA";
        // WHEN parsing the remark
        String remark = fSut.parse(code);
        // THEN the remark contains the decoded wind shift fropa
        assertNotNull(remark);
        assertThat(remark, containsString("wind shift accompanied by frontal passage at :30"));
    }

    @Test
    public void testParseTowerVisibility() {
        // GIVEN a rmk with tower visibility
        String code = "AO1 TWR VIS 16 1/2";
        // WHEN parsing the remark
        String remark = fSut.parse(code);
        // THEN the tower visibility is decoded
        assertThat(remark, containsString("tower visibility of 16 1/2 statute miles"));
    }

    @Test
    public void testParseSurfaceVisibility() {
        // GIVEN a rmk with surface visibility
        String code = "AO1 SFC VIS 16 1/2";
        // WHEN parsing the remark
        String remark = fSut.parse(code);
        // THEN the surface visibility is decoded
        assertThat(remark, containsString("surface visibility of 16 1/2 statute miles"));
    }

    @Test
    public void testParsePrevailingVisibility() {
        // GIVEN a rmk with variable prevailing visibility
        String code = "AO1 VIS 1/2V2";
        // WHEN parsing the remark
        String remark = fSut.parse(code);
        // THEN the variable prevailing visibility is decoded
        assertThat(remark, containsString("variable prevailing visibility between 1/2 and 2 SM"));
    }

    @Test
    public void testParseSectorVisibility() {
        // GIVEN a rmk with sector visibility
        String code = "AO1 VIS NE 2 1/2";
        // WHEN parsing the remark
        String remark = fSut.parse(code);
        // THEN the sector visibility is decoded
        assertThat(remark, containsString("visibility of 2 1/2 SM in the North East direction"));
    }

    @Test
    public void testParseSecondLocationVisibility() {
        // GIVEN a rmk with visibility at second location
        String code = "AO1 VIS 2 1/2 RWY11";
        // WHEN parsing the remark
        String remark = fSut.parse(code);
        // THEN the visibility at second location is decoded
        assertThat(remark, containsString("visibility of 2 1/2 SM mesured by a second sensor located at RWY11"));
    }

    @Test
    public void testParseTornadicActivityWithTornado() {
        String code = "A01 TORNADO B13 6 NE";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("tornado beginning at :13 6 SM North East of the station"));
    }

    @Test
    public void testParseTornadicActivityWithTornadoAndHour() {
        String code = "A01 TORNADO B1513 6 NE";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("tornado beginning at 15:13 6 SM North East of the station"));
    }

    @Test
    public void testParseTornadicActivityWithFunnelCloud() {
        String code = "AO1 FUNNEL CLOUD B1513E1630 6 NE";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("funnel cloud beginning at 15:13 ending at 16:30 6 SM North East of the station"));
    }

    @Test
    public void testParseTornadicActivityWithFunnelCloudAndHourEnd() {
        String code = "AO1 FUNNEL CLOUD B13E1630 6 NE";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("funnel cloud beginning at :13 ending at 16:30 6 SM North East of the station"));
    }

    @Test
    public void testParseTornadicActivityWithWaterSproutAndEndingTimeOnlyMinutes() {
        String code = "AO1 WATERSPOUT E16 12 NE";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("waterspout ending at :16 12 SM North East of the station"));
    }

    @Test
    public void testParseTornadicActivityWithWaterSproutAndEndingTime() {
        String code = "AO1 WATERSPOUT E1516 12 NE";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("waterspout ending at 15:16 12 SM North East of the station"));
    }

    @Test
    public void testParseBeginningEndPrecipitation() {
        String code = "AO1 RAB05E30SNB1520E1655";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("rain beginning at :05 ending at :30"));
        assertThat(remark, containsString("snow beginning at 15:20 ending at 16:55"));
    }

    @Test
    public void testParseBeginningEndPrecipitationWithDescriptive() {
        String code = "AO1 SHRAB05E30SHSNB20E55";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("showers of rain beginning at :05 ending at :30"));
        assertThat(remark, containsString("showers of snow beginning at :20 ending at :55"));
    }

    @Test
    public void testParseBeginningThunderstorm() {
        String code = "AO1 TSB0159E30";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("thunderstorm beginning at 01:59 ending at :30"));
    }

    @Test
    public void testParseThunderStormLocation() {
        String code = "AO1 TS SE";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("thunderstorm South East of the station"));
    }

    @Test
    public void testParseThunderStormLocationWithMoving() {
        String code = "AO1 TS SE MOV NE";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("thunderstorm South East of the station moving towards North East"));
    }

    @Test
    public void testParseHailSize() {
        String code = "AO1 GR 1 3/4";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("largest hailstones with a diameter of 1 3/4 inches"));
    }

    @Test
    public void testParseHailSizeWithLesserThan() {
        String code = "AO1 GR LESS THAN 1/4";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("largest hailstones with a diameter less than 1/4 inches"));
    }

    @Test
    public void testParseSnowPellets() {
        String code = "AO1 GS MOD";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("moderate snow pellets"));
    }

    @Test
    public void testParseVirgaWithDirection() {
        String code = "AO1 VIRGA SW";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("virga South West from the station"));
    }

    @Test
    public void testParseVirga() {
        String code = "AO1 VIRGA";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("virga"));
    }

    @Test
    public void testParseCeilingHeight() {
        String code = "AO1 CIG 005V010";
        String remark = fSut.parse(code);
        assertThat(remark, containsString("ceiling varying between 500 and 1,000 feet"));
    }
}
