package io.github.mivek.command.common;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/** @author mivek */
public class CommonCommandSupplierTest {

  @Test
  public void testBuildCommands() {
    List<Command> commands = new CommonCommandSupplier().buildCommands();

    assertNotNull(commands);
    assertThat(commands, Matchers.hasSize(8));
  }
}
