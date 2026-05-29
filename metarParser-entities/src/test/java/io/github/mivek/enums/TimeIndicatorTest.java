package io.github.mivek.enums;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mivek
 */
class TimeIndicatorTest {

    @Test
    void testToStringWithMultipleLocale() {
        assertEquals("De", TimeIndicator.FM.toString(Locale.FRANCE));
        assertEquals("From", TimeIndicator.FM.toString(Locale.ENGLISH));
    }
}
