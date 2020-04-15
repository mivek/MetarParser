package io.github.mivek.parser;

import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.model.AbstractWeatherCode;
import io.github.mivek.model.WeatherCondition;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Test class for {@link AbstractParser}
 * @author mivek
 */
@Ignore
public abstract class AbstractParserTest<T extends AbstractWeatherCode> {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    /*
     * =================== WEATHER CONDITION ===================
     */
    @Test
    public void testParseWCSimple() {
        String wcPart = "-DZ";

        WeatherCondition wc = getSut().parseWeatherCondition(wcPart);

        assertEquals(Intensity.LIGHT, wc.getIntensity());
        assertTrue(wc.getDescriptives().isEmpty());
        assertThat(wc.getPhenomenons(), hasSize(1));
        assertThat(wc.getPhenomenons(), hasItem(Phenomenon.DRIZZLE));
    }

    @Test
    public void testParseWCMultiplePHE() {
        String wcPart = "SHRAGR";

        WeatherCondition wc = getSut().parseWeatherCondition(wcPart);

        assertNull(wc.getIntensity());
        assertThat(wc.getDescriptives(), hasSize(1));
        assertTrue(wc.getDescriptives().contains(Descriptive.SHOWERS));
        assertThat(wc.getPhenomenons(), hasSize(2));
        assertThat(wc.getPhenomenons(), hasItems(Phenomenon.RAIN, Phenomenon.HAIL));
    }

    @Test public void testParseWCNotNull() {
        String wcPart = "-SH";

        WeatherCondition wc = getSut().parseWeatherCondition(wcPart);

        assertNotNull(wc);
    }

    @Test
    public void testParseWCNull() {
        String wcPart = "SH";

        WeatherCondition wc = getSut().parseWeatherCondition(wcPart);

        assertNotNull(wc);
        assertTrue(wc.getDescriptives().contains(Descriptive.SHOWERS));
        assertNull(wc.getIntensity());
        assertEquals(0, wc.getPhenomenons().size());
    }

    @Test
    public void testTokenize() {
        // GIVEN a string with 1 1/2SM
        String code = "METAR KTTN 051853Z 04011KT 1 1/2SM VCTS SN FZFG BKN003 OVC010 M02/M02 A3006 RMK AO2 TSB40 SLP176 P0002 T10171017=";
        String[] tokens = { "METAR", "KTTN", "051853Z", "04011KT", "1 1/2SM", "VCTS", "SN", "FZFG", "BKN003", "OVC010", "M02/M02", "A3006", "RMK", "AO2", "TSB40", "SLP176", "P0002", "T10171017" };
        // WHEN tokenizing the string
        String[] result = getSut().tokenize(code);
        // THEN the visibility part is 1 1/2SM
        assertNotNull(result);
        assertArrayEquals(tokens, result);

    }

    protected abstract AbstractParser<T> getSut();
}
