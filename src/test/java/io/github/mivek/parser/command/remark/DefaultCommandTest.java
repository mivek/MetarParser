package io.github.mivek.parser.command.remark;

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
