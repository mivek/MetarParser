package io.github.mivek.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import io.github.mivek.model.Airport;

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

        assertEquals(sut, sut);
        assertEquals(other1, sut);
        assertNotEquals(other2, sut);
        assertNotEquals(null, sut);
    }
}
