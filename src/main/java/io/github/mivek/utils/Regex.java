package io.github.mivek.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex utility class.
 * @author mivek
 */
public final class Regex {

    /**
     * Private constructor.
     */
    private Regex() {
    }

    /**
     * Similar to PHP function preg_match. Search a match between the pattern
     * (regex) and the subject (input) and returns an array of string results.
     * @param pPattern The pattern
     * @param pInput
     * The subject.
     * @return Array of matches.
     */
    public static String[] pregMatch(final Pattern pPattern, final String pInput) {
        Matcher m = init(pPattern, pInput);

        if (m.find()) {
            int length = m.groupCount() + 1;
            String[] matches = new String[length];
            for (int i = 0; i < length; i++) {
                matches[i] = m.group(i);
            }
            return matches;
        }
        return new String[0];
    }

    /**
     * Tries to match the regex and the input.
     * @param pPattern The compiled pattern
     * @param pInput
     * the input to test.
     * @return true if the input matches the regex.
     */
    public static boolean find(final Pattern pPattern, final String pInput) {
        Matcher m = init(pPattern, pInput);
        return m.find();
    }

    /**
     * Returns the subsequence captured if the regex and the input matches.
     * @param pPattern The compiled pattern.
     * @param pInput
     * The input string
     * @return the finding string.
     */
    public static String findString(final Pattern pPattern, final String pInput) {
        Matcher m = init(pPattern, pInput);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    /**
     * Initiate the pattern and the matcher.
     * @param pPattern the compiled pattern regex.
     * @param pInput the input to test.
     * The input to test.
     */
    private static Matcher init(final Pattern pPattern, final String pInput) {
        return pPattern.matcher(pInput);
    }

    /**
     * Checks if the input matches the regex.
     * @param pPattern the compiled pattern regex.
     * @param pInput the input to test
     * @return true if the input matches the regex.
     */
    public static boolean match(final Pattern pPattern, final String pInput) {
        Matcher m = init(pPattern, pInput);
        return m.matches();
    }
}
