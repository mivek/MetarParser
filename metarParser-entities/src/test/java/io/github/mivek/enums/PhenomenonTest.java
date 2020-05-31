package io.github.mivek.enums;

import io.github.mivek.internationalization.Messages;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

/**
 * @author mivek
 */
public class PhenomenonTest {

    @Test
    public void testToStringWithMultipleLocale() {
        Messages.getInstance().setLocale(Locale.FRANCE);
        assertEquals("pluie", Phenomenon.RAIN.toString());

        Messages.getInstance().setLocale(Locale.ENGLISH);
        assertEquals("rain", Phenomenon.RAIN.toString());
    }
}
