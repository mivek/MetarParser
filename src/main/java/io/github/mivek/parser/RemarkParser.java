package io.github.mivek.parser;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.MissingResourceException;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class RemarkParser {
    /** The instance of the parser. */
    private static final RemarkParser INSTANCE = new RemarkParser();
    /** The logger instance. */
    private static final Logger LOGGER = Logger.getLogger(RemarkParser.class.getName());

    /** Wind peak pattern. */
    private static final Pattern WIND_PEAK = Pattern.compile("^PK WND (\\d{3})(\\d{2,3})\\/(\\d{2})?(\\d{2})");
    /** Wind shift pattern. */
    private static final Pattern WIND_SHIFT = Pattern.compile("^WSHFT (\\d{2})?(\\d{2})");
    /** Wind shift fopa pattern. */
    private static final Pattern WIND_SHIFT_FROPA = Pattern.compile("^WSHFT (\\d{2})?(\\d{2}) FROPA");
    /** Tower visibility. */
    private static final Pattern TOWER_VISIBILITY = Pattern.compile("^TWR VIS ((\\d)*( )?(\\d?\\/?\\d))");
    /** Surface visibility. */
    private static final Pattern SURFACE_VISIBILITY = Pattern.compile("^SFC VIS ((\\d)*( )?(\\d?\\/?\\d))");
    /** Variable prevailing visibility. */
    private static final Pattern VARIABLE_PREVAILING_VISIBILITY = Pattern.compile("^VIS ((\\d)*( )?(\\d?\\/?\\d))V((\\d)*( )?(\\d?\\/?\\d))");
    /** Sector visibility. */
    private static final Pattern SECTOR_VISIBILITY = Pattern.compile("^VIS ([A-Z]{1,2}) ((\\d)*( )?(\\d?\\/?\\d))");
    /** Visibility at second location. */
    private static final Pattern SECOND_LOCATION_VISIBILITY = Pattern.compile("^VIS ((\\d)*( )?(\\d?\\/?\\d)) (\\w+)");
    /** Tornadic activity with beginning time. */
    private static final Pattern TORNADIC_ACTIVITY_BEGINNING = Pattern.compile("^(TORNADO|FUNNEL CLOUD|WATERSPOUT) (B(\\d{2})?(\\d{2}))( (\\d+)? ([A-Z]{1,2})?)?");
    /** Tornadic activity with ending time. */
    private static final Pattern TORNADIC_ACTIVITY_ENDING = Pattern.compile("^(TORNADO|FUNNEL CLOUD|WATERSPOUT) (E(\\d{2})?(\\d{2}))( (\\d+)? ([A-Z]{1,2})?)?");
    /** Tornadic activity with beginning and ending time. */
    private static final Pattern TORNADIC_ACTIVITY_BEG_END = Pattern.compile("^(TORNADO|FUNNEL CLOUD|WATERSPOUT) (B(\\d{2})?(\\d{2}))(E(\\d{2})?(\\d{2}))( (\\d+)? ([A-Z]{1,2})?)?");
    /** Beginning and ending of precipitation. */
    private static final Pattern PRECIPITATION_BEG_END = Pattern.compile("^(([A-Z]{2})?([A-Z]{2})B(\\d{2})?(\\d{2})E(\\d{2})?(\\d{2}))");
    /** Thunderstorm location moving. */
    private static final Pattern THUNDERSTORM_LOCATION_MOVING = Pattern.compile("^TS ([A-Z]{2}) MOV ([A-Z]{2})");
    /** Thunderstorm location. */
    private static final Pattern THUNDERSTORM_LOCATION = Pattern.compile("^TS ([A-Z]{2})");
    /** Hail size less than. */
    private static final Pattern HAIL_SIZE_LESS_THAN = Pattern.compile("^GR LESS THAN ((\\d )?(\\d\\/\\d)?)");
    /** Hail size. */
    private static final Pattern HAIL_SIZE = Pattern.compile("^GR ((\\d\\/\\d)|((\\d) ?(\\d\\/\\d)?))");
    /** Snow pellets intensity. */
    private static final Pattern SNOW_PELLETS_INTENSITY = Pattern.compile("^GS (LGT|MOD|HVY)");
    /** Virga with direction. */
    private static final Pattern VIRGA_DIRECTION = Pattern.compile("^VIRGA ([A-Z]{2})");
    /** Ceiling height. */
    private static final Pattern CEILING_HEIGHT = Pattern.compile("^CIG (\\d{3})V(\\d{3})");
    /** Obscuration pattern. */
    private static final Pattern OBSCURATION = Pattern.compile("^([A-Z]{2}) ([A-Z]{3})(\\d{3})");
    /** Variable sky condition with height. */
    private static final Pattern VARIABLE_SKY_HEIGHT = Pattern.compile("^([A-Z]{3})(\\d{3}) V ([A-Z]{3})");
    /** Variable sky condition. */
    private static final Pattern VARIABLE_SKY = Pattern.compile("^([A-Z]{3}) V ([A-Z]{3})");
    /** Ceiling height second location. */
    private static final Pattern CEILING_SECOND_LOCATION = Pattern.compile("^CIG (\\d{3}) (\\w+)");
    /** Sea level pressure. */
    private static final Pattern SEAL_LEVEL_PRESSURE = Pattern.compile("^SLP(\\d{2})(\\d)");
    /** Snow increasing rapidly. */
    private static final Pattern SNOW_INCR_RAPIDLY = Pattern.compile("^SNINCR (\\d+)\\/(\\d+)");

    /** Message instance. */
    private final Messages fMessages;

    /***
     * Private constructor.
     */
    private RemarkParser() {
        fMessages = Messages.getInstance();
    }

    /**
     * @param pRemark the remark to parse.
     * @return the remark string
     */
    public String parse(final String pRemark) {
        String rmk = pRemark;
        StringBuilder sb = new StringBuilder();
        while (!rmk.equals("")) {
            if (Regex.find(WIND_PEAK, rmk)) {
                String[] windPeakParts = Regex.pregMatch(WIND_PEAK, rmk);
                sb.append(fMessages.getString("Remark.PeakWind", windPeakParts[1], windPeakParts[2], verifyString(windPeakParts[3]), windPeakParts[4]));
                sb.append(" ");
                rmk = rmk.replaceFirst(WIND_PEAK.pattern(), "").trim();
            } else if (Regex.find(WIND_SHIFT_FROPA, rmk)) {
                String[] windShiftParts = Regex.pregMatch(WIND_SHIFT_FROPA, rmk);
                sb.append(fMessages.getString("Remark.WindShift.FROPA", verifyString(windShiftParts[1]), windShiftParts[2]));
                sb.append(" ");
                rmk = rmk.replaceFirst(WIND_SHIFT_FROPA.pattern(), "");
            } else if (Regex.find(WIND_SHIFT, rmk)) {
                String[] windShiftParts = Regex.pregMatch(WIND_SHIFT, rmk);
                sb.append(fMessages.getString("Remark.WindShift", verifyString(windShiftParts[1]), windShiftParts[2]));
                sb.append(" ");
                rmk = rmk.replaceFirst(WIND_SHIFT.pattern(), "").trim();
            } else if (Regex.find(TOWER_VISIBILITY, rmk)) {
                String[] towerVisibilityParts = Regex.pregMatch(TOWER_VISIBILITY, rmk);
                sb.append(fMessages.getString("Remark.Tower.Visibility", towerVisibilityParts[1])).append(" ");
                rmk = rmk.replaceFirst(TOWER_VISIBILITY.pattern(), "").trim();
            } else if (Regex.find(SURFACE_VISIBILITY, rmk)) {
                String[] surfaceVisivilityParts = Regex.pregMatch(SURFACE_VISIBILITY, rmk);
                sb.append(fMessages.getString("Remark.Surface.Visibility", surfaceVisivilityParts[1])).append(" ");
                rmk = rmk.replaceFirst(SURFACE_VISIBILITY.pattern(), "").trim();
            } else if (Regex.find(VARIABLE_PREVAILING_VISIBILITY, rmk)) {
                String[] visibilityParts = Regex.pregMatch(VARIABLE_PREVAILING_VISIBILITY, rmk);
                sb.append(fMessages.getString("Remark.Variable.Prevailing.Visibility", visibilityParts[1], visibilityParts[5])).append(" ");
                rmk = rmk.replaceFirst(VARIABLE_PREVAILING_VISIBILITY.pattern(), "").trim();
            } else if (Regex.find(SECTOR_VISIBILITY, rmk)) {
                String[] sectorVisibilityParts = Regex.pregMatch(SECTOR_VISIBILITY, rmk);
                sb.append(fMessages.getString("Remark.Sector.Visibility", fMessages.getString("Converter." + sectorVisibilityParts[1]), sectorVisibilityParts[2])).append(" ");
                rmk = rmk.replaceFirst(SECTOR_VISIBILITY.pattern(), "").trim();
            } else if (Regex.find(SECOND_LOCATION_VISIBILITY, rmk)) {
                String[] secondLocationVisibilityParts = Regex.pregMatch(SECOND_LOCATION_VISIBILITY, rmk);
                sb.append(fMessages.getString("Remark.Second.Location.Visibility", secondLocationVisibilityParts[1], secondLocationVisibilityParts[5])).append(" ");
                rmk = rmk.replaceFirst(SECOND_LOCATION_VISIBILITY.pattern(), "").trim();
            } else if (Regex.find(TORNADIC_ACTIVITY_BEG_END, rmk)) {
                String[] tornadicParts = Regex.pregMatch(TORNADIC_ACTIVITY_BEG_END, rmk);
                sb.append(fMessages.getString("Remark.Tornadic.Activity.BegEnd", fMessages.getString("Remark." + tornadicParts[1].replace(" ", "")), verifyString(tornadicParts[3]), tornadicParts[4],
                        verifyString(tornadicParts[6]), tornadicParts[7], tornadicParts[9], fMessages.getString("Converter." + tornadicParts[10]))).append(" ");
                rmk = rmk.replaceFirst(TORNADIC_ACTIVITY_BEG_END.pattern(), "").trim();
            } else if (Regex.find(TORNADIC_ACTIVITY_BEGINNING, rmk)) {
                String[] tornadicParts = Regex.pregMatch(TORNADIC_ACTIVITY_BEGINNING, rmk);
                sb.append(fMessages.getString("Remark.Tornadic.Activity.Beginning", fMessages.getString("Remark." + tornadicParts[1].replace(" ", "")), verifyString(tornadicParts[3]),
                        tornadicParts[4], tornadicParts[6], fMessages.getString("Converter." + tornadicParts[7]))).append(" ");
                rmk = rmk.replaceFirst(TORNADIC_ACTIVITY_BEGINNING.pattern(), "").trim();
            } else if (Regex.find(TORNADIC_ACTIVITY_ENDING, rmk)) {
                String[] tornadicParts = Regex.pregMatch(TORNADIC_ACTIVITY_ENDING, rmk);
                sb.append(fMessages.getString("Remark.Tornadic.Activity.Ending", fMessages.getString("Remark." + tornadicParts[1].replace(" ", "")), verifyString(tornadicParts[3]), tornadicParts[4],
                        tornadicParts[6], fMessages.getString("Converter." + tornadicParts[7]))).append(" ");
                rmk = rmk.replaceFirst(TORNADIC_ACTIVITY_ENDING.pattern(), "").trim();
            } else if (Regex.find(PRECIPITATION_BEG_END, rmk)) {
                String[] precipitationBegEnd = Regex.pregMatch(PRECIPITATION_BEG_END, rmk);
                sb.append(fMessages.getString("Remark.Precipitation.Beg.End", precipitationBegEnd[2] == null ? "" : fMessages.getString("Descriptive." + precipitationBegEnd[2]),
                        fMessages.getString("Phenomenon." + precipitationBegEnd[3]), verifyString(precipitationBegEnd[4]), precipitationBegEnd[5], verifyString(precipitationBegEnd[6]),
                        precipitationBegEnd[7])).append(" ");
                rmk = rmk.replaceFirst(PRECIPITATION_BEG_END.pattern(), "").trim();
            } else if (Regex.find(THUNDERSTORM_LOCATION_MOVING, rmk)) {
                String[] thunderStormParts = Regex.pregMatch(THUNDERSTORM_LOCATION_MOVING, rmk);
                sb.append(
                        fMessages.getString("Remark.Thunderstorm.Location.Moving", fMessages.getString("Converter." + thunderStormParts[1]), fMessages.getString("Converter." + thunderStormParts[2])))
                .append(" ");
                rmk = rmk.replaceFirst(THUNDERSTORM_LOCATION_MOVING.pattern(), "").trim();
            } else if (Regex.find(THUNDERSTORM_LOCATION, rmk)) {
                String[] thunderStormParts = Regex.pregMatch(THUNDERSTORM_LOCATION, rmk);
                sb.append(fMessages.getString("Remark.Thunderstorm.Location", fMessages.getString("Converter." + thunderStormParts[1]))).append(" ");
                rmk = rmk.replaceFirst(THUNDERSTORM_LOCATION.pattern(), "").trim();
            } else if (Regex.find(HAIL_SIZE, rmk)) {
                String[] hailParts = Regex.pregMatch(HAIL_SIZE, rmk);
                sb.append(fMessages.getString("Remark.Hail", hailParts[1])).append(" ");
                rmk = rmk.replaceFirst(HAIL_SIZE.pattern(), "").trim();
            } else if (Regex.find(HAIL_SIZE_LESS_THAN, rmk)) {
                String[] hailParts = Regex.pregMatch(HAIL_SIZE_LESS_THAN, rmk);
                sb.append(fMessages.getString("Remark.Hail.LesserThan", hailParts[1])).append(" ");
                rmk = rmk.replaceFirst(HAIL_SIZE_LESS_THAN.pattern(), "").trim();
            } else if (Regex.find(SNOW_PELLETS_INTENSITY, rmk)) {
                String[] intensityParts = Regex.pregMatch(SNOW_PELLETS_INTENSITY, rmk);
                sb.append(fMessages.getString("Remark.Snow.Pellets", fMessages.getString("Remark." + intensityParts[1]))).append(" ");
                rmk = rmk.replaceFirst(SNOW_PELLETS_INTENSITY.pattern(), "").trim();
            } else if (Regex.find(VIRGA_DIRECTION, rmk)) {
                String[] virgaDirection = Regex.pregMatch(VIRGA_DIRECTION, rmk);
                sb.append(fMessages.getString("Remark.Virga.Direction", fMessages.getString("Converter." + virgaDirection[1]))).append(" ");
                rmk = rmk.replaceFirst(VIRGA_DIRECTION.pattern(), "").trim();
            } else if (Regex.find(CEILING_HEIGHT, rmk)) {
                String[] ceilingParts = Regex.pregMatch(CEILING_HEIGHT, rmk);
                int min = Integer.parseInt(ceilingParts[1]) * 100;
                int max = Integer.parseInt(ceilingParts[2]) * 100;
                sb.append(fMessages.getString("Remark.Ceiling.Height", min, max)).append(" ");
                rmk = rmk.replaceFirst(CEILING_HEIGHT.pattern(), "").trim();
            } else if (Regex.find(OBSCURATION, rmk)) {
                String[] obscuration = Regex.pregMatch(OBSCURATION, rmk);
                try {
                    String layer = fMessages.getString("CloudQuantity." + obscuration[2]);
                    int height = Integer.parseInt(obscuration[3]) * 100;
                    String obscDetail = fMessages.getString("Phenomenon." + obscuration[1]);
                    sb.append(fMessages.getString("Remark.Obscuration", layer, height, obscDetail)).append(" ");
                    rmk = rmk.replaceFirst(OBSCURATION.pattern(), "").trim();
                } catch (MissingResourceException mre) {
                    rmk = defaultRemark(rmk, sb);
                }
            } else if (Regex.find(VARIABLE_SKY_HEIGHT, rmk)) {
                String[] variableSky = Regex.pregMatch(VARIABLE_SKY_HEIGHT, rmk);
                String layer1 = fMessages.getString("CloudQuantity." + variableSky[1]);
                int height = Integer.parseInt(variableSky[2]) * 100;
                String layer2 = fMessages.getString("CloudQuantity." + variableSky[3]);
                sb.append(fMessages.getString("Remark.Variable.Sky.Condition.Height", height, layer1, layer2)).append(" ");
                rmk = rmk.replaceFirst(VARIABLE_SKY_HEIGHT.pattern(), "").trim();
            } else if (Regex.find(VARIABLE_SKY, rmk)) {
                String[] variableSky = Regex.pregMatch(VARIABLE_SKY, rmk);
                String layer1 = fMessages.getString("CloudQuantity." + variableSky[1]);
                String layer2 = fMessages.getString("CloudQuantity." + variableSky[2]);
                sb.append(fMessages.getString("Remark.Variable.Sky.Condition", layer1, layer2)).append(" ");
                rmk = rmk.replaceFirst(VARIABLE_SKY.pattern(), "").trim();
            } else if (Regex.find(CEILING_SECOND_LOCATION, rmk)) {
                String[] ceilingParts = Regex.pregMatch(CEILING_SECOND_LOCATION, rmk);
                int height = 100 * Integer.parseInt(ceilingParts[1]);
                String location = ceilingParts[2];
                sb.append(fMessages.getString("Remark.Ceiling.Second.Location", height, location)).append(" ");
                rmk = rmk.replaceFirst(CEILING_SECOND_LOCATION.pattern(), "").trim();
            } else if (Regex.find(SEAL_LEVEL_PRESSURE, rmk)) {
                String[] sealevelParts = Regex.pregMatch(SEAL_LEVEL_PRESSURE, rmk);
                String pressure;
                if (sealevelParts[1].startsWith("9")) {
                    pressure = "9";
                } else {
                    pressure = "10";
                }
                pressure = pressure + sealevelParts[1] + "." + sealevelParts[2];
                sb.append(fMessages.getString("Remark.Sea.Level.Pressure", pressure)).append(" ");
                rmk = rmk.replaceFirst(SEAL_LEVEL_PRESSURE.pattern(), "").trim();
            } else if (Regex.find(SNOW_INCR_RAPIDLY, rmk)) {
                String[] snowParts = Regex.pregMatch(SNOW_INCR_RAPIDLY, rmk);
                sb.append(fMessages.getString("Remark.Snow.Increasing.Rapidly", snowParts[1], snowParts[2])).append(" ");
                rmk = rmk.replaceFirst(SNOW_INCR_RAPIDLY.pattern(), "").trim();
            } else {
                rmk = defaultRemark(rmk, sb);
            }
        }
        return sb.toString();
    }

    private String defaultRemark(String pRmk, final StringBuilder pSb) {
        String[] strSlit = pRmk.split(" ", 2);
        String token = strSlit[0];
        try {
            token = fMessages.getString("Remark." + token);
        } catch (MissingResourceException e) {
            LOGGER.info("Remark token \"" + token + "\" is unknown.");
        }
        pSb.append(token).append(" ");
        pRmk = strSlit.length == 1 ? "" : strSlit[1];
        return pRmk;
    }

    /**
     * Checks if the string is null.
     * @param pString the string to test
     * @return empty string if null pString otherwise.
     */
    private String verifyString(final String pString) {
        if (pString == null) {
            return "";
        }
        return pString;
    }

    /**
     * @return the instance of the parser.
     */
    public static RemarkParser getInstance() {
        return INSTANCE;
    }
}
