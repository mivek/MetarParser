/**
 * 
 */
package com.mivek.parser;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.mivek.enums.CloudQuantity;
import com.mivek.enums.CloudType;
import com.mivek.enums.Descriptive;
import com.mivek.enums.Phenomenon;
import com.mivek.model.TAF;
import com.mivek.model.Validity;
import com.mivek.utils.Converter;

/**
 * @author mivek
 *
 */
public class TAFParserTest extends AbstractParserTest<TAF> {

	private static TAFParser fSut;


	TAFParser getSut() {
		return fSut;
	}

	@Before
	public void setUp() {
		fSut = TAFParser.getInstance();
	}

	@Test
	public void testParseValidity() {
		String validityString = "3118/0124";

		Validity res = fSut.parseValidity(validityString);

		assertThat(res, is(not(nullValue())));
		assertEquals(Integer.valueOf(31), res.getStartDay());
		assertEquals(Integer.valueOf(18), res.getStartHour());
		assertEquals(Integer.valueOf(1), res.getEndDay());
		assertEquals(Integer.valueOf(24), res.getEndHour());
	}

	@Test
	public void testParseValid() {
		String taf = "TAF LFPB 011100Z 0112/0212 19020G40KT 6000 BKN015 TEMPO 0112/0113 2000 RA BKN010 TEMPO 0114/0122 27020G40KT 3000 SHRA SCT020TCU BECMG 0115/0117 9999 BKN020 PROB40 TEMPO 0116/0118 27025G55KT BECMG 0202/0204 27012KT";
		TAF res = fSut.parse(taf);

		assertThat(res, is(not(nullValue())));
		assertEquals(fSut.getAirports().get("LFPB"), res.getAirport());
		// Check on time delivery.
		assertEquals(Integer.valueOf(1), res.getDay());
		assertEquals(11, res.getTime().getHours());
		assertEquals(0, res.getTime().getMinutes());
		// Checks on validity.
		assertEquals(Integer.valueOf(1), res.getValidity().getStartDay());
		assertEquals(Integer.valueOf(12), res.getValidity().getStartHour());
		assertEquals(Integer.valueOf(2), res.getValidity().getEndDay());
		assertEquals(Integer.valueOf(12), res.getValidity().getEndHour());
		// Checks on wind.
		assertThat(res.getWind().getDirection(), is(Converter.degreesToDirection("190")));
		assertThat(res.getWind().getSpeed(), is(20));
		assertThat(res.getWind().getGust(), is(40));
		assertThat(res.getWind().getUnit(), is("KT"));
		// Checks on visibility.
		assertThat(res.getVisibility().getMainVisibility(), is("6000m"));
		// Checks on tempos.
		assertThat(res.getTempos(), hasSize(3));
		// First tempo
		assertThat(res.getTempos().get(0).getValidity().getStartDay(), is(1));
		assertThat(res.getTempos().get(0).getValidity().getStartHour(), is(12));
		assertThat(res.getTempos().get(0).getValidity().getEndDay(), is(1));
		assertThat(res.getTempos().get(0).getValidity().getEndHour(), is(13));
		assertThat(res.getTempos().get(0).getVisibility().getMainVisibility(), is("2000m"));
		assertThat(res.getTempos().get(0).getWeatherConditions(), hasSize(1));
		assertThat(res.getTempos().get(0).getWeatherConditions().get(0).getIntensity(), is(nullValue()));
		assertThat(res.getTempos().get(0).getWeatherConditions().get(0).getDescriptive(), is(nullValue()));
		assertThat(res.getTempos().get(0).getWeatherConditions().get(0).getPhenomenons(), hasSize(1));
		assertThat(res.getTempos().get(0).getWeatherConditions().get(0).getPhenomenons().get(0), is(Phenomenon.RAIN));
		// Second tempo
		assertThat(res.getTempos().get(1).getValidity().getStartDay(), is(1));
		assertThat(res.getTempos().get(1).getValidity().getStartHour(), is(14));
		assertThat(res.getTempos().get(1).getValidity().getEndDay(), is(1));
		assertThat(res.getTempos().get(1).getValidity().getEndHour(), is(22));
		assertThat(res.getTempos().get(1).getWind().getDirection(), is(Converter.degreesToDirection("270")));
		assertThat(res.getTempos().get(1).getWind().getSpeed(), is(20));
		assertThat(res.getTempos().get(1).getWind().getGust(), is(40));
		assertThat(res.getTempos().get(1).getWind().getUnit(), is("KT"));
		assertThat(res.getTempos().get(1).getVisibility().getMainVisibility(), is("3000m"));
		assertThat(res.getTempos().get(1).getWeatherConditions(), hasSize(1));
		assertThat(res.getTempos().get(1).getWeatherConditions().get(0).getIntensity(), is(nullValue()));
		assertThat(res.getTempos().get(1).getWeatherConditions().get(0).getDescriptive(), is(Descriptive.SHOWERS));
		assertThat(res.getTempos().get(1).getWeatherConditions().get(0).getPhenomenons(), hasSize(1));
		assertThat(res.getTempos().get(1).getWeatherConditions().get(0).getPhenomenons().get(0), is(Phenomenon.RAIN));
		assertThat(res.getTempos().get(1).getClouds(), hasSize(1));
		assertThat(res.getTempos().get(1).getClouds().get(0).getQuantity(), is(CloudQuantity.SCT));
		assertThat(res.getTempos().get(1).getClouds().get(0).getType(), is(CloudType.TCU));
		assertThat(res.getTempos().get(1).getClouds().get(0).getAltitude(), is(30 * 20));

		assertThat(res.getBECMGs(), hasSize(2));
		// First BECMG.
		assertThat(res.getBECMGs().get(0).getValidity().getStartDay(), is(1));
		assertThat(res.getBECMGs().get(0).getValidity().getStartHour(), is(15));
		assertThat(res.getBECMGs().get(0).getValidity().getEndDay(), is(1));
		assertThat(res.getBECMGs().get(0).getValidity().getEndHour(), is(17));
		assertThat(res.getBECMGs().get(0).getVisibility().getMainVisibility(), is(">10km"));
		assertThat(res.getBECMGs().get(0).getClouds(), hasSize(1));
		assertThat(res.getBECMGs().get(0).getClouds().get(0).getQuantity(), is(CloudQuantity.BKN));
		assertThat(res.getBECMGs().get(0).getClouds().get(0).getAltitude(), is(30 * 20));
		assertThat(res.getBECMGs().get(0).getClouds().get(0).getType(), is(nullValue()));
		assertThat(res.getBECMGs().get(0).getProbability(), is(40));

		// Third TEMPO
		assertThat(res.getTempos().get(2).getValidity().getStartDay(), is(1));
		assertThat(res.getTempos().get(2).getValidity().getStartHour(), is(16));
		assertThat(res.getTempos().get(2).getValidity().getEndDay(), is(1));
		assertThat(res.getTempos().get(2).getValidity().getEndHour(), is(18));
		assertThat(res.getTempos().get(2).getWind().getDirection(), is(Converter.degreesToDirection("270")));
		assertThat(res.getTempos().get(2).getWind().getSpeed(), is(25));
		assertThat(res.getTempos().get(2).getWind().getGust(), is(55));
		assertThat(res.getTempos().get(2).getWind().getUnit(), is("KT"));
		assertThat(res.getTempos().get(2).getClouds(), is(empty()));
		assertThat(res.getTempos().get(2).getWeatherConditions(), is(empty()));
		assertThat(res.getTempos().get(2).getProbability(), is(nullValue()));
		assertThat(res.getTempos().get(2).getVisibility(), is(nullValue()));

		// Second BECMG
		assertThat(res.getBECMGs().get(1).getValidity().getStartDay(), is(2));
		assertThat(res.getBECMGs().get(1).getValidity().getStartHour(), is(2));
		assertThat(res.getBECMGs().get(1).getValidity().getEndDay(), is(2));
		assertThat(res.getBECMGs().get(1).getValidity().getEndHour(), is(4));
		assertThat(res.getBECMGs().get(1).getWind().getDirection(), is(Converter.degreesToDirection("270")));
		assertThat(res.getBECMGs().get(1).getWind().getSpeed(), is(12));
		assertThat(res.getBECMGs().get(1).getWind().getGust(), is(0));
		assertThat(res.getBECMGs().get(1).getWind().getUnit(), is("KT"));
		assertThat(res.getBECMGs().get(1).getClouds(), is(empty()));
		assertThat(res.getBECMGs().get(1).getWeatherConditions(), is(empty()));
		assertThat(res.getBECMGs().get(1).getProbability(), is(nullValue()));
		assertThat(res.getBECMGs().get(1).getVisibility(), is(nullValue()));
	}
}
