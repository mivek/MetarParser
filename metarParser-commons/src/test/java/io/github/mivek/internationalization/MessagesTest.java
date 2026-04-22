package io.github.mivek.internationalization;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MessagesTest {

    @Test
    void testSetLocale() {
        // Given a french locale
        Messages.getInstance().setLocale(Locale.FRENCH);
        assertEquals("peu", Messages.getInstance().getString("CloudQuantity.FEW"));
        // WHEN Changing the locale to english
        Messages.getInstance().setLocale(Locale.ENGLISH);
        // THEN The locale is changed and so is the message.
        assertEquals("few", Messages.getInstance().getString("CloudQuantity.FEW"));
        assertEquals("ceiling varying between 5 and 15 feet", Messages.getInstance().getString("Remark.Ceiling.Height", 5, 15));
    }

    @Test
    void testClearLocale() {
        Messages.getInstance().setLocale(Locale.FRENCH);
        assertEquals("peu", Messages.getInstance().getString("CloudQuantity.FEW"));
        Messages.getInstance().clearLocale();
        // After clearing, the JVM default locale is used; the key must still be resolvable.
        assertDoesNotThrow(() -> Messages.getInstance().getString("CloudQuantity.FEW"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"messages_de", "messages_es", "messages_fr", "messages_it",
        "messages_pl_PL", "messages_ru_RU", "messages_tr_TR", "messages_zh_CN"})
    @Disabled("Requires all locale bundles to be complete and up-to-date with the base bundle")
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
