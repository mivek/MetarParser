package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mivek
 */
class TimeIndicatorTest {

    @Test
    void testToStringWithMultipleLocale() {
        Messages.getInstance().setLocale(Locale.FRANCE);
        assertEquals("De", TimeIndicator.FM.toString());

        Messages.getInstance().setLocale(Locale.ENGLISH);
        assertEquals("From", TimeIndicator.FM.toString());
    }
}
