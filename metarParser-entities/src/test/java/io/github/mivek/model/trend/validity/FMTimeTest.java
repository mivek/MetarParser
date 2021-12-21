package io.github.mivek.model.trend.validity;

import io.github.mivek.enums.TimeIndicator;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FMTimeTest {

    @Test
    void testToString() {
        FMTime sut = new FMTime();
        sut.setTime(LocalTime.of(12, 15));
        String des = sut.toString();
        assertEquals(TimeIndicator.FM + " 12:15", des);
    }
}
