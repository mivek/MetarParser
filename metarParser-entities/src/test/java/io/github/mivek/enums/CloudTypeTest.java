package io.github.mivek.enums;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mivek
 */
class CloudTypeTest {

    @Test
    void testToStringWithMultipleLocale() {
        assertEquals("Cirrocumulus", CloudType.CC.toString(Locale.FRANCE));
        assertEquals("CirroCumulus", CloudType.CC.toString(Locale.ENGLISH));
    }
}
