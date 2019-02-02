package com.mivek.parser;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import internationalization.Messages;
import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.CloudType;
import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.AbstractWeatherCode;
import io.github.mivek.model.Cloud;
import io.github.mivek.model.Visibility;
import io.github.mivek.model.WeatherCondition;
import io.github.mivek.model.Wind;

/**
 * Test class for {@link AbstractParser}
 * @author mivek
 */
@Ignore
public abstract class AbstractParserTest<T extends AbstractWeatherCode> {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    /*
     * ===========================
     * TEST ParseCloud
     * ==========================
     */

    @Test
    public void testParseCloudNullCloudQuantity() {
        String cloud = "AZE015";

        Cloud res = getSut().parseCloud(cloud);

        assertNull(res);
    }

    @Test
    public void testParseCloudSkyClear() {
        String cloud = "SKC";

        Cloud res = getSut().parseCloud(cloud);

        assertNotNull(res);
        assertEquals(CloudQuantity.SKC, res.getQuantity());
        assertEquals(0, res.getAltitude());
        assertEquals(0, res.getHeight());
        assertNull(res.getType());
    }

    @Test
    public void testParseCloudWithAltitude() {
        String cloud = "SCT016";
        Cloud res = getSut().parseCloud(cloud);

        assertNotNull(res);
        assertEquals(CloudQuantity.SCT, res.getQuantity());
        assertEquals(480, res.getAltitude());
        assertEquals(1600, res.getHeight());
        assertNull(res.getType());
    }

    @Test
    public void testParseCloudWithType() {
        String cloud = "SCT026CB";

        Cloud res = getSut().parseCloud(cloud);

        assertNotNull(res);
        assertEquals(CloudQuantity.SCT, res.getQuantity());
        assertEquals(30 * 26, res.getAltitude());
        assertEquals(2600, res.getHeight());
        assertNotNull(res.getType());
        assertEquals(CloudType.CB, res.getType());
    }

    @Test
    public void testParseCloudWithNSC() {
        String cloud = "NSC";
        Cloud res = getSut().parseCloud(cloud);
        assertNotNull(res);
        assertEquals(CloudQuantity.NSC, res.getQuantity());
    }

    /**
     * ===================== TEST ParseWind ==================== *
     */
    @Test
    public void testParseWindSimple() {
        String windPart = "34008KT";

        Wind res = getSut().parseWind(windPart);

        assertNotNull(res);
        assertThat(res.getDirection(), is(Messages.getInstance().getConverterN()));
        assertEquals(Integer.valueOf(340), res.getDirectionDegrees());
        assertEquals(8, res.getSpeed());
        assertEquals(0, res.getGust());
        assertEquals("KT", res.getUnit());

    }

    @Test
    public void testParseWindWithGusts() {
        String windPart = "12017G20KT";

        Wind res = getSut().parseWind(windPart);

        assertNotNull(res);
        assertThat(res.getDirection(), is(Messages.getInstance().getConverterSE()));
        assertEquals(Integer.valueOf(120), res.getDirectionDegrees());
        assertEquals(17, res.getSpeed());
        assertEquals(20, res.getGust());
        assertEquals("KT", res.getUnit());
    }

    @Test
    public void testParseWindVariable() {
        String windPart = "VRB08KT";

        Wind res = getSut().parseWind(windPart);

        assertNotNull(res);
        assertEquals(Messages.getInstance().getConverterVRB(), res.getDirection());
        assertEquals(8, res.getSpeed());
        assertNull(res.getDirectionDegrees());
    }

    /*
     * =================== WEATHER CONDITION ===================
     */
    @Test
    public void testParseWCSimple() {
        String wcPart = "-DZ";

        WeatherCondition wc = getSut().parseWeatherCondition(wcPart);

        assertEquals(Intensity.LIGHT, wc.getIntensity());
        assertNull(wc.getDescriptive());
        assertThat(wc.getPhenomenons(), hasSize(1));
        assertThat(wc.getPhenomenons(), hasItem(Phenomenon.DRIZZLE));
    }

    @Test
    public void testParseWCMultiplePHE() {
        String wcPart = "SHRAGR";

        WeatherCondition wc = getSut().parseWeatherCondition(wcPart);

        assertNull(wc.getIntensity());
        assertNotNull(wc.getDescriptive());
        assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
        assertThat(wc.getPhenomenons(), hasSize(2));
        assertThat(wc.getPhenomenons(), hasItems(Phenomenon.RAIN, Phenomenon.HAIL));
    }

    @Test
    public void testParseWCNull() {
        String wcPart = "-SH";

        WeatherCondition wc = getSut().parseWeatherCondition(wcPart);

        assertNull(wc);
    }

    @Test
    public void testParseWithoutAirport() throws ParseException {
        String message = "LLLL 191100Z 1912/2018 02010KT 9999 FEW040 PROB30 ";
        thrown.expect(ParseException.class);
        getSut().parse(message);
    }

    @Test
    public void testParseMinimalVisibility() {
        Visibility v = new Visibility();
        String code = "1100w";
        getSut().parseMinimalVisibility(v, code);
        assertEquals(1100, v.getMinVisibility());
        assertEquals("w", v.getMinDirection());
    }

    protected abstract AbstractParser<T> getSut();
}
