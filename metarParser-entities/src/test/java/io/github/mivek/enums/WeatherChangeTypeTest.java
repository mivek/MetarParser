package io.github.mivek.enums;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mivek
 */
class WeatherChangeTypeTest {

    @Test
    void testToStringWithMultipleLocale() {
        assertEquals("Devenant", WeatherChangeType.BECMG.toString(Locale.FRANCE));
        assertEquals("Becoming", WeatherChangeType.BECMG.toString(Locale.ENGLISH));
    }
}
