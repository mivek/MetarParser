package com.mivek.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex utility class.
 *
 * @author mivek
 *
 */
public final class Regex {
	/**
	 * Matcher (input).
	 */
	private static Matcher m;

	/**
	 * Private constructor.
	 */
	private Regex() {
	}

	/**
	 * Similar to PHP function preg_match. Search a match between the pattern
	 * (regex) and the subject (input) and returns an array of string results.
	 *
	 * @param regex
	 *            The pattern
	 * @param input
	 *            The subject.
	 * @return Array of matches.
	 */
	public static String[] pregMatch(final String regex, final String input) {
		init(regex, input);

		if (m.matches()) {
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
	 *
	 * @param regex
	 *            The regex to test.
	 * @param input
	 *            the input to test.
	 * @return true if the input matches the regex.
	 */
	public static boolean find(final String regex, final String input) {
		init(regex, input);
		return m.find();
	}

	/**
	 * Returns the subsequence captured if the regex and the input matches.
	 *
	 * @param regex
	 *            The regex to test.
	 * @param input
	 *            The input string
	 * @return the finding string.
	 */
	public static String findString(final String regex, final String input) {
		if (find(regex, input)) {
			return m.group(1);
		}
		return null;
	}

	/**
	 * Initiate the pattern and the matcher.
	 *
	 * @param regex
	 *            The regex.
	 * @param input
	 *            The input to test.
	 */
	private static void init(final String regex, final String input) {
		Pattern p = Pattern.compile(regex);
		m = p.matcher(input);
	}

	public static boolean match(final String regex, final String input) {
		init(regex, input);
		return m.matches();
	}
}
