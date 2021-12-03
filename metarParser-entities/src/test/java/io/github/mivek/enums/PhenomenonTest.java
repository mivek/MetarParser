package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mivek
 */
class PhenomenonTest {

    @Test
    void testToStringWithMultipleLocale() {
        Messages.getInstance().setLocale(Locale.FRANCE);
        assertEquals("pluie", Phenomenon.RAIN.toString());

        Messages.getInstance().setLocale(Locale.ENGLISH);
        assertEquals("rain", Phenomenon.RAIN.toString());
    }
}
