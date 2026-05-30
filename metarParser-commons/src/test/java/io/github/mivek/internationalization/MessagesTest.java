package io.github.mivek.internationalization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MessagesTest {

    @Test
    @SuppressWarnings("java:S1874")
    void testSetLocale() {
        Messages.getInstance().setLocale(Locale.FRENCH);
        assertEquals("peu", Messages.getInstance().getString("CloudQuantity.FEW"));
        Messages.getInstance().setLocale(Locale.ENGLISH);
        assertEquals("few", Messages.getInstance().getString("CloudQuantity.FEW"));
        Messages.getInstance().clearLocale();
    }

    @Test
    @SuppressWarnings("java:S1874")
    void testClearLocale() {
        Messages.getInstance().setLocale(Locale.FRENCH);
        assertEquals("peu", Messages.getInstance().getString("CloudQuantity.FEW"));
        Messages.getInstance().clearLocale();
        assertDoesNotThrow(() -> Messages.getInstance().getString("CloudQuantity.FEW"));
    }

    @Test
    void testGetStringWithArgs() {
        assertEquals("ceiling varying between 5 and 15 feet",
            Messages.getInstance().getString("Remark.Ceiling.Height", 5, 15));
    }

    @Test
    void testGetStringWithLocale() {
        assertEquals("peu", Messages.getInstance().getString(Locale.FRENCH, "CloudQuantity.FEW"));
        assertEquals("few", Messages.getInstance().getString(Locale.ENGLISH, "CloudQuantity.FEW"));
    }

    @Test
    void testGetStringWithLocaleAndArgs() {
        assertEquals("variation du plafond entre 5 et 15 pieds",
            Messages.getInstance().getString(Locale.FRENCH, "Remark.Ceiling.Height", 5, 15));
        assertEquals("ceiling varying between 5 and 15 feet",
            Messages.getInstance().getString(Locale.ENGLISH, "Remark.Ceiling.Height", 5, 15));
    }

    @Test
    void testMissingKeyReturnsKey() {
        Messages messages = Messages.getInstance();
        assertThrows(MissingResourceException.class, () -> messages.getString("NonExistent.Key"));
        assertEquals("NonExistent.Key", Messages.getInstance().getString(Locale.FRENCH, "NonExistent.Key"));
    }

    @Test
    void testMissingKeyWithArgsReturnsKey() {
        Messages messages = Messages.getInstance();
        assertThrows(MissingResourceException.class, () -> messages.getString("NonExistent.Key", "arg1"));
        assertEquals("NonExistent.Key", Messages.getInstance().getString(Locale.FRENCH, "NonExistent.Key", "arg1"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"messages_de", "messages_es", "messages_fr", "messages_it",
        "messages_pl_PL", "messages_ru_RU", "messages_tr_TR", "messages_zh_CN"})
    void testLocaleContainsAllBaseKeys(final String bundleName) throws IOException {
        Properties base = loadProperties("internationalization/messages.properties");
        Properties locale = loadProperties("internationalization/" + bundleName + ".properties");
        Set<Object> baseKeys = base.keySet();
        for (Object key : baseKeys) {
            assertTrue(locale.containsKey(key),
                "Locale bundle '" + bundleName + "' is missing key: " + key);
        }
    }

    private Properties loadProperties(final String resourcePath) throws IOException {
        Properties props = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
             InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            props.load(reader);
        }
        return props;
    }
}
