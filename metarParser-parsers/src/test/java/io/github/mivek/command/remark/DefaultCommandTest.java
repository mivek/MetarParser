package io.github.mivek.command.remark;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author mivek
 */
class DefaultCommandTest {

    @Test
    void canParse() {
        assertTrue(new DefaultCommand().canParse(""));
    }
}
