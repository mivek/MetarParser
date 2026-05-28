package io.github.mivek.enums;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mivek
 */
class PhenomenonTest {

    @Test
    void testToStringWithMultipleLocale() {
        assertEquals("pluie", Phenomenon.RAIN.toString(Locale.FRANCE));
        assertEquals("rain", Phenomenon.RAIN.toString(Locale.ENGLISH));
    }
}
