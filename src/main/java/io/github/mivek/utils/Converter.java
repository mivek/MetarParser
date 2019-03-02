package io.github.mivek.utils;

import java.time.LocalTime;

import io.github.mivekinternationalization.Messages;

/**
 * This class is used to convert data.
 * @author mivek
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
    private Converter() {
    }

    /**
     * This method converter degrees to direction.
     * @param pDegreesStr a string representing the degrees.
     * @return A string for the direction.
     */
    public static String degreesToDirection(final String pDegreesStr) {
        double degrees = 0;
        String res = "";
        try {
            degrees = Double.parseDouble(pDegreesStr);
        } catch (NumberFormatException e) {
            return Messages.getInstance().getConverterVRB();
        }

        if (isBetween(degrees, NORTH_EAST_MIN, SOUTH)) {
            if (isBetween(degrees, NORTH_EAST_MIN, NORTH_EAST_MAX)) {
                res = Messages.getInstance().getConverterNE();
            } else if (isBetween(degrees, NORTH_EAST_MAX, EAST)) {
                res = Messages.getInstance().getConverterE();
            } else if (isBetween(degrees, EAST, SOUTH_EAST)) {
                res = Messages.getInstance().getConverterSE();
            } else {
                res = Messages.getInstance().getConverterS();
            }
        } else {
            if (isBetween(degrees, SOUTH, SOUTH_WEST)) {
                res = Messages.getInstance().getConverterSW();
            } else if (isBetween(degrees, SOUTH_WEST, WEST)) {
                res = Messages.getInstance().getConverterW();
            } else if (isBetween(degrees, WEST, NORTH_WEST)) {
                res = Messages.getInstance().getConverterNW();
            } else {
                res = Messages.getInstance().getConverterN();
            }
        }
        return res;
    }

    /**
     * Checks if num is between lower and max.
     * @param pNum
     * double to test
     * @param pLower
     * the minimum value, included.
     * @param pMax
     * The maximum value, exluded.
     * @return true if num is between lower and max, false otherwise.
     */
    public static boolean isBetween(final double pNum, final double pLower, final double pMax) {
        return pLower <= pNum && pMax > pNum;
    }

    /**
     * Converts a string representing the visibility into a real visibility.
     * @param pInput
     * A string.
     * @return a string correctly formatted.
     */
    public static String convertVisibility(final String pInput) {
        if ("9999".equals(pInput)) { //$NON-NLS-1$
            return ">10km"; //$NON-NLS-1$
        } else {
            return Integer.parseInt(pInput) + "m"; //$NON-NLS-1$
        }
    }

    /**
     * Converts the metar part of temperature into integer.
     * @param pInput The metar part of the temperature.
     * @return an integer of the temperature.
     */
    public static int convertTemperature(final String pInput) {
        if (pInput.startsWith("M")) { //$NON-NLS-1$
            return -(Integer.parseInt(pInput.substring(1, 3)));
        } else {
            return Integer.parseInt(pInput);
        }
    }

    /**
     * Converts the trend of clouds.
     * @param pInput Single character string representing the trend.
     * @return The signification of the single caracter.
     */
    public static String convertTrend(final String pInput) {
        if ("U".equals(pInput)) { //$NON-NLS-1$
            return Messages.getInstance().getConverterU();
        } else if ("D".equals(pInput)) { //$NON-NLS-1$
            return Messages.getInstance().getConverterD();
        } else if ("N".equals(pInput)) { //$NON-NLS-1$
            return Messages.getInstance().getConverterNSC();
        }
        return ""; //$NON-NLS-1$
    }

    /**
     * Converts inches of mercury pressure into hecto pascals.
     * @param pInchesMercury string of mercury.
     * @return double of the pressure in Pascals.
     */
    public static double inchesMercuryToHPascal(final double pInchesMercury) {
        return 33.8639 * pInchesMercury;
    }

    /**
     * Converts a string representing a time to a LocalTime.
     * Eg: "0830" returns a LocalTime of 08:30.
     * @param pInput the string to convert.
     * @return the corresponding time.
     */
    public static LocalTime stringToTime(final String pInput) {
        int hours = Integer.parseInt(pInput.substring(0, 2));
        int minutes = Integer.parseInt(pInput.substring(2));
        return LocalTime.of(hours, minutes);
    }
}
