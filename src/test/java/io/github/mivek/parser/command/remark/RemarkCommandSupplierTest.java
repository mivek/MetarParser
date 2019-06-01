package io.github.mivek.parser.command.remark;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author mivek
 */
public class RemarkCommandSupplierTest {

    @Test public void testBuildCommandList() {
        List<Command> commands = new RemarkCommandSupplier().buildCommandList();

        assertNotNull(commands);
        assertThat(commands, hasSize(25));
    }

}
