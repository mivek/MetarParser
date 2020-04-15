package io.github.mivek.parser;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.CloudType;
import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.TAF;
import io.github.mivek.model.TemperatureDated;
import io.github.mivek.model.trend.AbstractTafTrend;
import io.github.mivek.model.trend.BECMGTafTrend;
import io.github.mivek.model.trend.FMTafTrend;
import io.github.mivek.model.trend.PROBTafTrend;
import io.github.mivek.model.trend.TEMPOTafTrend;
import io.github.mivek.model.trend.validity.BeginningValidity;
import io.github.mivek.model.trend.validity.Validity;
import io.github.mivek.utils.Converter;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Test class for {@link TAFParser}
 *
 * @author mivek
 */
public class TAFParserTest extends AbstractParserTest<TAF> {

    private TAFParser fSut;

    @Override
    protected TAFParser getSut() {
        return fSut;
    }

    @Before
    public void setUp() {
        fSut = TAFParser.getInstance();
    }


    @Test
    public void testProcessGeneralChangesWithTX() {
        AbstractTafTrend<?> change = new BECMGTafTrend();
        String part = "TX15/0612Z";
        fSut.processGeneralChanges(change, part);

        assertThat(change.getClouds(), is(empty()));
        assertThat(change.getWeatherConditions(), is(empty()));
    }

    @Test
    public void testProcessGeneralChangesWithTN() {
        AbstractTafTrend<?> change = new BECMGTafTrend();
        String part = "TN01/0612Z";
        fSut.processGeneralChanges(change, part);

        assertThat(change.getClouds(), is(empty()));
        assertThat(change.getWeatherConditions(), is(empty()));
    }

    @Test
    public void testProcessGeneralChangesCloudValid() {
        AbstractTafTrend<?> change = new BECMGTafTrend();
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
        AbstractTafTrend<?> change = new BECMGTafTrend();
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
    public void testParseValidWithInvalidLineBreaks() throws ParseException {
        String taf = "TAF LFPG 150500Z 1506/1612 17005KT 6000 SCT012 \n"
                + "TEMPO 1506/1509 3000 BR BKN006 PROB40 \n"
                + "TEMPO 1506/1508 0400 BCFG BKN002 PROB40 \n"
                + "TEMPO 1512/1516 4000 -SHRA FEW030TCU BKN040 \n"
                + "BECMG 1520/1522 CAVOK \n"
                + "TEMPO 1603/1608 3000 BR BKN006 PROB40 \n"
                + "TEMPO 1604/1607 0400 BCFG BKN002 TX17/1512Z TN07/1605Z";

        TAF res = fSut.parse(taf);

        assertThat(res, is(not(nullValue())));
        assertEquals(fSut.getAirportSupplier().get("LFPG"), res.getAirport());
        // Check on time delivery.
        assertEquals(Integer.valueOf(15), res.getDay());
        assertEquals(5, res.getTime().getHour());
        assertEquals(0, res.getTime().getMinute());
        // Checks on validity.
        assertEquals(Integer.valueOf(15), res.getValidity().getStartDay());
        assertEquals(Integer.valueOf(6), res.getValidity().getStartHour());
        assertEquals(Integer.valueOf(16), res.getValidity().getEndDay());
        assertEquals(Integer.valueOf(12), res.getValidity().getEndHour());
        // Checks on wind.
        assertThat(res.getWind().getDirection(), is(Converter.degreesToDirection("170")));
        assertThat(res.getWind().getSpeed(), is(5));
        assertThat(res.getWind().getGust(), is(0));
        assertThat(res.getWind().getUnit(), is("KT"));
        // Checks on visibility.
        assertThat(res.getVisibility().getMainVisibility(), is("6000m"));
        //Check on clouds.
        assertThat(res.getClouds(), hasSize(1));
        assertThat(res.getClouds().get(0).getQuantity(), is(CloudQuantity.SCT));
        assertThat(res.getClouds().get(0).getAltitude(), is(30 * 12));
        assertThat(res.getClouds().get(0).getType(), nullValue());

        assertThat(res.getMaxTemperature(), notNullValue());
        assertThat(res.getMinTemperature(), notNullValue());
        assertEquals(17, res.getMaxTemperature().getTemperature().intValue());
        assertEquals(15, res.getMaxTemperature().getDay().intValue());
        assertEquals(12, res.getMaxTemperature().getHour().intValue());
        assertEquals(7, res.getMinTemperature().getTemperature().intValue());
        assertEquals(16, res.getMinTemperature().getDay().intValue());
        assertEquals(5, res.getMinTemperature().getHour().intValue());

        // Check that no weatherCondition
        assertThat(res.getWeatherConditions(), empty());
        // Checks on tempos.
        assertThat(res.getTempos(), hasSize(5));
        // First tempo
        assertThat(res.getTempos().get(0).getValidity().getStartDay(), is(15));
        assertThat(res.getTempos().get(0).getValidity().getStartHour(), is(6));
        assertThat(res.getTempos().get(0).getValidity().getEndDay(), is(15));
        assertThat(res.getTempos().get(0).getValidity().getEndHour(), is(9));
        assertThat(res.getTempos().get(0).getVisibility().getMainVisibility(), is("3000m"));
        assertThat(res.getTempos().get(0).getWeatherConditions(), hasSize(1));
        assertThat(res.getTempos().get(0).getWeatherConditions().get(0).getIntensity(), is(nullValue()));
        assertThat(res.getTempos().get(0).getWeatherConditions().get(0).getDescriptives(), is(empty()));
        assertThat(res.getTempos().get(0).getWeatherConditions().get(0).getPhenomenons(), hasSize(1));
        assertThat(res.getTempos().get(0).getWeatherConditions().get(0).getPhenomenons().get(0), is(Phenomenon.MIST));
        assertThat(res.getTempos().get(0).getClouds(), hasSize(1));
        assertThat(res.getTempos().get(0).getClouds().get(0).getQuantity(), is(CloudQuantity.BKN));
        assertThat(res.getTempos().get(0).getClouds().get(0).getAltitude(), is(6 * 30));
        assertThat(res.getTempos().get(0).getClouds().get(0).getType(), nullValue());
        assertThat(res.getTempos().get(0).getProbability(), nullValue());
        // Second tempo
        assertThat(res.getTempos().get(1).getValidity().getStartDay(), is(15));
        assertThat(res.getTempos().get(1).getValidity().getStartHour(), is(6));
        assertThat(res.getTempos().get(1).getValidity().getEndDay(), is(15));
        assertThat(res.getTempos().get(1).getValidity().getEndHour(), is(8));
        assertThat(res.getTempos().get(1).getWind(), nullValue());
        assertThat(res.getTempos().get(1).getVisibility().getMainVisibility(), is("400m"));
        assertThat(res.getTempos().get(1).getWeatherConditions(), hasSize(1));
        assertThat(res.getTempos().get(1).getWeatherConditions().get(0).getIntensity(), is(nullValue()));
        assertThat(res.getTempos().get(1).getWeatherConditions().get(0).getDescriptives(), contains(Descriptive.PATCHES));
        assertThat(res.getTempos().get(1).getWeatherConditions().get(0).getPhenomenons(), hasSize(1));
        assertThat(res.getTempos().get(1).getWeatherConditions().get(0).getPhenomenons().get(0), is(Phenomenon.FOG));
        assertThat(res.getTempos().get(1).getClouds(), hasSize(1));
        assertThat(res.getTempos().get(1).getClouds().get(0).getQuantity(), is(CloudQuantity.BKN));
        assertThat(res.getTempos().get(1).getClouds().get(0).getAltitude(), is(30 * 2));
        assertThat(res.getTempos().get(1).getClouds().get(0).getHeight(), is(200));
        assertThat(res.getTempos().get(1).getProbability(), is(40));
        // Third tempo
        assertThat(res.getTempos().get(2).getValidity().getStartDay(), is(15));
        assertThat(res.getTempos().get(2).getValidity().getStartHour(), is(12));
        assertThat(res.getTempos().get(2).getValidity().getEndDay(), is(15));
        assertThat(res.getTempos().get(2).getValidity().getEndHour(), is(16));
        assertThat(res.getTempos().get(2).getVisibility().getMainVisibility(), is("4000m"));
        assertThat(res.getTempos().get(2).getWeatherConditions(), not(empty()));
        assertThat(res.getTempos().get(2).getWeatherConditions(), hasSize(1));
        assertThat(res.getTempos().get(2).getWeatherConditions().get(0).getIntensity(), is(Intensity.LIGHT));
        assertThat(res.getTempos().get(2).getWeatherConditions().get(0).getDescriptives(), contains(Descriptive.SHOWERS));
        assertThat(res.getTempos().get(2).getWeatherConditions().get(0).getPhenomenons(), hasSize(1));
        assertThat(res.getTempos().get(2).getWeatherConditions().get(0).getPhenomenons().get(0), is(Phenomenon.RAIN));
        assertThat(res.getTempos().get(2).getClouds(), hasSize(2));
        assertThat(res.getTempos().get(2).getClouds().get(0).getQuantity(), is(CloudQuantity.FEW));
        assertThat(res.getTempos().get(2).getClouds().get(0).getAltitude(), is(30 * 30));
        assertThat(res.getTempos().get(2).getClouds().get(0).getType(), is(CloudType.TCU));
        assertThat(res.getTempos().get(2).getClouds().get(1).getQuantity(), is(CloudQuantity.BKN));
        assertThat(res.getTempos().get(2).getClouds().get(1).getAltitude(), is(30 * 40));
        assertThat(res.getTempos().get(2).getClouds().get(1).getType(), nullValue());
        assertThat(res.getTempos().get(2).getProbability(), is(40));

        // First BECMG
        assertThat(res.getBECMGs(), hasSize(1));
        BECMGTafTrend becmg = res.getBECMGs().get(0);
        assertEquals(15, becmg.getValidity().getStartDay().intValue());
        assertEquals(20, becmg.getValidity().getStartHour().intValue());
        assertEquals(15, becmg.getValidity().getEndDay().intValue());
        assertEquals(22, becmg.getValidity().getEndHour().intValue());

        // Fourth Tempo
        TEMPOTafTrend tempo4 = res.getTempos().get(3);
        assertEquals(16, tempo4.getValidity().getStartDay().intValue());
        assertEquals(3, tempo4.getValidity().getStartHour().intValue());
        assertEquals(16, tempo4.getValidity().getEndDay().intValue());
        assertEquals(8, tempo4.getValidity().getEndHour().intValue());
        assertEquals("3000m", tempo4.getVisibility().getMainVisibility());
        assertThat(tempo4.getWeatherConditions(), hasSize(1));
        assertNull(tempo4.getWeatherConditions().get(0).getIntensity());
        assertTrue(tempo4.getWeatherConditions().get(0).getDescriptives().isEmpty());
        assertThat(tempo4.getWeatherConditions().get(0).getPhenomenons(), hasSize(1));
        assertEquals(Phenomenon.MIST, tempo4.getWeatherConditions().get(0).getPhenomenons().get(0));
        assertThat(tempo4.getClouds(), hasSize(1));
        assertEquals(CloudQuantity.BKN, tempo4.getClouds().get(0).getQuantity());
        assertNull(tempo4.getClouds().get(0).getType());
        assertEquals(6 * 30, tempo4.getClouds().get(0).getAltitude());
        assertNull(tempo4.getProbability());

        // Fifth Tempo
        TEMPOTafTrend tempo5 = res.getTempos().get(4);
        assertEquals(16, tempo5.getValidity().getStartDay().intValue());
        assertEquals(4, tempo5.getValidity().getStartHour().intValue());
        assertEquals(16, tempo5.getValidity().getEndDay().intValue());
        assertEquals(7, tempo5.getValidity().getEndHour().intValue());
        assertEquals("400m", tempo5.getVisibility().getMainVisibility());
        assertThat(tempo5.getWeatherConditions(), hasSize(1));
        assertNull(tempo5.getWeatherConditions().get(0).getIntensity());
        assertTrue(tempo5.getWeatherConditions().get(0).getDescriptives().contains(Descriptive.PATCHES));
        assertThat(tempo5.getWeatherConditions().get(0).getPhenomenons(), hasSize(1));
        assertEquals(Phenomenon.FOG, tempo5.getWeatherConditions().get(0).getPhenomenons().get(0));
        assertThat(tempo5.getClouds(), hasSize(1));
        assertEquals(CloudQuantity.BKN, tempo5.getClouds().get(0).getQuantity());
        assertEquals(2 * 30, tempo5.getClouds().get(0).getAltitude());
        assertNull(tempo5.getClouds().get(0).getType());
        assertThat(tempo5.getProbability(), is(40));
    }

    @Test
    public void testParseValidWithoutLineBreaks() throws ParseException {
        String taf = "TAF LSZH 292025Z 2921/3103 VRB03KT 9999 FEW020 BKN080 TX20/3014Z TN06/3003Z " +
                "PROB30 TEMPO 2921/2923 SHRA " +
                "BECMG 3001/3004 4000 MIFG NSC " +
                "PROB40 3003/3007 1500 BCFG SCT004 " +
                "PROB30 3004/3007 0800 FG VV003 " +
                "BECMG 3006/3009 9999 FEW030 " +
                "PROB40 TEMPO 3012/3017 30008KT";

        TAF res = fSut.parse(taf);

        assertThat(res, is(not(nullValue())));
        assertEquals(fSut.getAirportSupplier().get("LSZH"), res.getAirport());
        // Check on time delivery.
        assertEquals(29, res.getDay().intValue());
        assertEquals(20, res.getTime().getHour());
        assertEquals(25, res.getTime().getMinute());
        // Checks on validity.
        assertEquals(29, res.getValidity().getStartDay().intValue());
        assertEquals(21, res.getValidity().getStartHour().intValue());
        assertEquals(31, res.getValidity().getEndDay().intValue());
        assertEquals(3, res.getValidity().getEndHour().intValue());
        // Checks on wind.
        assertThat(res.getWind().getDirectionDegrees(), nullValue());
        assertThat(res.getWind().getDirection(), is(Converter.degreesToDirection("VRB")));
        assertThat(res.getWind().getSpeed(), is(3));
        assertThat(res.getWind().getGust(), is(0));
        assertThat(res.getWind().getUnit(), is("KT"));
        // Checks on visibility.
        assertThat(res.getVisibility().getMainVisibility(), is(">10km"));
        //Check on clouds.
        assertThat(res.getClouds(), hasSize(2));
        assertThat(res.getClouds().get(0).getQuantity(), is(CloudQuantity.FEW));
        assertThat(res.getClouds().get(0).getHeight(), is(2000));
        assertThat(res.getClouds().get(0).getType(), nullValue());

        assertThat(res.getClouds().get(1).getQuantity(), is(CloudQuantity.BKN));
        assertThat(res.getClouds().get(1).getHeight(), is(8000));
        assertThat(res.getClouds().get(1).getType(), nullValue());
        // Check that no weatherCondition
        assertThat(res.getWeatherConditions(), empty());
        // Check max temperature
        assertThat(res.getMaxTemperature().getDay(), is(30));
        assertThat(res.getMaxTemperature().getHour(), is(14));
        assertThat(res.getMaxTemperature().getTemperature(), is(20));
        // Check min temperature
        assertThat(res.getMinTemperature().getDay(), is(30));
        assertThat(res.getMinTemperature().getHour(), is(3));
        assertThat(res.getMinTemperature().getTemperature(), is(6));

        // Checks on tempos.
        assertThat(res.getTempos(), hasSize(2));
        // Checks on BECOMGs.
        assertThat(res.getBECMGs(), hasSize(2));
        // Checks on probs.
        assertThat(res.getProbs(), hasSize(2));

        // First TEMPO
        TEMPOTafTrend tempo0 = res.getTempos().get(0);
        assertThat(tempo0.getValidity().getStartDay(), is(29));
        assertThat(tempo0.getValidity().getStartHour(), is(21));
        assertThat(tempo0.getValidity().getEndDay(), is(29));
        assertThat(tempo0.getValidity().getEndHour(), is(23));
        assertThat(tempo0.getWeatherConditions(), hasSize(1));
        assertThat(tempo0.getWeatherConditions().get(0).getIntensity(), is(nullValue()));
        assertTrue(tempo0.getWeatherConditions().get(0).getDescriptives().contains(Descriptive.SHOWERS));
        assertThat(tempo0.getWeatherConditions().get(0).getPhenomenons(), hasSize(1));
        assertThat(tempo0.getWeatherConditions().get(0).getPhenomenons().get(0), is(Phenomenon.RAIN));
        assertThat(tempo0.getProbability(), is(30));

        // First BECOMG
        BECMGTafTrend becmg0 = res.getBECMGs().get(0);
        assertThat(becmg0.getValidity().getStartDay(), is(30));
        assertThat(becmg0.getValidity().getStartHour(), is(1));
        assertThat(becmg0.getValidity().getEndDay(), is(30));
        assertThat(becmg0.getValidity().getEndHour(), is(4));
        assertThat(becmg0.getVisibility().getMainVisibility(), is("4000m"));
        assertThat(becmg0.getWeatherConditions().get(0).getIntensity(), is(nullValue()));
        assertTrue(becmg0.getWeatherConditions().get(0).getDescriptives().contains(Descriptive.SHALLOW));
        assertThat(becmg0.getWeatherConditions().get(0).getPhenomenons(), hasSize(1));
        assertThat(becmg0.getWeatherConditions().get(0).getPhenomenons().get(0), is(Phenomenon.FOG));
        assertThat(becmg0.getClouds().get(0).getQuantity(), is(CloudQuantity.NSC));

        // First PROB
        PROBTafTrend prob0 = res.getProbs().get(0);
        assertThat(prob0.getValidity().getStartDay(), is(30));
        assertThat(prob0.getValidity().getStartHour(), is(3));
        assertThat(prob0.getValidity().getEndDay(), is(30));
        assertThat(prob0.getValidity().getEndHour(), is(7));
        assertThat(prob0.getVisibility().getMainVisibility(), is("1500m"));
        assertThat(prob0.getWeatherConditions().get(0).getIntensity(), is(nullValue()));
        assertThat(prob0.getWeatherConditions().get(0).getDescriptives(), contains(Descriptive.PATCHES));
        assertThat(prob0.getWeatherConditions().get(0).getPhenomenons(), hasSize(1));
        assertThat(prob0.getWeatherConditions().get(0).getPhenomenons().get(0), is(Phenomenon.FOG));
        assertThat(prob0.getClouds(), hasSize(1));
        assertThat(prob0.getClouds().get(0).getQuantity(), is(CloudQuantity.SCT));
        assertThat(prob0.getClouds().get(0).getHeight(), is(400));
        assertThat(prob0.getClouds().get(0).getType(), nullValue());
        assertThat(prob0.getProbability(), is(40));

        // Second PROB
        PROBTafTrend prob1 = res.getProbs().get(1);
        assertThat(prob1.getValidity().getStartDay(), is(30));
        assertThat(prob1.getValidity().getStartHour(), is(4));
        assertThat(prob1.getValidity().getEndDay(), is(30));
        assertThat(prob1.getValidity().getEndHour(), is(7));
        assertThat(prob1.getVisibility().getMainVisibility(), is("800m"));
        assertThat(prob1.getWeatherConditions().get(0).getIntensity(), is(nullValue()));
        assertThat(prob1.getWeatherConditions().get(0).getDescriptives(), is(empty()));
        assertThat(prob1.getWeatherConditions().get(0).getPhenomenons(), hasSize(1));
        assertThat(prob1.getWeatherConditions().get(0).getPhenomenons().get(0), is(Phenomenon.FOG));
        assertThat(prob1.getVerticalVisibility(), is(300));
        assertThat(prob1.getClouds(), hasSize(0));
        assertThat(prob1.getProbability(), is(30));

        // Second BECOMG
        BECMGTafTrend becmg1 = res.getBECMGs().get(1);
        assertThat(becmg1.getValidity().getStartDay(), is(30));
        assertThat(becmg1.getValidity().getStartHour(), is(6));
        assertThat(becmg1.getValidity().getEndDay(), is(30));
        assertThat(becmg1.getValidity().getEndHour(), is(9));
        assertThat(becmg1.getVisibility().getMainVisibility(), is(">10km"));
        assertThat(becmg1.getWeatherConditions(), hasSize(0));
        assertThat(becmg1.getClouds(), hasSize(1));
        assertThat(becmg1.getClouds().get(0).getQuantity(), is(CloudQuantity.FEW));
        assertThat(becmg1.getClouds().get(0).getHeight(), is(3000));
        assertThat(becmg1.getClouds().get(0).getType(), nullValue());

        // Second TEMPO
        TEMPOTafTrend tempo1 = res.getTempos().get(1);
        assertThat(tempo1.getValidity().getStartDay(), is(30));
        assertThat(tempo1.getValidity().getStartHour(), is(12));
        assertThat(tempo1.getValidity().getEndDay(), is(30));
        assertThat(tempo1.getValidity().getEndHour(), is(17));
        assertThat(tempo1.getWeatherConditions(), hasSize(0));
        assertThat(tempo1.getWind().getDirectionDegrees(), is(300));
        assertThat(tempo1.getWind().getDirection(), is(Converter.degreesToDirection("300.0")));
        assertThat(tempo1.getWind().getSpeed(), is(8));
        assertThat(tempo1.getWind().getGust(), is(0));
        assertThat(tempo1.getWind().getUnit(), is("KT"));
        assertThat(tempo1.getProbability(), is(40));
    }

    @Test
    public void testParseValidWithoutLineBreaksAndEndingTemperature() throws ParseException {
        String taf = "TAF KLSV 120700Z 1207/1313 VRB06KT 9999 SCT250 QNH2992INS BECMG 1217/1218 10010G15KT 9999 SCT250 QNH2980INS BECMG 1303/1304 VRB06KT 9999 FEW250 QNH2979INS TX42/1223Z TN24/1213Z";

        TAF res = fSut.parse(taf);

        assertThat(res, is(not(nullValue())));
        assertEquals(fSut.getAirportSupplier().get("KLSV"), res.getAirport());
        // Check on time delivery.
        assertEquals(12, res.getDay().intValue());
        assertEquals(7, res.getTime().getHour());
        assertEquals(0, res.getTime().getMinute());
        // Checks on validity.
        assertEquals(12, res.getValidity().getStartDay().intValue());
        assertEquals(7, res.getValidity().getStartHour().intValue());
        assertEquals(13, res.getValidity().getEndDay().intValue());
        assertEquals(13, res.getValidity().getEndHour().intValue());
        // Checks on wind.
        assertThat(res.getWind().getDirectionDegrees(), nullValue());
        assertThat(res.getWind().getDirection(), is(Converter.degreesToDirection("VRB")));
        assertThat(res.getWind().getSpeed(), is(6));
        assertThat(res.getWind().getGust(), is(0));
        assertThat(res.getWind().getUnit(), is("KT"));
        // Checks on visibility.
        assertThat(res.getVisibility().getMainVisibility(), is(">10km"));
        //Check on clouds.
        assertThat(res.getClouds(), hasSize(1));
        assertThat(res.getClouds().get(0).getQuantity(), is(CloudQuantity.SCT));
        assertThat(res.getClouds().get(0).getHeight(), is(25000));
        assertThat(res.getClouds().get(0).getType(), nullValue());

        // Check that no weatherCondition
        assertThat(res.getWeatherConditions(), empty());
        // Check max temperature
        assertThat(res.getMaxTemperature().getDay(), is(12));
        assertThat(res.getMaxTemperature().getHour(), is(23));
        assertThat(res.getMaxTemperature().getTemperature(), is(42));
        // Check min temperature
        assertThat(res.getMinTemperature().getDay(), is(12));
        assertThat(res.getMinTemperature().getHour(), is(13));
        assertThat(res.getMinTemperature().getTemperature(), is(24));

        // Checks on BECOMGs.
        assertThat(res.getBECMGs(), hasSize(2));

        // First BECOMG
        BECMGTafTrend becmg0 = res.getBECMGs().get(0);
        assertThat(becmg0.getValidity().getStartDay(), is(12));
        assertThat(becmg0.getValidity().getStartHour(), is(17));
        assertThat(becmg0.getValidity().getEndDay(), is(12));
        assertThat(becmg0.getValidity().getEndHour(), is(18));
        assertThat(becmg0.getVisibility().getMainVisibility(), is(">10km"));
        assertThat(becmg0.getWeatherConditions(), hasSize(0));
        assertThat(becmg0.getClouds().get(0).getQuantity(), is(CloudQuantity.SCT));
        assertThat(becmg0.getClouds().get(0).getHeight(), is(25000));
        assertThat(becmg0.getWind().getDirectionDegrees(), is(100));
        assertThat(becmg0.getWind().getDirection(), is(Converter.degreesToDirection("100.0")));
        assertThat(becmg0.getWind().getSpeed(), is(10));
        assertThat(becmg0.getWind().getGust(), is(15));
        assertThat(becmg0.getWind().getUnit(), is("KT"));

        // Second BECOMG
        BECMGTafTrend becmg1 = res.getBECMGs().get(1);
        assertThat(becmg1.getValidity().getStartDay(), is(13));
        assertThat(becmg1.getValidity().getStartHour(), is(3));
        assertThat(becmg1.getValidity().getEndDay(), is(13));
        assertThat(becmg1.getValidity().getEndHour(), is(4));
        assertThat(becmg1.getVisibility().getMainVisibility(), is(">10km"));
        assertThat(becmg1.getWind().getDirectionDegrees(), nullValue());
        assertThat(becmg1.getWind().getDirection(), is(Converter.degreesToDirection("VRB")));
        assertThat(becmg1.getWind().getSpeed(), is(6));
        assertThat(becmg1.getWind().getGust(), is(0));
        assertThat(becmg1.getWind().getUnit(), is("KT"));
        assertThat(becmg1.getWeatherConditions(), hasSize(0));
        assertThat(becmg1.getClouds(), hasSize(1));
        assertThat(becmg1.getClouds().get(0).getQuantity(), is(CloudQuantity.FEW));
        assertThat(becmg1.getClouds().get(0).getHeight(), is(25000));
        assertThat(becmg1.getClouds().get(0).getType(), nullValue());
    }

    @Test
    public void testParseWithFM() throws ParseException {
        String message = "TAF KLWT 211120Z 2112/2212 20008KT 9999 SKC \n" + "TEMPO 2112/2116 VRB06KT \n"
                + "FM212300 30012G22KT 9999 FEW050 SCT250 \n" + "FM220700 27008KT 9999 FEW030 FEW250";

        TAF res = fSut.parse(message);
        assertNotNull(res);

        assertThat(res, is(not(nullValue())));
        assertThat(res.getAirport(), is(fSut.getAirportSupplier().get("KLWT")));
        assertThat(res.getDay(), is(21));
        assertThat(res.getTime().getHour(), is(11));
        assertThat(res.getTime().getMinute(), is(20));
        assertThat(res.getValidity().getStartDay(), is(21));
        assertThat(res.getValidity().getStartHour(), is(12));
        assertThat(res.getValidity().getEndDay(), is(22));
        assertThat(res.getValidity().getEndHour(), is(12));

        // Wind
        assertThat(res.getWind(), is(not(nullValue())));
        assertThat(res.getWind().getDirection(), is(Converter.degreesToDirection("200")));
        assertThat(res.getWind().getSpeed(), is(8));
        assertThat(res.getWind().getGust(), is(0));
        assertThat(res.getWind().getUnit(), is("KT"));

        // Visibility
        assertThat(res.getVisibility(), is(not(nullValue())));
        assertThat(res.getVisibility().getMainVisibility(), is(">10km"));
        assertThat(res.getVisibility().getMinDirection(), is(nullValue()));
        //Clouds
        assertThat(res.getClouds(), hasSize(1));
        assertThat(res.getClouds().get(0).getQuantity(), is(CloudQuantity.SKC));

        assertThat(res.getFMs(), hasSize(2));
        FMTafTrend fm1 = res.getFMs().get(0);
        assertEquals(21, fm1.getValidity().getStartDay().intValue());
        assertEquals(23, fm1.getValidity().getStartHour().intValue());
        assertEquals(0, fm1.getValidity().getStartMinutes().intValue());
        assertEquals(300, fm1.getWind().getDirectionDegrees().intValue());
        assertEquals(12, fm1.getWind().getSpeed());
        assertEquals(22, fm1.getWind().getGust());
        assertThat(fm1.getClouds(), hasSize(2));
        assertEquals(CloudQuantity.FEW, fm1.getClouds().get(0).getQuantity());
        assertEquals(50 * 30, fm1.getClouds().get(0).getAltitude());
        assertNull(fm1.getClouds().get(0).getType());
        assertEquals(CloudQuantity.SCT, fm1.getClouds().get(1).getQuantity());
        assertEquals(250 * 30, fm1.getClouds().get(1).getAltitude());
        assertNull(fm1.getClouds().get(1).getType());
        // Tempos
        assertThat(res.getTempos(), hasSize(1));
        assertThat(res.getTempos().get(0).getValidity().getStartDay(), is(21));
        assertThat(res.getTempos().get(0).getValidity().getStartHour(), is(12));
        assertThat(res.getTempos().get(0).getValidity().getEndDay(), is(21));
        assertThat(res.getTempos().get(0).getValidity().getEndHour(), is(16));
        assertEquals(Messages.getInstance().getString("Converter.VRB"), res.getTempos().get(0).getWind().getDirection());
        assertThat(res.getTempos().get(0).getWind().getSpeed(), is(6));

        FMTafTrend fm2 = res.getFMs().get(1);
        assertEquals(22, fm2.getValidity().getStartDay().intValue());
        assertEquals(7, fm2.getValidity().getStartHour().intValue());
        assertEquals(0, fm2.getValidity().getStartMinutes().intValue());
        assertThat(fm2.getClouds(), hasSize(2));

    }

    @Test
    public void testParseWith2Taf() throws ParseException {
        String message = "TAF TAF LFPG 191100Z 1912/2018 02010KT 9999 FEW040 PROB30 ";

        TAF result = fSut.parse(message);

        assertNotNull(result);
        assertThat(result.getProbs(), hasSize(1));
        assertThat(result.getProbs().get(0).getProbability(), is(30));
    }

    @Test
    public void testParseInvalidMessage() throws ParseException {
        String message = "LFPG 191100Z 1912/2018 02010KT 9999 FEW040 PROB30 ";
        thrown.expect(ParseException.class);
        thrown.expect(hasProperty("errorCode", is(ErrorCodes.ERROR_CODE_INVALID_MESSAGE)));
        fSut.parse(message);
    }

    @Test
    public void testParseWithWindShear() throws ParseException {
        // GIVEN a TAF message with windshear in principal part and from part.
        String message = "TAF KMKE 011530 0116/0218 WS020/24045KT\n" + "FM010200 17005KT P6SM SKC WS020/23055KT ";
        // WHEN parsing the message.
        TAF result = fSut.parse(message);

        assertEquals(message, result.getMessage());
        // THEN the windshear of the principle part is decoded
        assertNotNull(result);
        assertNotNull(result.getWindShear());
        assertEquals(2000, result.getWindShear().getHeight());
        assertEquals(240, result.getWindShear().getDirectionDegrees().intValue());
        assertEquals(45, result.getWindShear().getSpeed());

        // Checks on the from part.
        FMTafTrend fm = result.getFMs().get(0);
        assertNotNull(fm);
        // Checks on the wind of the FM
        assertNotNull(fm.getWind());
        assertEquals(170, fm.getWind().getDirectionDegrees().intValue());
        assertEquals(5, fm.getWind().getSpeed());
        // Checks on the wind shear of the fm
        assertNotNull(fm.getWindShear());
        assertEquals(2000, fm.getWindShear().getHeight());
        assertEquals(230, fm.getWindShear().getDirectionDegrees().intValue());
        assertEquals(55, fm.getWindShear().getSpeed());
    }

    @Test
    public void testParseInvalidAirport() throws ParseException {
        String message = "TAF AAAA 191100Z 1912/2018 02010KT 9999 FEW040 PROB30";
        TAF res = fSut.parse(message);
        assertNull(res.getAirport());
        assertEquals("AAAA", res.getStation());
    }

    @Test
    public void testParseWithNauticalMilesVisibility() throws ParseException {
        //GIVEN a TAF message with nautical miles visibility;
        String message = "TAF CZBF 300939Z 3010/3022 VRB03KT 6SM -SN OVC015 \r\n" +
                "TEMPO 3010/3012 11/2SM -SN OVC009 \nFM301200 10008KT 2SM -SN OVC010 \r\n" +
                "TEMPO 3012/3022 3/4SM -SN VV007 RMK FCST BASED ON AUTO OBS. NXT FCST BY 301400Z";

        //WHEN parsing the message.
        TAF result = fSut.parse(message);
        //THEN the visibility of the main event is 6 SM
        assertNotNull(result);
        assertNotNull(result.getVisibility());
        assertEquals("6SM", result.getVisibility().getMainVisibility());
        //THEN the visibility of the first tempo is 11/2 SM
        assertNotNull(result.getTempos().get(0).getVisibility());
        assertEquals("11/2SM", result.getTempos().get(0).getVisibility().getMainVisibility());
        //THEN the visibility of the second tempo is 3/4 SM
        assertNotNull(result.getTempos().get(1).getVisibility());
        assertEquals("3/4SM", result.getTempos().get(1).getVisibility().getMainVisibility());
        // Then the visibility of the FROM part is 2SN
        assertNotNull(result.getFMs().get(0).getVisibility());
        assertEquals("2SM", result.getFMs().get(0).getVisibility().getMainVisibility());
        assertFalse(result.isAmendment());
    }

    @Test
    public void testParseWithAMDTAF() throws ParseException {
        //GIVEN an amended TAF
        String message = "TAF AMD LFPO 100742Z 1007/1112 21018G35KT 9999 BKN025 \r\n" +
                "      TEMPO 1007/1009 4000 RA BKN014 SCT020CB PROB40 \r\n" +
                "      TEMPO 1007/1009 22020G45KT \r\n" +
                "      TEMPO 1010/1017 24022G45KT SHRA SCT030CB PROB30 \r\n" +
                "      TEMPO 1012/1016 -TSRA \r\n" +
                "      BECMG 1020/1023 24013KT SCT023 \r\n" +
                "      TEMPO 1104/1112 4000 -SHRA BKN012 BKN020TCU";
        //WHEN parsing the message
        TAF result = fSut.parse(message);
        //Then the taf is correctly parsed and the amendment attribute is true.
        assertTrue(result.isAmendment());
    }

    @Test
    public void testParseWithCavok() throws ParseException {
        // GIVEN a TAF with CAVOK in the main part and in a BECMG part.
        String message = "TAF LFPG 211700Z 2118/2224 VRB03KT CAVOK TX15/2215Z TN02/2205Z \n" +
                "BECMG 2122/2124 3000 BR \n" +
                "TEMPO 2202/2209 0800 BCFG PROB30 \n" +
                "TEMPO 2203/2209 0500 FG  \n" +
                "BECMG 2211/2213 09006KT CAVOK";

        // WHEN parsing the event.
        TAF result = fSut.parse(message);
        // THEN the result is CAVOK and the second BECMG is also cavok.
        assertNotNull(result);
        assertTrue(result.isCavok());
        assertEquals(">10km", result.getVisibility().getMainVisibility());
        assertTrue(result.getBECMGs().get(1).isCavok());
        assertEquals(">10km", result.getBECMGs().get(1).getVisibility().getMainVisibility());
    }

    @Test
    public void testParseWithRMKInTempo() throws ParseException {
        // GIVEN a TAF with remark in second tempo.
        String message = "TAF CZBF 300939Z 3010/3022 VRB03KT 6SM -SN OVC015\n" + "TEMPO 3010/3012 11/2SM -SN OVC009 FM301200 10008KT 2SM -SN OVC010 \n"
                + "TEMPO 3012/3022 3/4SM -SN VV007 RMK FCST BASED ON AUTO OBS. NXT FCST BY 301400Z";
        // WHEN parsing the event.
        TAF result = fSut.parse(message);
        // THEN the second tempo contains the remark.
        assertNotNull(result);
        assertNotNull(result.getTempos().get(1));
        assertNotNull(result.getTempos().get(1).getRemark());
        String rmk = Messages.getInstance().getString("Remark.FCST") + " " + Messages.getInstance().getString("Remark.BASED") + " " + Messages.getInstance().getString("Remark.ON") + " AUTO OBS. "
                + Messages.getInstance().getString("Remark.NXT") + " " + Messages.getInstance().getString("Remark.FCST") + " BY 301400Z";
        assertThat(result.getTempos().get(1).getRemark(), containsString(rmk));
    }

    @Test
    public void testParseWithRMK() throws ParseException {
        // GIVEN a TAF with remark.
        String message = "TAF CZBF 300939Z 3010/3022 VRB03KT 6SM -SN OVC015 RMK FCST BASED ON AUTO OBS. NXT FCST BY 301400Z\n" + "TEMPO 3010/3012 11/2SM -SN OVC009 FM301200 10008KT 2SM -SN OVC010 \n"
                + "TEMPO 3012/3022 3/4SM -SN VV007";
        // WHEN parsing the event.
        TAF result = fSut.parse(message);
        // THEN the second tempo contains the remark.
        assertNotNull(result);
        String rmk = Messages.getInstance().getString("Remark.FCST") + " " + Messages.getInstance().getString("Remark.BASED") + " " + Messages.getInstance().getString("Remark.ON") + " AUTO OBS. "
                + Messages.getInstance().getString("Remark.NXT") + " " + Messages.getInstance().getString("Remark.FCST") + " BY 301400Z";
        assertThat(result.getRemark(), containsString(rmk));
        String description = result.toString();
        assertThat(description, containsString(Messages.getInstance().getString("ToString.start.day.month") + "=30"));
        assertThat(description, containsString(Messages.getInstance().getString("ToString.start.hour.day") + "=10"));
        assertThat(description, containsString(Messages.getInstance().getString("ToString.end.day.month") + "=30"));
        assertThat(description, containsString(Messages.getInstance().getString("ToString.end.hour.day") + "=12"));
    }

}
