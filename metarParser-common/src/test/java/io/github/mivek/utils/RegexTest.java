package io.github.mivek.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {

    @Test
    public void testPregMatchSuccess() {
        Pattern regex = Pattern.compile("(a((b)(c)))");
        String input = "abc";

        String[] res = Regex.pregMatch(regex, input);

        assertThat(res, is(not(emptyArray())));
        assertThat(res, arrayWithSize(5));
        assertThat(res, arrayContainingInAnyOrder("abc", "abc", "bc", "b", "c"));
    }

    @Test
    public void testPregMatchFail() {
        Pattern regex = Pattern.compile("(a((b)(c)))");
        String input = "";

        String[] res = Regex.pregMatch(regex, input);

        assertThat(res, is(emptyArray()));
    }

    @Test
    public void testFind() {
        Pattern regex = Pattern.compile("^(R\\d{2}\\w?\\/)");
        String input1 = "R26/0550V700U";
        String input2 = "Random string";

        assertTrue(Regex.find(regex, input1));
        assertFalse(Regex.find(regex, input2));
    }
}
