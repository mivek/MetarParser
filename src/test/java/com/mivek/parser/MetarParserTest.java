/**
 * 
 */
package com.mivek.parser;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.mivek.enums.CloudQuantity;
import com.mivek.enums.CloudType;
import com.mivek.enums.Descriptive;
import com.mivek.enums.Intensity;
import com.mivek.enums.Phenomenon;
import com.mivek.model.Cloud;
import com.mivek.model.Metar;
import com.mivek.model.RunwayInfo;
import com.mivek.model.WeatherCode;
import com.mivek.model.WeatherCondition;
import com.mivek.model.Wind;
import com.mivek.parser.MetarParser;

/**
 * @author mivek
 *
 */
public class MetarParserTest {


	/*
	 * ===========================
	 * 
	 * TEST ParseCloud
	 * 
	 * ==========================
	 */

	@Test
	public void testParseCloudNullCloudQuantity() {
		String[] cloudTab = new String[] { "AZE", "015" };

		Cloud res = MetarParser.getInstance().parseCloud(cloudTab);

		assertNull(res);
	}

	@Test
	public void testParseCloudSkyClear() {
		String[] cloudTab = new String[] { "SKC", "SKC", null, null };

		Cloud res = MetarParser.getInstance().parseCloud(cloudTab);

		assertNotNull(res);
		assertEquals(CloudQuantity.SKC, res.getQuantity());
		assertEquals(0, res.getAltitude());
		assertNull(res.getType());
	}

	@Test
	public void testParseCloudWithAltitude() {
		String[] cloudTab = new String[] { "SCT016", "SCT", "016", null };
		Cloud res = MetarParser.getInstance().parseCloud(cloudTab);

		assertNotNull(res);
		assertEquals(CloudQuantity.SCT, res.getQuantity());
		assertEquals(480, res.getAltitude());
		assertNull(res.getType());
	}

	@Test
	public void testParseCloudWithType() {
		String[] cloudTab = new String[] { "SCT026CB", "SCT", "026", "CB" };

		Cloud res = MetarParser.getInstance().parseCloud(cloudTab);

		assertNotNull(res);
		assertEquals(CloudQuantity.SCT, res.getQuantity());
		assertEquals(30 * 26, res.getAltitude());
		assertNotNull(res.getType());
		assertEquals(CloudType.CB, res.getType());
	}

	/**
	 * ===================== TEST ParseWind ==================== *
	 */
	@Test
	public void testParseWindSimple() {
		String[] windPart = new String[] { "34008KT", "340", "08", null, "KT" };

		Wind res = MetarParser.getInstance().parseWind(windPart);

		assertNotNull(res);
		assertThat(res.getDirection(), is(i18n.Messages.CONVERTER_N));
		assertEquals(8, res.getSpeed());
		assertEquals(0, res.getGust());
		assertEquals("KT", res.getUnit());
		
	}

	@Test
	public void testParseWindWithGusts() {
		String[] windPart = new String[] { "12017G20KT", "120", "17", "20", "KT" };

		Wind res = MetarParser.getInstance().parseWind(windPart);

		assertNotNull(res);
		assertThat(res.getDirection(), is(i18n.Messages.CONVERTER_SE));
		assertEquals(17, res.getSpeed());
		assertEquals(20, res.getGust());
		assertEquals("KT", res.getUnit());
	}

	/*
	 * =================== WEATHER CONDITION ===================
	 */
	@Test
	public void testParseWCSimple() {
		String wcPart = "-DZ";
		
		WeatherCondition wc = MetarParser.getInstance().parseWeatherCondition(wcPart);
		
		assertEquals(Intensity.LIGHT, wc.getIntensity());
		assertNull(wc.getDescriptive());
		assertThat(wc.getPhenomenons(), hasSize(1));
		assertThat(wc.getPhenomenons(), hasItem(Phenomenon.DRIZZLE));
	}

	@Test
	public void testParseWCMultiplePHE() {
		String wcPart = "SHRAGR";

		WeatherCondition wc = MetarParser.getInstance().parseWeatherCondition(wcPart);

		assertNull(wc.getIntensity());
		assertNotNull(wc.getDescriptive());
		assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
		assertThat(wc.getPhenomenons(), hasSize(2));
		assertThat(wc.getPhenomenons(), hasItems(Phenomenon.RAIN, Phenomenon.HAIL));
	}

	@Test
	public void testParseWCNull() {
		String wcPart = "-SH";

		WeatherCondition wc = MetarParser.getInstance().parseWeatherCondition(wcPart);

		assertNull(wc);
	}

	/**
	 * ======================== Test ParseRunWays ========================
	 */
	@Test
	public void testParseRunwayActionSimple() {
		String riString = "R26/0600U";

		RunwayInfo ri = MetarParser.getInstance().parseRunWayAction(riString);

		assertNotNull(ri);
		assertEquals("26", ri.getName());
		assertEquals(600, ri.getMinRange());
		assertEquals(i18n.Messages.CONVERTER_U, ri.getTrend());
	}

	@Test
	public void testParseRunWaysComplex() {
		String riString = "R26L/0550V700U";

		RunwayInfo ri = MetarParser.getInstance().parseRunWayAction(riString);
		assertNotNull(ri);
		assertEquals("26L", ri.getName());
		assertEquals(550, ri.getMinRange());
		assertEquals(700, ri.getMaxRange());
		assertEquals(i18n.Messages.CONVERTER_U, ri.getTrend());
	}

	@Test
	public void testParseRunWayNull() {
		String riString = "R26R/AZEZFDFS";

		RunwayInfo ri = MetarParser.getInstance().parseRunWayAction(riString);

		assertNull(ri);
	}

	/**
		 * =========================== Test ParseMetarAction ===========================
		 */
	
		@Test
		public void testParse() {
			String metarString = "LFPG 170830Z 00000KT 0350 R27L/0375N R09R/0175N R26R/0500D R08L/0400N R26L/0275D R08R/0250N R27R/0300N R09L/0200N FG SCT000 M01/M01 Q1026 NOSIG";
	
	
			Metar m = MetarParser.getInstance().parse(metarString);
	
			assertNotNull(m);
	
			MetarParser.getInstance();
			assertEquals(MetarParser.getAirports().get("LFPG"), m.getAirport());
			assertEquals(Integer.valueOf(17), m.getDay());
			assertEquals(8, m.getTime().getHours());
			assertEquals(30, m.getTime().getMinutes());
			assertNotNull(m.getWind());
			assertEquals(0, m.getWind().getSpeed());
			assertEquals(i18n.Messages.CONVERTER_N, m.getWind().getDirection());
			assertEquals("KT", m.getWind().getUnit());
			assertEquals("350m", m.getVisibility().getMainVisibility());
			assertThat(m.getRunways(), is(not(empty())));
			assertThat(m.getRunways(), hasSize(8));
			//Check if runways are correctly parsed
			assertEquals("27L", m.getRunways().get(0).getName());
			assertEquals(375, m.getRunways().get(0).getMinRange());
			assertEquals(i18n.Messages.CONVERTER_NSC, m.getRunways().get(0).getTrend());
		}

	@Test
		public void testParseNullAirport() {
			String metarString = "AAAA 170830Z 00000KT 0350 R27L/0375N R09R/0175N R26R/0500D R08L/0400N R26L/0275D R08R/0250N R27R/0300N R09L/0200N FG SCT000 M01/M01 Q1026 NOSIG";
	
			WeatherCode m = MetarParser.getInstance().parse(metarString);
	
			assertNull(m);
		}
}
