package io.github.mivek.parser;

import io.github.mivek.enums.*;
import io.github.mivek.exception.ParseException;
import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.*;
import io.github.mivek.model.trend.MetarTrend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link MetarParser}
 *
 * @author mivek
 */
class MetarParserTest extends AbstractWeatherCodeParserTest<Metar> {

    static final String TEN_KM = ">10km";
    private MetarParser parser;

    @Override
    protected MetarParser getParser() {
        return parser;
    }

    @BeforeEach
    void setUp() {
        parser = new MetarParser();
    }

    /**
     * =========================== Test ParseMetarAction ===========================
     */

    @Test
    void testParse() throws ParseException {
        String metarString = "LFPG 170830Z 00000KT 0350 R27L/0375N R09R/0175N R26R/0500D R08L/0400N R26L/0275D R08R/0250N R27R/0300N R09L/0200N FG SCT000 M01/M01 Q1026 NOSIG";

        Metar m = parser.parse(metarString);

        assertNotNull(m);

        assertEquals(parser.getAirportSupplier().get("LFPG"), m.getAirport());
        assertEquals(Integer.valueOf(17), m.getDay());
        assertEquals(8, m.getTime().getHour());
        assertEquals(30, m.getTime().getMinute());
        assertNotNull(m.getWind());
        assertEquals(0, m.getWind().getSpeed());
        assertEquals(Messages.getInstance().getString("Converter.N"), m.getWind().getDirection());
        assertEquals("KT", m.getWind().getUnit());
        assertNotNull(m.getVisibility());
        assertEquals(metarString, m.getMessage());
        assertEquals("350m", m.getVisibility().getMainVisibility());
        assertThat(m.getRunways(), is(not(empty())));
        assertThat(m.getRunways(), hasSize(8));
        // Check if runways are correctly parsed
        assertEquals("27L", m.getRunways().get(0).getName());
        assertEquals(375, m.getRunways().get(0).getMinRange());
        assertEquals(RunwayInfoTrend.NO_SIGNIFICANT_CHANGE, m.getRunways().get(0).getTrend());
    }

    @Test
    void testParseNullAirport() throws ParseException {
        String metarString = "AAAA 170830Z 00000KT 0350 R27L/0375N R09R/0175N R26R/0500D R08L/0400N R26L/0275D R08R/0250N R27R/0300N R09L/0200N FG SCT000 M01/M01 Q1026 NOSIG";
        Metar m = parser.parse(metarString);
        assertEquals("AAAA", m.getStation());
        assertNull(m.getAirport());
    }

    @Test
    void testParseWithTempo() throws ParseException {
        String metarString = "LFBG 081130Z AUTO 23012KT 9999 SCT022 BKN072 BKN090 22/16 Q1011 TEMPO 26015G25KT 3000 TSRA SCT025CB BKN050";

        Metar m = parser.parse(metarString);
        assertNotNull(m);
        assertTrue(m.isAuto());
        assertThat(m.getClouds(), hasSize(3));
        assertThat(m.getTrends(), hasSize(1));
        MetarTrend trend = m.getTrends().get(0);
        assertThat(trend.getType(), is(WeatherChangeType.TEMPO));
        assertNotNull(trend.getWind());
        assertEquals(Integer.valueOf(260), trend.getWind().getDirectionDegrees());
        assertEquals(15, trend.getWind().getSpeed());
        assertEquals(25, trend.getWind().getGust());
        assertThat(trend.getTimes(), hasSize(0));
        assertNotNull(trend.getVisibility());
        assertEquals("3000m", trend.getVisibility().getMainVisibility());
        assertThat(trend.getWeatherConditions(), hasSize(1));
        WeatherCondition wc = trend.getWeatherConditions().get(0);
        assertEquals(Descriptive.THUNDERSTORM, wc.getDescriptive());
        assertThat(wc.getPhenomenons(), hasSize(1));
        assertEquals(Phenomenon.RAIN, wc.getPhenomenons().get(0));
        assertThat(trend.getClouds(), hasSize(2));
        Cloud c1 = trend.getClouds().get(0);
        assertEquals(CloudQuantity.SCT, c1.getQuantity());
        assertEquals(2500, c1.getHeight());
        assertEquals(CloudType.CB, c1.getType());
        Cloud c2 = trend.getClouds().get(1);
        assertEquals(CloudQuantity.BKN, c2.getQuantity());
        assertEquals(5000, c2.getHeight());
    }

    @Test
    void testParseWithTempoAndBecmg() throws ParseException {
        String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO SHRA BECMG SKC";

        Metar m = parser.parse(metarString);

        assertNotNull(m);
        assertThat(m.getTrends(), hasSize(2));
        assertThat(m.getTrends().get(0).getType(), is(WeatherChangeType.TEMPO));
        assertThat(m.getTrends().get(0).getWeatherConditions(), hasSize(1));
        WeatherCondition wc = m.getTrends().get(0).getWeatherConditions().get(0);
        assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
        assertThat(wc.getPhenomenons(), hasSize(1));
        assertThat(m.getTrends().get(1).getType(), is(WeatherChangeType.BECMG));
        assertThat(m.getTrends().get(1).getClouds(), hasSize(1));
    }

    @Test
    void testParseWithTempoAndAT() throws ParseException {
        String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO AT0800 SHRA ";

        Metar m = parser.parse(metarString);

        assertNotNull(m);
        assertThat(m.getTrends(), hasSize(1));
        assertThat(m.getTrends().get(0).getType(), is(WeatherChangeType.TEMPO));
        assertThat(m.getTrends().get(0).getWeatherConditions(), hasSize(1));
        MetarTrend trend = m.getTrends().get(0);
        WeatherCondition wc = trend.getWeatherConditions().get(0);
        assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
        assertThat(wc.getPhenomenons(), hasSize(1));
        assertThat(trend.getTimes(), hasSize(1));
        assertEquals(TimeIndicator.AT, trend.getTimes().get(0).getType());
        assertEquals(8, trend.getTimes().get(0).getTime().getHour());
        assertEquals(0, trend.getTimes().get(0).getTime().getMinute());
    }

    @Test
    void testParseWithTempoAndTL() throws ParseException {
        String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO TL1830 SHRA ";

        Metar m = parser.parse(metarString);

        assertNotNull(m);
        assertThat(m.getTrends(), hasSize(1));
        assertThat(m.getTrends().get(0).getType(), is(WeatherChangeType.TEMPO));
        assertThat(m.getTrends().get(0).getWeatherConditions(), hasSize(1));
        MetarTrend trend = m.getTrends().get(0);
        WeatherCondition wc = trend.getWeatherConditions().get(0);
        assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
        assertThat(wc.getPhenomenons(), hasSize(1));
        assertThat(trend.getTimes(), hasSize(1));
        assertEquals(TimeIndicator.TL, trend.getTimes().get(0).getType());
        assertEquals(18, trend.getTimes().get(0).getTime().getHour());
        assertEquals(30, trend.getTimes().get(0).getTime().getMinute());
    }

    @Test
    void testParseWithTempoAndFM() throws ParseException {
        String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO FM1830 SHRA ";

        Metar m = parser.parse(metarString);

        assertNotNull(m);
        assertThat(m.getTrends(), hasSize(1));
        assertThat(m.getTrends().get(0).getType(), is(WeatherChangeType.TEMPO));
        assertThat(m.getTrends().get(0).getWeatherConditions(), hasSize(1));
        MetarTrend trend = m.getTrends().get(0);
        WeatherCondition wc = trend.getWeatherConditions().get(0);
        assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
        assertThat(wc.getPhenomenons(), hasSize(1));
        assertThat(trend.getTimes(), hasSize(1));
        assertEquals(TimeIndicator.FM, trend.getTimes().get(0).getType());
        assertEquals(18, trend.getTimes().get(0).getTime().getHour());
        assertEquals(30, trend.getTimes().get(0).getTime().getMinute());
    }

    @Test
    void testParseWithTempoAndFMAndTL() throws ParseException {
        String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO FM1700 TL1830 SHRA ";

        Metar m = parser.parse(metarString);

        assertNotNull(m);
        assertThat(m.getTrends(), hasSize(1));
        assertThat(m.getTrends().get(0).getType(), is(WeatherChangeType.TEMPO));
        assertThat(m.getTrends().get(0).getWeatherConditions(), hasSize(1));
        MetarTrend trend = m.getTrends().get(0);
        WeatherCondition wc = trend.getWeatherConditions().get(0);
        assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
        assertEquals(Phenomenon.RAIN, wc.getPhenomenons().get(0));
        assertThat(wc.getPhenomenons(), hasSize(1));
        assertThat(trend.getTimes(), hasSize(2));
        assertEquals(TimeIndicator.FM, trend.getTimes().get(0).getType());
        assertEquals(17, trend.getTimes().get(0).getTime().getHour());
        assertEquals(0, trend.getTimes().get(0).getTime().getMinute());
        assertEquals(TimeIndicator.TL, trend.getTimes().get(1).getType());
        assertEquals(18, trend.getTimes().get(1).getTime().getHour());
        assertEquals(30, trend.getTimes().get(1).getTime().getMinute());
        String toString = m.toString();
        assertThat(toString, containsString(Messages.getInstance().getString("WeatherChangeType.FM") + " 17:00"));
        assertThat(toString, containsString(Messages.getInstance().getString("TimeIndicator.TL") + " 18:30"));
        assertThat(toString, containsString(Descriptive.SHOWERS.toString()));
        assertThat(toString, containsString(Phenomenon.RAIN.toString()));
        assertNotNull(m.getVisibility());
        assertEquals(TEN_KM, m.getVisibility().getMainVisibility());
    }

    @Test
    void testParseWithMinVisibility() throws ParseException {
        String code = "LFPG 161430Z 24015G25KT 5000 1100w";

        Metar m = parser.parse(code);

        assertNotNull(m);
        assertEquals(16, m.getDay());
        assertEquals(14, m.getTime().getHour());
        assertEquals(30, m.getTime().getMinute());
        assertNotNull(m.getWind());
        Wind w = m.getWind();
        assertEquals(240, w.getDirectionDegrees());
        assertEquals(15, w.getSpeed());
        assertEquals(25, w.getGust());
        assertNotNull(m.getVisibility());
        Visibility v = m.getVisibility();
        assertEquals("5000m", v.getMainVisibility());
        assertEquals(1100, v.getMinVisibility());
        assertEquals("W", v.getMinDirection());
        String des = m.toString();

        assertThat(des, containsString(Messages.getInstance().getString("ToString.day.month") + "=16"));
        assertThat(des, containsString(Messages.getInstance().getString("ToString.report.time") + "=14:30"));
        assertThat(des, containsString(Messages.getInstance().getString("ToString.wind.direction.degrees") + "=240"));
    }

    @Test
    void testParseWithMaximalWind() throws ParseException {
        // Given a code with wind variation.
        String code = "LFPG 161430Z 24015G25KT 180V300";
        //WHEN parsing the code.
        Metar m = parser.parse(code);
        // THEN the wind contains information on variation
        assertNotNull(m);
        assertEquals(240, m.getWind().getDirectionDegrees().intValue());
        assertEquals(15, m.getWind().getSpeed());
        assertEquals(25, m.getWind().getGust());
        assertEquals("KT", m.getWind().getUnit());
        assertEquals(180, m.getWind().getMinVariation());
        assertEquals(300, m.getWind().getMaxVariation());

    }

    @Test
    void testParseWithVerticalVisibility() throws ParseException {
        String code = "LFLL 160730Z 28002KT 0350 FG VV002";

        Metar m = parser.parse(code);

        assertNotNull(m);
        assertEquals(16, m.getDay());
        assertEquals(7, m.getTime().getHour());
        assertEquals(30, m.getTime().getMinute());
        assertNotNull(m.getWind());
        Wind w = m.getWind();
        assertEquals(280, w.getDirectionDegrees().intValue());
        assertEquals(2, w.getSpeed());

        assertNotNull(m.getVisibility());
        assertEquals("350m", m.getVisibility().getMainVisibility());
        assertThat(m.getWeatherConditions(), hasSize(1));
        assertEquals(Phenomenon.FOG, m.getWeatherConditions().get(0).getPhenomenons().get(0));
        assertNotNull(m.getVerticalVisibility());
        assertEquals(200, m.getVerticalVisibility().intValue());
        assertThat(m.toString(), containsString(Messages.getInstance().getString("ToString.visibility.main") + "=350m"));
    }

    @Test
    void testParseVisibilityWithNDV() throws ParseException {
        String code = "LSZL 300320Z AUTO 00000KT 9999NDV BKN060 OVC074 00/M04 Q1001\n" + "RMK=";
        Metar m = parser.parse(code);
        assertNotNull(m);
        assertEquals(TEN_KM, m.getVisibility().getMainVisibility());
    }

    @Test
    void testParseWithCavok() throws ParseException {
        // GIVEN a metar with token CAVOK
        String code = "LFPG 212030Z 03003KT CAVOK 09/06 Q1031 NOSIG";
        // WHEN parsing the metar.
        Metar m = parser.parse(code);
        // THEN the attribute cavok is true and the main visibility is > 10 km.
        assertNotNull(m);
        assertTrue(m.isCavok());
        assertEquals(TEN_KM, m.getVisibility().getMainVisibility());
        assertEquals(Integer.valueOf(9), m.getTemperature());
        assertEquals(Integer.valueOf(6), m.getDewPoint());
        assertEquals(Integer.valueOf(1031), m.getAltimeter());
        assertTrue(m.isNosig());
    }

    @Test
    void testParseWithAltimeterInMercury() throws ParseException {
        // GIVEN a metar with altimeter in inches of mercury
        String code = "KTTN 051853Z 04011KT 9999 VCTS SN FZFG BKN003 OVC010 M02/M02 A3006";
        // WHEN parsing the metar
        Metar m = parser.parse(code);
        // THEN the altimeter is converted in HPa
        assertNotNull(m);
        assertEquals(Integer.valueOf(1017), m.getAltimeter());
        assertThat(m.getWeatherConditions(), is(notNullValue()));
        assertThat(m.getWeatherConditions(), hasSize(3));
    }

    @Test
    void testParseWithRMK() throws ParseException {
        //GIVEN a metar with RMK
        String code = "CYWG 172000Z 30015G25KT 1 3/4SM R36/4000FT/D -SN BLSN BKN008 OVC040 M05/M08 Q1001 RMK SF5NS3 SLP134";
        // WHEN parsing the metar
        Metar m = parser.parse(code);
        // THEN the remark is not null
        assertNotNull(m);
        assertNotNull(m.getVisibility());
        assertEquals("1 3/4SM", m.getVisibility().getMainVisibility());
        assertThat(m.getRemark(), containsString("SF5NS3 " + Messages.getInstance().getString("Remark.Sea.Level.Pressure", "1013.4")));
    }

    @Test
    void testParseRMK() {
        Metar m = new Metar();
        String[] array = { "RMK", "AO2", "TSB40", "SLP176", "P0002", "T10171017=" };
        getParser().parseRMK(m, array, 0);
        String rmk = m.getRemark();
        assertNotNull(rmk);
        assertThat(rmk, not(containsString("RMK")));
    }

    @Test
    void alternativeWindForm() throws ParseException {
        String code = "ENLK 081350Z 26026G40 240V300 9999 VCSH FEW025 BKN030 02/M01 Q0996";
        Metar m = parser.parse(code);
        assertNotNull(m);
        assertNotNull(m.getWind());
        assertEquals(Integer.valueOf(260), m.getWind().getDirectionDegrees());
        assertEquals(26, m.getWind().getSpeed());
        assertEquals(40, m.getWind().getGust());
        assertEquals("KT", m.getWind().getUnit());
        assertEquals(240, m.getWind().getMinVariation());
        assertEquals(300, m.getWind().getMaxVariation());
        assertEquals(Intensity.IN_VICINITY, m.getWeatherConditions().get(0).getIntensity());
        assertEquals(Descriptive.SHOWERS, m.getWeatherConditions().get(0).getDescriptive());
    }

    @Test
    void desriptiveFieldIsPreservedAlsoWithoutWeatherConditions() throws ParseException {
        //example form field
        String code = "AGGH 140340Z 05010KT 9999 TS FEW020 SCT021CB BKN300 32/26 Q1010";
        Metar m = parser.parse(code);
        assertNotNull(m);
        assertEquals(1, m.getWeatherConditions().size());
        assertEquals(Descriptive.THUNDERSTORM, m.getWeatherConditions().get(0).getDescriptive());
    }

    @Test
    void testParseWithInvalidWeatherCondition() throws ParseException {
        Metar m = parser.parse("ENLK 081350Z 26026G40 240V300 9999 VCMI");

        assertThat(m.getWeatherConditions(), hasSize(0));
    }

    @Test
    void testParseWithRemarks() throws ParseException {
        String code = "KCOS 261454Z 34012G18KT 10SM BKN060 BKN100 15/10 A3020 RMK AO2 RAE45 SLP171 P0000 60002 T01500100 51009 $";
        Messages.getInstance().setLocale(Locale.ENGLISH);
        Metar m = parser.parse(code);

        assertEquals("KCOS", m.getStation());
        assertThat(m.getWeatherConditions(), empty());
        assertThat(m.getRemark(), containsString("automated station with a precipitation discriminator"));
        assertThat(m.getRemark(), containsString("rain ending at :45"));
        assertThat(m.getRemark(), containsString("sea level pressure of 1017.1 HPa"));
        assertThat(m.getRemark(), containsString("0/100 of an inch of precipitation fell in the last hour"));
        assertThat(m.getRemark(), containsString("0/100 of an inch of precipitation fell in the last hour"));
        assertThat(m.getRemark(), containsString("0.02 inches of precipitation fell in the last 6 hours"));
        assertThat(m.getRemark(), containsString("hourly temperature of 15°C and dew point of 10°C"));
        assertThat(m.getRemark(), containsString("Increase, then steady, or increase then Increase more slowly of 0.9 hectopascals in the past 3 hours"));
    }

    @Test
    void testParseWithMinimalVisibility() throws ParseException {
        String code = "SUMU 070520Z 34025KT 8000 2000SW VCSH SCT013CB BKN026 00/M05 Q1012 TEMPO 2000 SHSN=";
        Messages.getInstance().setLocale(Locale.ENGLISH);
        Metar m = parser.parse(code);
        assertEquals("SUMU", m.getStation());
        assertNotNull(m.getVisibility());
        assertEquals(2000, m.getVisibility().getMinVisibility());
        assertEquals("SW", m.getVisibility().getMinDirection());
    }

    @Test
    void testParseNil() throws ParseException {
        String code = "SVMC 211703Z AUTO NIL";
        Metar m = parser.parse(code);
        assertTrue(m.isNil());
    }

    @Test
    void testParseWithUnknownCloudType() throws ParseException {
        String code = "EKVG 291550Z AUTO 13009KT 9999 BKN037/// BKN048/// 07/06 Q1009 RMK FEW011/// FEW035/// WIND SKEID 13020KT";

        Metar m = parser.parse(code);

        assertNotNull(m);
        assertEquals("EKVG", m.getStation());
        assertThat(m.getClouds(), hasSize(2));
    }
    @Test
    void testParseVC() throws ParseException {
        String code = "CYVM 282100Z 36028G36KT 1SM -SN DRSN VCBLSN OVC008 M03/M04 A2935 RMK SN2ST8 LAST STFFD OBS/NXT 291200UTC SLP940";

        Metar m = parser.parse(code);

        assertNotNull(m);
        assertThat(m.getWeatherConditions(), hasSize(3));
        assertEquals(Intensity.LIGHT, m.getWeatherConditions().get(0).getIntensity());
        assertEquals(Phenomenon.SNOW, m.getWeatherConditions().get(0).getPhenomenons().get(0));
        assertEquals(Descriptive.DRIFTING, m.getWeatherConditions().get(1).getDescriptive());
        assertEquals(Phenomenon.SNOW, m.getWeatherConditions().get(1).getPhenomenons().get(0));
        assertEquals(Intensity.IN_VICINITY, m.getWeatherConditions().get(2).getIntensity());
        assertEquals(Descriptive.BLOWING, m.getWeatherConditions().get(2).getDescriptive());
        assertEquals(Phenomenon.SNOW, m.getWeatherConditions().get(2).getPhenomenons().get(0));
    }

    @Test
    void testParseRunwayDeposit() throws ParseException {
        String code = "UUDD 212100Z 20005MPS 8000 -FZRA SCT005 M01/M02 Q1010 R14R/590335 NOSIG";

        Messages.getInstance().setLocale(Locale.ENGLISH);
        Metar m = parser.parse(code);
        assertEquals("UUDD", m.getStation());
        assertThat(m.getRunways(), hasSize(1));
        assertEquals("14R", m.getRunways().get(0).getName());
        assertEquals(DepositType.WET_SNOW, m.getRunways().get(0).getDepositType());
        assertEquals(DepositCoverage.FROM_51_TO_100, m.getRunways().get(0).getCoverage());
        assertEquals("03 mm", m.getRunways().get(0).getThickness());
        assertEquals("friction coefficient of 0.35", m.getRunways().get(0).getBrakingCapacity());
    }

    @Test
    void testParsWithLowWind() throws ParseException {
        String code = "KATW 022045Z 00000KT 10SM SCT120 00/M08 A2996";

        Metar m = parser.parse(code);
        assertEquals("KATW", m.getStation());
        assertNotNull(m.getWind());
        assertEquals(0, m.getWind().getSpeed());
        assertEquals(0, m.getWind().getDirectionDegrees());
    }

    @Test
    void testParseWithRecentRain() throws ParseException {
        String code = "LTAE 250250Z VRB02KT 9999 BKN030 BKN080 06/05 Q1005 RESHRA NOSIG RMK RWY21 07004KT 040V100";

        Metar m = parser.parse(code);
        assertEquals("LTAE", m.getStation());
        assertEquals(1, m.getWeatherConditions().size());
        assertEquals(Intensity.RECENT, m.getWeatherConditions().get(0).getIntensity());
        assertEquals(Descriptive.SHOWERS, m.getWeatherConditions().get(0).getDescriptive());
        assertEquals(1, m.getWeatherConditions().get(0).getPhenomenons().size());
        assertEquals(Phenomenon.RAIN, m.getWeatherConditions().get(0).getPhenomenons().get(0));
    }

    @Test
    void testGetInstance() {
        assertNotNull(MetarParser.getInstance());
        assertInstanceOf(MetarParser.class, MetarParser.getInstance());
    }
}
