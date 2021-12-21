package io.github.mivek.command.remark;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author mivek
 */
class RemarkCommandSupplierTest {

    @Test
    void testBuildCommandList() {
        List<Command> commands = new RemarkCommandSupplier().buildCommandList();

        assertNotNull(commands);
        assertThat(commands, hasSize(39));
    }

}
