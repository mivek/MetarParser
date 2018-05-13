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
import org.junit.Test;

import com.mivek.enums.CloudQuantity;
import com.mivek.enums.CloudType;
import com.mivek.enums.Descriptive;
import com.mivek.enums.Intensity;
import com.mivek.enums.Phenomenon;
import com.mivek.model.AbstractWeatherCode;
import com.mivek.model.Cloud;
import com.mivek.model.WeatherCondition;
import com.mivek.model.Wind;

/**
 * Test class for {@link AbstractParser}
 * @author mivek
 */
@Ignore
public abstract class AbstractParserTest<T extends AbstractWeatherCode> {
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

    /**
     * ===================== TEST ParseWind ==================== *
     */
    @Test
    public void testParseWindSimple() {
        String windPart = "34008KT";

        Wind res = getSut().parseWind(windPart);

        assertNotNull(res);
        assertThat(res.getDirection(), is(i18n.Messages.CONVERTER_N));
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
        assertThat(res.getDirection(), is(i18n.Messages.CONVERTER_SE));
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
        assertEquals(i18n.Messages.CONVERTER_VRB, res.getDirection());
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

    abstract AbstractParser<T> getSut();
}
