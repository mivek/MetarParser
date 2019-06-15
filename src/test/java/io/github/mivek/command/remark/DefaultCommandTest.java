package io.github.mivek.command.remark;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author mivek
 */
public class DefaultCommandTest {

    @Test public void canParse() {
        assertTrue(new DefaultCommand().canParse(""));
    }
}
