package io.github.mivek.utils;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import io.github.mivek.internationalization.Messages;

@RunWith(Parameterized.class)
public class DegreesToDirectionTest {
    private final String direction;
    private final String degrees;

    public DegreesToDirectionTest(final String pDirection, final String pDegrees) {
        direction = pDirection;
        degrees = pDegrees;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { "Converter.E", "80" },
            { "Converter.NNE", "30" },
            { "Converter.SSW", "200" },
            { "Converter.W", "280" },
            { "Converter.WNW", "300"},
            { "Converter.SE", "130" },
            { "Converter.SW", "230" },
            { "Converter.N", "2" },
            { "Converter.NNW", "345" },
            { "Converter.N", "350" },
            {"Converter.VRB","anyString"}
        });
    }

    @Test
    public void testDegreesToDirection() {
        assertEquals(Messages.getInstance().getString(direction), Converter.degreesToDirection(degrees));
    }
}
