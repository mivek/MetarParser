package io.github.mivek.enums;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mivek
 */
class DescriptiveTest {

    @Test
    void testToStringWithMultipleLocale() {
        assertEquals("averses de", Descriptive.SHOWERS.toString(Locale.FRANCE));
        assertEquals("showers of", Descriptive.SHOWERS.toString(Locale.ENGLISH));
    }
}
