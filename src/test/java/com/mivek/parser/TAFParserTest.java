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
import com.mivek.enums.Intensity;
import com.mivek.enums.Phenomenon;
import com.mivek.model.AbstractWeatherChange;
import com.mivek.model.BECMGChange;
import com.mivek.model.BeginningValidity;
import com.mivek.model.Cloud;
import com.mivek.model.TAF;
import com.mivek.model.TEMPOChange;
import com.mivek.model.TemperatureDated;
import com.mivek.model.Validity;
import com.mivek.model.Wind;
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
	public void testprocessGeneralChangesWithPROB() {
		AbstractWeatherChange<?> change = new TEMPOChange();
		String part = "PROB56";
		fSut.processGeneralChanges(change, part);

		assertThat(change.getProbability(), is(56));
		assertThat(change.getClouds(), is(empty()));
		assertThat(change.getWeatherConditions(), is(empty()));
	}

	@Test
	public void testprocessGeneralChangesWithTX() {
		AbstractWeatherChange<?> change = new BECMGChange();
		String part = "TX15/0612Z";
		fSut.processGeneralChanges(change, part);

		assertThat(change.getMaxTemperature(), is(not(nullValue())));
		assertThat(change.getMaxTemperature().getTemperature(), is(15));
		assertThat(change.getMaxTemperature().getDay(), is(6));
		assertThat(change.getMaxTemperature().getHour(), is(12));
		assertThat(change.getMinTemperature(), is(nullValue()));
		assertThat(change.getClouds(), is(empty()));
		assertThat(change.getWeatherConditions(), is(empty()));
	}

	@Test
	public void testprocessGeneralChangesWithTN() {
		AbstractWeatherChange<?> change = new BECMGChange();
		String part = "TN01/0612Z";
		fSut.processGeneralChanges(change, part);

		assertThat(change.getMinTemperature(), is(not(nullValue())));
		assertThat(change.getMinTemperature().getTemperature(), is(1));
		assertThat(change.getMinTemperature().getDay(), is(6));
		assertThat(change.getMinTemperature().getHour(), is(12));
		assertThat(change.getMaxTemperature(), is(nullValue()));
		assertThat(change.getClouds(), is(empty()));
		assertThat(change.getWeatherConditions(), is(empty()));
	}

	@Test
	public void testProcessGeneralChangesCloudValid() {
		AbstractWeatherChange<?> change = new BECMGChange();
		String part = "SCT012TCU";
		fSut.processGeneralChanges(change, part);

		assertThat(change.getClouds(), hasSize(1));
		assertThat(change.getClouds().get(0).getQuantity(), is(CloudQuantity.SCT));
		assertThat(change.getClouds().get(0).getAltitude(), is(12 * 30));
		assertThat(change.getClouds().get(0).getHeight(), is(1200));
		assertThat(change.getClouds().get(0).getType(), is(CloudType.TCU));
	}

	@Test
	public void testProcessGeneralChangesCloudNull() {
		AbstractWeatherChange<?> change = new BECMGChange();
		String part = "NSW";
		fSut.processGeneralChanges(change, part);

		assertThat(change.getClouds(), is(empty()));
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
	public void testParseTemperatureMax() {
		String tempString = "TX15/0612Z";
		TemperatureDated res = fSut.parseTemperature(tempString);

		assertThat(res.getTemperature(), is(15));
		assertThat(res.getDay(), is(6));
		assertThat(res.getHour(), is(12));
	}

	@Test
	public void testParseTemperatureMinus() {
		String tempString = "TNM02/0612Z";
		TemperatureDated res = fSut.parseTemperature(tempString);

		assertThat(res.getTemperature(), is(-2));
		assertThat(res.getDay(), is(6));
		assertThat(res.getHour(), is(12));
	}
	@Test
	public void testParseBeginningValidity() {
		String validity = "FM061300";
		BeginningValidity res = fSut.parseBasicValidity(validity);
		assertThat(res.getStartDay(), is(6));
		assertThat(res.getStartHour(), is(13));
		assertThat(res.getStartMinutes(), is(0));
	}

	@Test
	public void testParseValid() {
		String taf = "TAF LFPB 011100Z 0112/0212 19020G40KT 6000 BKN015 TEMPO 0112/0113 2000 RA BKN010 TEMPO 0114/0122 27020G40KT 3000 SHRA SCT020TCU BECMG 0115/0117 9999 BKN020 PROB40 TEMPO 0116/0118 27025G55KT BECMG 0202/0204 27012KT";
		TAF res = fSut.parse(taf);

		assertThat(res, is(not(nullValue())));
		assertEquals(fSut.getAirports().get("LFPB"), res.getAirport());
		// Check on time delivery.
		assertEquals(Integer.valueOf(1), res.getDay());
		assertEquals(11, res.getTime().getHour());
		assertEquals(0, res.getTime().getMinute());
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
		assertThat(res.getTempos().get(1).getClouds().get(0).getHeight(), is(2000));

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
		assertThat(res.getBECMGs().get(0).getClouds().get(0).getHeight(), is(2000));
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

	@Test
	public void parseTAFWithFMs() {
		String tafCode = "TAF LLIB 061123Z 0612/0712 18006KT 9999 SCT020 TEMPO 0612/0613 7000 -RA BKN015 FM061300 34008KT 9999 SCT025 BECMG 0616/0618 VRB03KT 4000 BR SCT015 BECMG 0706/0709 16006KT 9999 NSW FEW035 TX15/0612Z TN05/0704Z";

		TAF res = fSut.parse(tafCode);

		assertThat(res, is(not(nullValue())));
		assertThat(res.getAirport(), is(fSut.getAirports().get("LLIB")));
		assertThat(res.getDay(), is(6));
		assertThat(res.getTime().getHour(), is(11));
		assertThat(res.getTime().getMinute(), is(23));
		assertThat(res.getValidity().getStartDay(), is(6));
		assertThat(res.getValidity().getStartHour(), is(12));
		assertThat(res.getValidity().getEndDay(), is(7));
		assertThat(res.getValidity().getEndHour(), is(12));

		// Wind
		assertThat(res.getWind(), is(not(nullValue())));
		assertThat(res.getWind().getDirection(), is(Converter.degreesToDirection("180")));
		assertThat(res.getWind().getSpeed(), is(6));
		assertThat(res.getWind().getGust(), is(0));
		assertThat(res.getWind().getUnit(), is("KT"));

		// Visibility
		assertThat(res.getVisibility(), is(not(nullValue())));
		assertThat(res.getVisibility().getMainVisibility(), is(">10km"));
		assertThat(res.getVisibility().getMinDirection(), is(nullValue()));
		//Clouds
		assertThat(res.getClouds(), hasSize(1));
		assertThat(res.getClouds().get(0).getQuantity(), is(CloudQuantity.SCT));
		assertThat(res.getClouds().get(0).getAltitude(), is(30 * 20));
		assertThat(res.getClouds().get(0).getHeight(), is(2000));

		// Tempos
		assertThat(res.getTempos(), hasSize(1));
		assertThat(res.getTempos().get(0).getValidity().getStartDay(), is(6));
		assertThat(res.getTempos().get(0).getValidity().getStartHour(), is(12));
		assertThat(res.getTempos().get(0).getValidity().getEndDay(), is(6));
		assertThat(res.getTempos().get(0).getValidity().getEndHour(), is(13));
		assertThat(res.getTempos().get(0).getVisibility().getMainVisibility(), is("7000m"));
		assertThat(res.getTempos().get(0).getWeatherConditions(), hasSize(1));
		assertThat(res.getTempos().get(0).getWeatherConditions().get(0).getIntensity(), is(Intensity.LIGHT));
		assertThat(res.getTempos().get(0).getWeatherConditions().get(0).getDescriptive(), is(nullValue()));
		assertThat(res.getTempos().get(0).getWeatherConditions().get(0).getPhenomenons(), hasSize(1));
		assertThat(res.getTempos().get(0).getWeatherConditions().get(0).getPhenomenons().get(0), is(Phenomenon.RAIN));
		assertThat(res.getTempos().get(0).getClouds(), hasSize(1));
		assertThat(res.getTempos().get(0).getClouds().get(0).getQuantity(), is(CloudQuantity.BKN));
		assertThat(res.getTempos().get(0).getClouds().get(0).getAltitude(), is(30 * 15));
		assertThat(res.getTempos().get(0).getClouds().get(0).getHeight(), is(1500));
		assertThat(res.getTempos().get(0).getClouds().get(0).getType(), is(nullValue()));

		// FMs
		assertThat(res.getFMs(), hasSize(1));
		assertThat(res.getFMs().get(0).getValidity().getStartDay(), is(6));
		assertThat(res.getFMs().get(0).getValidity().getStartHour(), is(13));
		assertThat(res.getFMs().get(0).getValidity().getStartMinutes(), is(0));
		assertThat(res.getFMs().get(0).getWind(), is(not(nullValue())));
		Wind FMWind = res.getFMs().get(0).getWind();
		assertThat(FMWind.getDirection(), is(Converter.degreesToDirection("340")));
		assertThat(FMWind.getSpeed(), is(8));
		assertThat(FMWind.getExtreme1(), is(0));
		assertThat(FMWind.getExtreme2(), is(0));
		assertThat(FMWind.getGust(), is(0));
		assertThat(FMWind.getUnit(), is("KT"));
		assertThat(res.getFMs().get(0).getVisibility().getMainVisibility(), is(">10km"));
		assertThat(res.getFMs().get(0).getClouds(), hasSize(1));
		Cloud FMCloud = res.getFMs().get(0).getClouds().get(0);
		assertThat(FMCloud.getQuantity(), is(CloudQuantity.SCT));
		assertThat(FMCloud.getAltitude(), is(30 * 25));
		assertThat(FMCloud.getHeight(), is(2500));
		assertThat(FMCloud.getType(), is(nullValue()));

		// BECMS Only the second will be checks;
		assertThat(res.getBECMGs(), hasSize(2));
		BECMGChange becmg2 = res.getBECMGs().get(1);
		assertThat(becmg2.getValidity().getStartDay(), is(7));
		assertThat(becmg2.getValidity().getStartHour(), is(6));
		assertThat(becmg2.getValidity().getEndDay(), is(7));
		assertThat(becmg2.getValidity().getEndHour(), is(9));
		assertThat(becmg2.getWind(), is(not(nullValue())));
		assertThat(becmg2.getWind().getDirection(), is(Converter.degreesToDirection("160")));
		assertThat(becmg2.getWind().getSpeed(), is(6));
		assertThat(becmg2.getWind().getUnit(), is("KT"));
		assertThat(becmg2.getVisibility(), is(not(nullValue())));
		assertThat(becmg2.getVisibility().getMainVisibility(), is(">10km"));
		assertThat(becmg2.getClouds(), hasSize(1));
		assertThat(becmg2.getClouds().get(0).getQuantity(), is(CloudQuantity.FEW));
		assertThat(becmg2.getClouds().get(0).getAltitude(), is(30 * 35));
		assertThat(becmg2.getClouds().get(0).getHeight(), is(3500));
		assertThat(becmg2.getMaxTemperature(), is(not(nullValue())));
		assertThat(becmg2.getMaxTemperature().getTemperature(), is(15));
		assertThat(becmg2.getMaxTemperature().getDay(), is(6));
		assertThat(becmg2.getMaxTemperature().getHour(), is(12));
		assertThat(becmg2.getMinTemperature().getTemperature(), is(5));
		assertThat(becmg2.getMinTemperature().getDay(), is(7));
		assertThat(becmg2.getMinTemperature().getHour(), is(4));
	}
}
