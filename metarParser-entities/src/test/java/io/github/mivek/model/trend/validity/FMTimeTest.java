package io.github.mivek.model.trend.validity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.mivek.enums.TimeIndicator;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

public class FMTimeTest {

  @Test
  public void testToString() {
    FMTime sut = new FMTime();
    sut.setTime(LocalTime.of(12, 15));
    String des = sut.toString();
    assertEquals(TimeIndicator.FM + " 12:15", des);
  }
}
