package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mivek
 */
class CloudQuantityTest {

    @Test
    void testToStringMultipleLocale() {
        Messages.getInstance().setLocale(Locale.FRANCE);
        assertEquals("peu", CloudQuantity.FEW.toString());

        Messages.getInstance().setLocale(Locale.ENGLISH);
        assertEquals("few", CloudQuantity.FEW.toString());
    }
}
