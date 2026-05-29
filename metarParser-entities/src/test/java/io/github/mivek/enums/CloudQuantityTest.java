package io.github.mivek.enums;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mivek
 */
class CloudQuantityTest {

    @Test
    void testToStringMultipleLocale() {
        assertEquals("peu", CloudQuantity.FEW.toString(Locale.FRENCH));
        assertEquals("few", CloudQuantity.FEW.toString(Locale.ENGLISH));
    }
}
