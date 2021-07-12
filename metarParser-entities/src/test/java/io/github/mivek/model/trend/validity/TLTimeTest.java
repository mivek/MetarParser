package io.github.mivek.model.trend.validity;

import io.github.mivek.enums.TimeIndicator;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class TLTimeTest {
    @Test
    public void testToString() {
        TLTime sut = new TLTime();
        sut.setTime(LocalTime.of(12, 15));

        String desc = sut.toString();

        assertEquals(TimeIndicator.TL + " 12:15", desc);
    }
}
