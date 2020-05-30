package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * @author mivek
 */
public class DescriptiveTest {

    @Test
    public void testToStringWithMultipleLocale() {
        Messages.getInstance().setLocale(Locale.FRANCE);
        assertEquals("averses de", Descriptive.SHOWERS.toString());

        Messages.getInstance().setLocale(Locale.ENGLISH);
        assertEquals("showers of", Descriptive.SHOWERS.toString());
    }
}
