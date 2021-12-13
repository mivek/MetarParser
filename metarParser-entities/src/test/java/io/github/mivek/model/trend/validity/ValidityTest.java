package io.github.mivek.model.trend.validity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import io.github.mivek.internationalization.Messages;
import org.junit.jupiter.api.Test;

public class ValidityTest {
  @Test
  public void testToString() {
    Validity sut = new Validity();
    sut.setStartDay(10);
    sut.setStartHour(15);
    sut.setEndDay(12);
    sut.setEndHour(16);

    String desc = sut.toString();

    assertThat(
        desc, containsString(Messages.getInstance().getString("ToString.start.day.month") + "=10"));
    assertThat(
        desc, containsString(Messages.getInstance().getString("ToString.start.hour.day") + "=15"));
    assertThat(
        desc, containsString(Messages.getInstance().getString("ToString.end.day.month") + "=12"));
    assertThat(
        desc, containsString(Messages.getInstance().getString("ToString.end.hour.day") + "=16"));
  }
}
