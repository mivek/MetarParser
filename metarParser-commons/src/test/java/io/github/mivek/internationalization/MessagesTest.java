package io.github.mivek.internationalization;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

public class MessagesTest {

    @Test
    public void testSetLocale() {
        // Given a french locale
        Messages.getInstance().setLocale(Locale.FRENCH);
        assertEquals("peu", Messages.getInstance().getString("CloudQuantity.FEW"));
        // WHEN Changing the locale to english
        Messages.getInstance().setLocale(Locale.ENGLISH);
        // THEN The locale is changed and so is the message.
        assertEquals("few", Messages.getInstance().getString("CloudQuantity.FEW"));
        assertEquals("ceiling varying between 5 and 15 feet", Messages.getInstance().getString("Remark.Ceiling.Height", 5, 15));
    }
}
