package io.github.mivek.command.remark;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;

/** @author mivek */
public class RemarkCommandSupplierTest {

  @Test
  public void testBuildCommandList() {
    List<Command> commands = new RemarkCommandSupplier().buildCommandList();

    assertNotNull(commands);
    assertThat(commands, hasSize(39));
  }
}
