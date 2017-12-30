package com.mivek.utils;

import i18n.Messages;

/**
 * This class is used to convert data.
 * @author mivek
 *
 */
public final class Converter {

	/**
	 * North East minimal degrees.
	 */
	protected static final double NORTH_EAST_MIN = 22.5;
	/**
	 * North east maximal degrees.
	 */
	protected static final double NORTH_EAST_MAX = 67.5;
	/**
	 * East degrees.
	 */
	protected static final double EAST = 112.5;
	/**
	 * South East degrees.
	 */
	protected static final double SOUTH_EAST = 157.5;
	/**
	 * South degrees.
	 */
	protected static final double SOUTH = 202.5;
	/**
	 * North West degrees.
	 */
	protected static final double NORTH_WEST = 337.5;
	/**
	 * West degrees.
	 */
	protected static final double WEST = 292.5;
	/**
	 * South west degrees.
	 */
	protected static final double SOUTH_WEST = 247.5;

	/**
	 * Private constructor.
	 */
	private Converter() { }

	/**
	 * This method converter degrees to direction.
	 * @param degreesStr a string representing the degrees.
	 * @return A string for the direction.
	 */
	public static String degreesToDirection(final String degreesStr) {
		double degrees = 0;
		String res = "";
		try {
			degrees = Double.parseDouble(degreesStr);
		} catch (NumberFormatException e) {
			return Messages.CONVERTER_VRB;
		}

		if (isBetween(degrees, NORTH_EAST_MIN, NORTH_EAST_MAX)) {
			res = Messages.CONVERTER_NE;
		} else if (isBetween(degrees, NORTH_EAST_MAX, EAST)) {
			res = Messages.CONVERTER_E;
		} else if (isBetween(degrees, EAST, SOUTH_EAST)) {
			res = Messages.CONVERTER_SE;
		} else if (isBetween(degrees, SOUTH_EAST, SOUTH)) {
			res = Messages.CONVERTER_S;
		} else if (isBetween(degrees, SOUTH, SOUTH_WEST)) {
			res = Messages.CONVERTER_SW;
		} else if (isBetween(degrees, SOUTH_WEST, WEST)) {
			res = Messages.CONVERTER_W;
		} else if (isBetween(degrees, WEST, NORTH_WEST)) {
			res = Messages.CONVERTER_NW;
		} else {
			res = Messages.CONVERTER_N;
		}
		return res;
	}

	/**
	 * Checks if num is between lower and max.
	 *
	 * @param num
	 *            double to test
	 * @param lower
	 *            the minimum value, included.
	 * @param max
	 *            The maximum value, exluded.
	 * @return true if num is between lower and max, false otherwise.
	 */
	public static boolean isBetween(final double num, final double lower, final double max) {
		return lower <= num && max > num;
	}

	/**
	 * Converts a string representing the visibility into a real visibility.
	 *
	 * @param input
	 *            A string.
	 * @return a string correctly formatted.
	 */
	public static String convertVisibility(final String input) {
		if (input.equals("9999")) { //$NON-NLS-1$
			return ">10km"; //$NON-NLS-1$
		} else {
			return Integer.parseInt(input) + "m"; //$NON-NLS-1$
		}
	}

	/**
	 * Converts the metar part of temperature into integer.
	 *
	 * @param input
	 *            The metar part of the temperature.
	 * @return an integer of the temperature.
	 */
	public static int convertTemperature(final String input) {
		if (input.startsWith("M")) { //$NON-NLS-1$
			return -(Integer.parseInt(input.substring(1, 3)));
		} else {
			return Integer.parseInt(input);
		}
	}

	/**
	 * Converts the trend of clouds.
	 *
	 * @param input
	 *            Single character string representing the trend.
	 * @return The signification of the single caracter.
	 */
	public static String convertTrend(final String input) {
		if (input.equals("U")) { //$NON-NLS-1$
			return Messages.CONVERTER_U;
		} else if (input.equals("D")) { //$NON-NLS-1$
			return Messages.CONVERTER_D;
		} else if (input.equals("N")) { //$NON-NLS-1$
			return Messages.CONVERTER_NSC;
		}
		return ""; //$NON-NLS-1$
	}


	/**
	 * Converts mercury pressure into pascals.
	 *
	 * @param input
	 *            string of mercury.
	 * @return double of the pressure in Pascals.
	 */
	public static double mercuryToPascal(final String input) {
		return (3386 * (Double.parseDouble(input) / 100)) / 100;
	}
}
