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

import org.junit.Before;
import org.junit.Test;

import com.mivek.model.Metar;
import com.mivek.model.RunwayInfo;
import com.mivek.model.AbstractWeatherCode;

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

		AbstractWeatherCode m = fSut.parse(metarString);

		assertNull(m);
	}
}
