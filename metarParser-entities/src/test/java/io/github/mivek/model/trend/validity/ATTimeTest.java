package io.github.mivek.model.trend.validity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.mivek.enums.TimeIndicator;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

public class ATTimeTest {

  @Test
  public void testToString() {
    ATTime sut = new ATTime();
    sut.setTime(LocalTime.of(12, 15));
    String des = sut.toString();

    assertEquals(TimeIndicator.AT + " 12:15", des);
  }
}
