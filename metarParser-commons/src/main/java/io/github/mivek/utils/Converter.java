package io.github.mivek.utils;

import io.github.mivek.internationalization.Messages;

import java.time.LocalTime;

/**
 * This class is used to convert data.
 *
 * @author mivek
 */
public final class Converter {
    /**
     * Arrays of cardinal directions.
     */
    private static final String[] DIRECTIONS = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE",
            "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};

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
        try {
            degrees = Double.parseDouble(degreesStr);
        } catch (NumberFormatException e) {
            return Messages.getInstance().getString("Converter.VRB");
        }

        return Messages.getInstance().getString("Converter." + DIRECTIONS[(int) ((degrees + 11.5) / 22.5) % DIRECTIONS.length]);
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
     * @return The signification of the single character.
     */
    public static String convertTrend(final String input) {
        if ("U".equals(input) || "D".equals(input)) {
            return Messages.getInstance().getString("Converter." + input);
        } else if ("N".equals(input)) {
            return Messages.getInstance().getString("Converter.NSC");
        }
        return "";
    }

    /**
     * Converts the indicator for the runway visual range.
     * @param input Single character representing the indicator
     * @return Signification of the indicator or empty string.
     */
    public static String convertIndicator(final String input) {
        if ("M".equals(input) || "P".equals(input)) {
            return  Messages.getInstance().getString("Indicator." + input);
        }
        return "";
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

    /**
     * Converts a string representing a precipitation amount in inches.
     * @param input The string to convert
     * @return The actual amount.
     */
    public static float convertPrecipitationAmount(final String input) {
        return Float.parseFloat(input) / 100;
    }
}
