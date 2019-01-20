package internationalization;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

public class MessagesTest {

    @Test
    public void testSetLocalee() {
        // Given a french locale
        Messages.getInstance().setLocale(Locale.FRENCH);
        assertEquals("Peu", Messages.getInstance().getCloudQuantityFEW());
        // WHEN Changing the locale to english
        Messages.getInstance().setLocale(Locale.ENGLISH);
        // THEN The locale is changed and so is the message.
        assertEquals("Few", Messages.getInstance().getCloudQuantityFEW());
    }
}
