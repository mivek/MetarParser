package io.github.mivekinternationalization;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

import io.github.mivekinternationalization.Messages;

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
        // When Changing the locale to german.
        Messages.getInstance().setLocale(Locale.GERMAN);
        // Then the message is in german.
        assertEquals("gering", Messages.getInstance().getCloudQuantityFEW());
    }
}
