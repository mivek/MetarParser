package io.github.mivek.utils;

import io.github.mivek.internationalization.Messages;

import java.time.LocalTime;

/**
 * This class is used to convert data.
 *
 * @author mivek
 */
public final class Converter {

    /** North East minimal degrees. */
    private static final double NORTH_EAST_MIN = 22.5;
    /** North east maximal degrees. */
    private static final double NORTH_EAST_MAX = 67.5;
    /** East degrees. */
    private static final double EAST = 112.5;
    /** South East degrees. */
    private static final double SOUTH_EAST = 157.5;
    /** South degrees. */
    private static final double SOUTH = 202.5;
    /** North West degrees. */
    private static final double NORTH_WEST = 337.5;
    /** West degrees. */
    private static final double WEST = 292.5;
    /** South west degrees. */
    private static final double SOUTH_WEST = 247.5;

    /**
     * Private constructor.
     */
    private Converter() {
    }

    /**
     * This method converter degrees to direction.
     *
     * @param degreesStr a string representing the degrees.
     * @return A string for the direction.
     */
    public static String degreesToDirection(final String degreesStr) {
        double degrees;
        String res;
        try {
            degrees = Double.parseDouble(degreesStr);
        } catch (NumberFormatException e) {
            return Messages.getInstance().getString("Converter.VRB");
        }

        if (isBetween(degrees, NORTH_EAST_MIN, SOUTH)) {
            if (isBetween(degrees, NORTH_EAST_MIN, NORTH_EAST_MAX)) {
                res = Messages.getInstance().getString("Converter.NE");
            } else if (isBetween(degrees, NORTH_EAST_MAX, EAST)) {
                res = Messages.getInstance().getString("Converter.E");
            } else if (isBetween(degrees, EAST, SOUTH_EAST)) {
                res = Messages.getInstance().getString("Converter.SE");
            } else {
                res = Messages.getInstance().getString("Converter.S");
            }
        } else {
            if (isBetween(degrees, SOUTH, SOUTH_WEST)) {
                res = Messages.getInstance().getString("Converter.SW");
            } else if (isBetween(degrees, SOUTH_WEST, WEST)) {
                res = Messages.getInstance().getString("Converter.W");
            } else if (isBetween(degrees, WEST, NORTH_WEST)) {
                res = Messages.getInstance().getString("Converter.NW");
            } else {
                res = Messages.getInstance().getString("Converter.N");
            }
        }
        return res;
    }

    /**
     * Checks if num is between lower and max.
     *
     * @param num   double to test
     * @param lower the minimum value, included.
     * @param max   The maximum value, exluded.
     * @return true if num is between lower and max, false otherwise.
     */
    static boolean isBetween(final double num, final double lower, final double max) {
        return lower <= num && max > num;
    }

    /**
     * Converts a string representing the visibility into a real visibility.
     *
     * @param input A string.
     * @return a string correctly formatted.
     */
    public static String convertVisibility(final String input) {
        if ("9999".equals(input)) { //$NON-NLS-1$
            return ">10km"; //$NON-NLS-1$
        } else {
            return Integer.parseInt(input) + "m"; //$NON-NLS-1$
        }
    }

    /**
     * Converts the metar part of temperature into integer.
     *
     * @param input The metar part of the temperature.
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
     * @param input Single character string representing the trend.
     * @return The signification of the single caracter.
     */
    public static String convertTrend(final String input) {
        if ("U".equals(input)) { //$NON-NLS-1$
            return Messages.getInstance().getString("Converter.U");
        } else if ("D".equals(input)) { //$NON-NLS-1$
            return Messages.getInstance().getString("Converter.D");
        } else if ("N".equals(input)) { //$NON-NLS-1$
            return Messages.getInstance().getString("Converter.NSC");
        }
        return ""; //$NON-NLS-1$
    }

    /**
     * Converts inches of mercury pressure into hecto pascals.
     *
     * @param inchesMercury string of mercury.
     * @return double of the pressure in Pascals.
     */
    public static double inchesMercuryToHPascal(final double inchesMercury) {
        return 33.8639 * inchesMercury;
    }

    /**
     * Converts a string representing a time to a LocalTime.
     * Eg: "0830" returns a LocalTime of 08:30.
     *
     * @param input the string to convert.
     * @return the corresponding time.
     */
    public static LocalTime stringToTime(final String input) {
        int hours = Integer.parseInt(input.substring(0, 2));
        int minutes = Integer.parseInt(input.substring(2));
        return LocalTime.of(hours, minutes);
    }
}
