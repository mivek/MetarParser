package io.github.mivek.command.common;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author mivek
 */
public class CommonCommandSupplierTest {

    @Test
    public void testBuildCommands() {
        List<Command> commands = new CommonCommandSupplier().buildCommands();

        assertNotNull(commands);
        assertThat(commands, Matchers.hasSize(8));
    }
}
