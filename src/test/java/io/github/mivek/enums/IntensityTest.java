package io.github.mivek.enums;

import org.junit.Test;

public class IntensityTest {

    @Test(expected = IllegalArgumentException.class)
    public void testGetEnum() {
        Intensity.getEnum("QWERTY");
    }
}
