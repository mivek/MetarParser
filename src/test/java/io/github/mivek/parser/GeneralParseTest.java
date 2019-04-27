package io.github.mivek.parser;

import io.github.mivek.model.AbstractWeatherCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author mivek
 *
 */
@RunWith(Parameterized.class)
public abstract class GeneralParseTest<T extends AbstractWeatherCode> {

    private String fPartToParse;
    private boolean fExpected;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { "WS020/24045KT", true }, // Wind shear
            { "05009KT", true }, // Wind
            { "030V113", true }, // Wind variable
            { "9999", true }, // Main visibility
            { "6 1/2SM", true}, //Main visibility SM
            { "1100w", true }, // Min visibility
            { "VV002", true }, // Vertical visibility
            { "CAVOK", true }, // CAVOK
            { "SCT026CB", true }, // Cloud
            { "ZZZ026CB", false }, // Cloud null
            { "+SHGSRA", true }, // Weather condition
            { "+ZERT", false } // Weather null
        });
    }

    public GeneralParseTest(final String pPartToParse, final boolean pExpected) {
        fPartToParse = pPartToParse;
        fExpected = pExpected;
    }

    @Test
    public void testGeneralParse() {
        assertEquals(fExpected, getSut().generalParse(getWeatherCode(), fPartToParse));
    }

    protected abstract T getWeatherCode();

    protected abstract AbstractParser<T> getSut();
}
