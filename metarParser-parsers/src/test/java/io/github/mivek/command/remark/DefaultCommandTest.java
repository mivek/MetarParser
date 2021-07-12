package io.github.mivek.command.remark;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author mivek
 */
public class DefaultCommandTest {

    @Test
    public void canParse() {
        assertTrue(new DefaultCommand().canParse(""));
    }
}
