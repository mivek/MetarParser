package io.github.mivek.command.remark;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** @author mivek */
public class DefaultCommandTest {

  @Test
  public void canParse() {
    assertTrue(new DefaultCommand().canParse(""));
  }
}
