package io.github.mivek.command.metar;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author mivek
 */
public class MetarCommandSupplierTest {

    @Test
    public void testBuildCommandList() {
        List<Command> commands = new MetarCommandSupplier().buildCommandList();

        assertNotNull(commands);
        assertThat(commands, Matchers.hasSize(4));
    }
}
