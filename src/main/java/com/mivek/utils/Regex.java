package com.mivek.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Regex {
	private static Pattern p;
	private static Matcher m;
	
	private Regex() {
	}
	public static String[] preg_match(String regex, String input) {
		init(regex, input);
		
		if(m.matches()) {
			int length = m.groupCount()+1;
			String[] matches = new String[length];
			for(int i = 0; i < length; i++) {
				matches[i] = m.group(i);
			}
			return matches;
		}
		return null;
	}
	
	public static boolean find(String regex, String input) {
		init(regex, input);
		return m.find();
	}
	
	public static String findString(String regex, String input) {
		if (find(regex, input)) {
			return m.group(1);
		}
		return null;
	}
	private static void init(String regex, String input) {
		p = Pattern.compile(regex);
		m = p.matcher(input);
	}
}
