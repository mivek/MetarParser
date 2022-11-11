package io.github.mivek.command.taf;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * @author Jean-Kevin KPADEY
 */
class TAFCommandSupplierTest {

  @Test
  void testBuildCommandList() {
    List<Command> commands = new TAFCommandSupplier().buildCommandList();

    assertNotNull(commands);
    MatcherAssert.assertThat(commands, Matchers.hasSize(2));
  }
}
