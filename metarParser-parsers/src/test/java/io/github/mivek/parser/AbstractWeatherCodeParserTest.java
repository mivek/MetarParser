package io.github.mivek.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.mivek.model.AbstractWeatherCode;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link AbstractWeatherCodeParser}
 *
 * @author mivek
 */
@Disabled
abstract class AbstractWeatherCodeParserTest<T extends AbstractWeatherCode> extends AbstractWeatherContainerParserTest<T, String> {
    protected abstract AbstractWeatherCodeParser<T> getParser();
    @Test
    void testTokenize() {
        // GIVEN a string with 1 1/2SM
        String code = "METAR KTTN 051853Z 04011KT 1 1/2SM VCTS SN FZFG BKN003 OVC010 M02/M02 A3006 RMK AO2 TSB40 SLP176 P0002 T10171017=";
        String[] tokens = { "METAR", "KTTN", "051853Z", "04011KT", "1 1/2SM", "VCTS", "SN", "FZFG", "BKN003", "OVC010", "M02/M02", "A3006", "RMK", "AO2", "TSB40", "SLP176", "P0002", "T10171017" };
        // WHEN tokenizing the string
        String[] result = getParser().tokenize(code);
        // THEN the visibility part is 1 1/2SM
        assertNotNull(result);
        assertArrayEquals(tokens, result);

    }


}
