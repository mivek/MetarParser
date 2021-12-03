package io.github.mivek.command.metar;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author mivek
 */
class MetarCommandSupplierTest {

    @Test
    void testBuildCommandList() {
        List<Command> commands = new MetarCommandSupplier().buildCommandList();

        assertNotNull(commands);
        assertThat(commands, Matchers.hasSize(4));
    }
}
