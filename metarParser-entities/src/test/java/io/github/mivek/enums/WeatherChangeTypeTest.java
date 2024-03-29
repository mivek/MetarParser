package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mivek
 */
class WeatherChangeTypeTest {

    @Test
    void testToStringWithMultipleLocale() {
        Messages.getInstance().setLocale(Locale.FRANCE);
        assertEquals("Devenant", WeatherChangeType.BECMG.toString());

        Messages.getInstance().setLocale(Locale.ENGLISH);
        assertEquals("Becoming", WeatherChangeType.BECMG.toString());
    }
}
