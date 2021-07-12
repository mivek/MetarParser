package io.github.mivek.command.remark;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;

/**
 * @author mivek
 */
public class RemarkCommandSupplierTest {

    @Test
    public void testBuildCommandList() {
        List<Command> commands = new RemarkCommandSupplier().buildCommandList();

        assertNotNull(commands);
        assertThat(commands, hasSize(39));
    }

}
