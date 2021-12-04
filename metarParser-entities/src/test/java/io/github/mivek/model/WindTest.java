package io.github.mivek.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import io.github.mivek.internationalization.Messages;
import org.junit.jupiter.api.Test;

public class WindTest {

  @Test
  public void testToString() {
    Wind sut = new Wind();

    sut.setDirection(Messages.getInstance().getString("Converter.NE"));
    sut.setDirectionDegrees(45);
    sut.setSpeed(25);
    sut.setUnit("KM/H");

    String desc = sut.toString();

    assertThat(
        desc, containsString(Messages.getInstance().getString("ToString.wind.speed") + "=25"));
    assertThat(
        desc,
        containsString(
            Messages.getInstance().getString("ToString.wind.direction")
                + "="
                + Messages.getInstance().getString("Converter.NE")));
    assertThat(
        desc,
        containsString(
            Messages.getInstance().getString("ToString.wind.direction.degrees") + "=45"));
    assertThat(
        desc, containsString(Messages.getInstance().getString("ToString.wind.gusts") + "=0"));
    assertThat(
        desc,
        containsString(Messages.getInstance().getString("ToString.wind.min.variation") + "=0"));
    assertThat(
        desc,
        containsString(Messages.getInstance().getString("ToString.wind.max.variation") + "=0"));
    assertThat(
        desc, containsString(Messages.getInstance().getString("ToString.wind.unit") + "=KM/H"));
  }
}
