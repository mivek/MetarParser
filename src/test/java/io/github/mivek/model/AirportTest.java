package io.github.mivek.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AirportTest {
    private Airport sut;

    @Before
    public void init() {
        sut = new Airport();
    }

    @Test
    public void testEqual() {
        sut.setIcao("111");

        Airport other1 = new Airport();
        other1.setIcao("111");

        Airport other2 = new Airport();
        other2.setIcao("498");

        assertTrue(sut.equals(sut));
        assertTrue(sut.equals(other1));
        assertFalse(sut.equals(other2));
        assertFalse(sut.equals(null));
        assertFalse(sut.equals(new Object()));
    }
}
