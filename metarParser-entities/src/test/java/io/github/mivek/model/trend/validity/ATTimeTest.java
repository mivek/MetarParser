package io.github.mivek.model.trend.validity;

import io.github.mivek.enums.TimeIndicator;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class ATTimeTest {

    @Test
    public void testToString() {
        ATTime sut = new ATTime();
        sut.setTime(LocalTime.of(12, 15));
        String des = sut.toString();

        assertEquals(TimeIndicator.AT.toString() + " 12:15", des);
    }
}
