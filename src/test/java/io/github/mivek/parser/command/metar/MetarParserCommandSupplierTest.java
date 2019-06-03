package io.github.mivek.parser.command.metar;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author mivek
 */
public class MetarParserCommandSupplierTest {

    @Test public void testBuildCommandList() {
        List<Command> commands = new MetarParserCommandSupplier().buildCommandList();

        assertNotNull(commands);
        assertThat(commands, Matchers.hasSize(4));
    }
}
