package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

/**
 * @author mivek
 */
public class CloudQuantityTest {

    @Test
    public void testToStringMultipleLocale() {
        Messages.getInstance().setLocale(Locale.FRANCE);
        assertEquals("peu", CloudQuantity.FEW.toString());

        Messages.getInstance().setLocale(Locale.ENGLISH);
        assertEquals("few", CloudQuantity.FEW.toString());
    }
}
