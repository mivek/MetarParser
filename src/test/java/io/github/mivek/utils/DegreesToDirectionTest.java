package io.github.mivek.utils;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import io.github.mivekinternationalization.Messages;

@RunWith(Parameterized.class)
public class DegreesToDirectionTest {
    private String direction;
    private String degrees;

    public DegreesToDirectionTest(final String pDirection, final String pDegrees) {
        direction = pDirection;
        degrees = pDegrees;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { "Converter.E", "80" },
            { "Converter.NE", "30" },
            { "Converter.S", "200" },
            { "Converter.W", "280" },
            { "Converter.NW", "300"},
            { "Converter.SE", "130" },
            { "Converter.SW", "230" },
            { "Converter.N", "2" },
            { "Converter.N", "345" },
            {"Converter.VRB","anyString"}
        });
    }
    @Test
    public void testDegreesToDirection() {
        assertEquals(Messages.getInstance().getString(direction), Converter.degreesToDirection(degrees));
    }
}
