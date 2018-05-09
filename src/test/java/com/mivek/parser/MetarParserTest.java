/**
 * 
 */
package com.mivek.parser;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.mivek.enums.CloudQuantity;
import com.mivek.enums.CloudType;
import com.mivek.enums.Descriptive;
import com.mivek.enums.Phenomenon;
import com.mivek.enums.TimeIndicator;
import com.mivek.enums.WeatherChangeType;
import com.mivek.model.Cloud;
import com.mivek.model.Metar;
import com.mivek.model.RunwayInfo;
import com.mivek.model.WeatherCode;
import com.mivek.model.WeatherCondition;
import com.mivek.model.trend.AbstractMetarTrend;

/**
 * Test class for {@link MetarParser}
 * @author mivek
 *
 */
public class MetarParserTest extends AbstractParserTest<Metar> {

	private static MetarParser fSut;

	@Override
	MetarParser getSut() {
		return fSut;
	}

	@Before
	public void setUp() {
		fSut = MetarParser.getInstance();
	}

	/**
	 * ======================== Test ParseRunWays ========================
	 */
	@Test
	public void testParseRunwayActionSimple() {
		String riString = "R26/0600U";

		RunwayInfo ri = fSut.parseRunWayAction(riString);

		assertNotNull(ri);
		assertEquals("26", ri.getName());
		assertEquals(600, ri.getMinRange());
		assertEquals(i18n.Messages.CONVERTER_U, ri.getTrend());
	}

	@Test
	public void testParseRunWaysComplex() {
		String riString = "R26L/0550V700U";

		RunwayInfo ri = fSut.parseRunWayAction(riString);
		assertNotNull(ri);
		assertEquals("26L", ri.getName());
		assertEquals(550, ri.getMinRange());
		assertEquals(700, ri.getMaxRange());
		assertEquals(i18n.Messages.CONVERTER_U, ri.getTrend());
	}

	@Test
	public void testParseRunWayNull() {
		String riString = "R26R/AZEZFDFS";

		RunwayInfo ri = fSut.parseRunWayAction(riString);

		assertNull(ri);
	}

	/**
	 * =========================== Test ParseMetarAction ===========================
	 */

	@Test
	public void testParse() {
		String metarString = "LFPG 170830Z 00000KT 0350 R27L/0375N R09R/0175N R26R/0500D R08L/0400N R26L/0275D R08R/0250N R27R/0300N R09L/0200N FG SCT000 M01/M01 Q1026 NOSIG";

		Metar m = fSut.parse(metarString);

		assertNotNull(m);

		assertEquals(fSut.getAirports().get("LFPG"), m.getAirport());
		assertEquals(Integer.valueOf(17), m.getDay());
		assertEquals(8, m.getTime().getHour());
		assertEquals(30, m.getTime().getMinute());
		assertNotNull(m.getWind());
		assertEquals(0, m.getWind().getSpeed());
		assertEquals(i18n.Messages.CONVERTER_N, m.getWind().getDirection());
		assertEquals("KT", m.getWind().getUnit());
		assertEquals("350m", m.getVisibility().getMainVisibility());
		assertThat(m.getRunways(), is(not(empty())));
		assertThat(m.getRunways(), hasSize(8));
		// Check if runways are correctly parsed
		assertEquals("27L", m.getRunways().get(0).getName());
		assertEquals(375, m.getRunways().get(0).getMinRange());
		assertEquals(i18n.Messages.CONVERTER_NSC, m.getRunways().get(0).getTrend());
	}

	@Test
	public void testParseNullAirport() {
		String metarString = "AAAA 170830Z 00000KT 0350 R27L/0375N R09R/0175N R26R/0500D R08L/0400N R26L/0275D R08R/0250N R27R/0300N R09L/0200N FG SCT000 M01/M01 Q1026 NOSIG";

		WeatherCode m = fSut.parse(metarString);

		assertNull(m);
	}

	@Test
	public void testParseWithTempo() {
		String metarString = "LFBG 081130Z AUTO 23012KT 9999 SCT022 BKN072 BKN090 22/16 Q1011 TEMPO 26015G25KT 3000 TSRA SCT025CB BKN050";

		Metar m = fSut.parse(metarString);
		assertNotNull(m);
		assertTrue(m.isAuto());
		assertThat(m.getClouds(), hasSize(3));
		assertThat(m.getTrends(), hasSize(1));
		AbstractMetarTrend trend = m.getTrends().get(0);
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
	public void testParseWithTempoAndBecmg() {
		String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO SHRA BECMG SKC";

		Metar m = fSut.parse(metarString);

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
	public void testParseWithTempoAndAT() {
		String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO AT0800 SHRA ";

		Metar m = fSut.parse(metarString);

		assertNotNull(m);
		assertThat(m.getTrends(), hasSize(1));
		assertThat(m.getTrends().get(0).getType(), is(WeatherChangeType.TEMPO));
		assertThat(m.getTrends().get(0).getWeatherConditions(), hasSize(1));
		AbstractMetarTrend trend = m.getTrends().get(0);
		WeatherCondition wc = trend.getWeatherConditions().get(0);
		assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
		assertThat(wc.getPhenomenons(), hasSize(1));
		assertThat(trend.getTimes(), hasSize(1));
		assertEquals(TimeIndicator.AT, trend.getTimes().get(0).getType());
		assertEquals(8, trend.getTimes().get(0).getTime().getHour());
		assertEquals(0, trend.getTimes().get(0).getTime().getMinute());
	}

	@Test
	public void testParseWithTempoAndTL() {
		String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO TL1830 SHRA ";

		Metar m = fSut.parse(metarString);

		assertNotNull(m);
		assertThat(m.getTrends(), hasSize(1));
		assertThat(m.getTrends().get(0).getType(), is(WeatherChangeType.TEMPO));
		assertThat(m.getTrends().get(0).getWeatherConditions(), hasSize(1));
		AbstractMetarTrend trend = m.getTrends().get(0);
		WeatherCondition wc = trend.getWeatherConditions().get(0);
		assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
		assertThat(wc.getPhenomenons(), hasSize(1));
		assertThat(trend.getTimes(), hasSize(1));
		assertEquals(TimeIndicator.TL, trend.getTimes().get(0).getType());
		assertEquals(18, trend.getTimes().get(0).getTime().getHour());
		assertEquals(30, trend.getTimes().get(0).getTime().getMinute());
	}

	@Test
	public void testParseWithTempoAndFM() {
		String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO FM1830 SHRA ";

		Metar m = fSut.parse(metarString);

		assertNotNull(m);
		assertThat(m.getTrends(), hasSize(1));
		assertThat(m.getTrends().get(0).getType(), is(WeatherChangeType.TEMPO));
		assertThat(m.getTrends().get(0).getWeatherConditions(), hasSize(1));
		AbstractMetarTrend trend = m.getTrends().get(0);
		WeatherCondition wc = trend.getWeatherConditions().get(0);
		assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
		assertThat(wc.getPhenomenons(), hasSize(1));
		assertThat(trend.getTimes(), hasSize(1));
		assertEquals(TimeIndicator.FM, trend.getTimes().get(0).getType());
		assertEquals(18, trend.getTimes().get(0).getTime().getHour());
		assertEquals(30, trend.getTimes().get(0).getTime().getMinute());
	}

	@Test
	public void testParseWithTempoAndFMAndTL() {
		String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO FM1700 TL1830 SHRA ";

		Metar m = fSut.parse(metarString);

		assertNotNull(m);
		assertThat(m.getTrends(), hasSize(1));
		assertThat(m.getTrends().get(0).getType(), is(WeatherChangeType.TEMPO));
		assertThat(m.getTrends().get(0).getWeatherConditions(), hasSize(1));
		AbstractMetarTrend trend = m.getTrends().get(0);
		WeatherCondition wc = trend.getWeatherConditions().get(0);
		assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
		assertThat(wc.getPhenomenons(), hasSize(1));
		assertThat(trend.getTimes(), hasSize(2));
		assertEquals(TimeIndicator.FM, trend.getTimes().get(0).getType());
		assertEquals(17, trend.getTimes().get(0).getTime().getHour());
		assertEquals(0, trend.getTimes().get(0).getTime().getMinute());
		assertEquals(TimeIndicator.TL, trend.getTimes().get(1).getType());
		assertEquals(18, trend.getTimes().get(1).getTime().getHour());
		assertEquals(30, trend.getTimes().get(1).getTime().getMinute());
	}
}
