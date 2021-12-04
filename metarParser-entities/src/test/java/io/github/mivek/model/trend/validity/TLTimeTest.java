package io.github.mivek.model.trend.validity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.mivek.enums.TimeIndicator;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

public class TLTimeTest {
  @Test
  public void testToString() {
    TLTime sut = new TLTime();
    sut.setTime(LocalTime.of(12, 15));

    String desc = sut.toString();

    assertEquals(TimeIndicator.TL + " 12:15", desc);
  }
}
