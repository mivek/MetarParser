package io.github.mivek.parser.command.common;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author mivek
 */
public class CommonCommandSupplierTest {

    @Test public void testBuildCommands() {
        List<Command> commands = new CommonCommandSupplier().buildCommands();

        assertNotNull(commands);
        assertThat(commands, Matchers.hasSize(8));
    }
}
