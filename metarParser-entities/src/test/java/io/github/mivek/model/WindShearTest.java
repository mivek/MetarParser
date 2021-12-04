package io.github.mivek.model;

import static org.hamcrest.MatcherAssert.assertThat;

import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class WindShearTest {

  @Test
  public void testToString() {
    WindShear sut = new WindShear();
    sut.setHeight(500);

    assertThat(
        sut.toString(),
        Matchers.containsString(Messages.getInstance().getString("ToString.height.feet") + "=500"));
  }
}
