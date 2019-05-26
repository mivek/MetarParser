package io.github.mivek.parser;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.parser.command.remark.CeilingHeightCommand;
import io.github.mivek.parser.command.remark.CeilingSecondLocationCommand;
import io.github.mivek.parser.command.remark.Command;
import io.github.mivek.parser.command.remark.HailSizeCommand;
import io.github.mivek.parser.command.remark.ObscurationCommand;
import io.github.mivek.parser.command.remark.PrecipitationBegEndCommand;
import io.github.mivek.parser.command.remark.PrevailingVisibilityCommand;
import io.github.mivek.parser.command.remark.SeaLevelPressureCommand;
import io.github.mivek.parser.command.remark.SecondLocationVisibilityCommand;
import io.github.mivek.parser.command.remark.SectorVisibilityCommand;
import io.github.mivek.parser.command.remark.SmallHailSizeCommand;
import io.github.mivek.parser.command.remark.SnowIncreaseCommand;
import io.github.mivek.parser.command.remark.SnowPelletsCommand;
import io.github.mivek.parser.command.remark.SurfaceVisibilityCommand;
import io.github.mivek.parser.command.remark.ThunderStormLocationCommand;
import io.github.mivek.parser.command.remark.ThunderStormLocationMovingCommand;
import io.github.mivek.parser.command.remark.TornadicActivityBegCommand;
import io.github.mivek.parser.command.remark.TornadicActivityBegEndCommand;
import io.github.mivek.parser.command.remark.TornadicActivityEndCommand;
import io.github.mivek.parser.command.remark.TowerVisibilityCommand;
import io.github.mivek.parser.command.remark.VariableSkyCommand;
import io.github.mivek.parser.command.remark.VariableSkyHeightCommand;
import io.github.mivek.parser.command.remark.VirgaDirectionCommand;
import io.github.mivek.parser.command.remark.WindPeakCommand;
import io.github.mivek.parser.command.remark.WindShiftCommand;
import io.github.mivek.parser.command.remark.WindShiftFropaCommand;
import io.github.mivek.utils.Regex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class RemarkParser {
    /** Wind peak pattern. */
    public static final Pattern WIND_PEAK = Pattern.compile("^PK WND (\\d{3})(\\d{2,3})/(\\d{2})?(\\d{2})");
    /** Wind shift pattern. */
    public static final Pattern WIND_SHIFT = Pattern.compile("^WSHFT (\\d{2})?(\\d{2})");
    /** Wind shift fopa pattern. */
    public static final Pattern WIND_SHIFT_FROPA = Pattern.compile("^WSHFT (\\d{2})?(\\d{2}) FROPA");
    /** Tower visibility. */
    public static final Pattern TOWER_VISIBILITY = Pattern.compile("^TWR VIS ((\\d)*( )?(\\d?/?\\d))");
    /** Surface visibility. */
    public static final Pattern SURFACE_VISIBILITY = Pattern.compile("^SFC VIS ((\\d)*( )?(\\d?/?\\d))");
    /** Variable prevailing visibility. */
    public static final Pattern VARIABLE_PREVAILING_VISIBILITY = Pattern.compile("^VIS ((\\d)*( )?(\\d?/?\\d))V((\\d)*( )?(\\d?/?\\d))");
    /** Sector visibility. */
    public static final Pattern SECTOR_VISIBILITY = Pattern.compile("^VIS ([A-Z]{1,2}) ((\\d)*( )?(\\d?/?\\d))");
    /** Visibility at second location. */
    public static final Pattern SECOND_LOCATION_VISIBILITY = Pattern.compile("^VIS ((\\d)*( )?(\\d?/?\\d)) (\\w+)");
    /** Tornadic activity with beginning time. */
    public static final Pattern TORNADIC_ACTIVITY_BEGINNING = Pattern.compile("^(TORNADO|FUNNEL CLOUD|WATERSPOUT) (B(\\d{2})?(\\d{2}))( (\\d+)? ([A-Z]{1,2})?)?");
    /** Tornadic activity with ending time. */
    public static final Pattern TORNADIC_ACTIVITY_ENDING = Pattern.compile("^(TORNADO|FUNNEL CLOUD|WATERSPOUT) (E(\\d{2})?(\\d{2}))( (\\d+)? ([A-Z]{1,2})?)?");
    /** Tornadic activity with beginning and ending time. */
    public static final Pattern TORNADIC_ACTIVITY_BEG_END = Pattern.compile("^(TORNADO|FUNNEL CLOUD|WATERSPOUT) (B(\\d{2})?(\\d{2}))(E(\\d{2})?(\\d{2}))( (\\d+)? ([A-Z]{1,2})?)?");
    /** Beginning and ending of precipitation. */
    public static final Pattern PRECIPITATION_BEG_END = Pattern.compile("^(([A-Z]{2})?([A-Z]{2})B(\\d{2})?(\\d{2})E(\\d{2})?(\\d{2}))");
    /** Thunderstorm location moving. */
    public static final Pattern THUNDERSTORM_LOCATION_MOVING = Pattern.compile("^TS ([A-Z]{2}) MOV ([A-Z]{2})");
    /** Thunderstorm location. */
    public static final Pattern THUNDERSTORM_LOCATION = Pattern.compile("^TS ([A-Z]{2})");
    /** Hail size less than. */
    public static final Pattern HAIL_SIZE_LESS_THAN = Pattern.compile("^GR LESS THAN ((\\d )?(\\d/\\d)?)");
    /** Hail size. */
    public static final Pattern HAIL_SIZE = Pattern.compile("^GR ((\\d/\\d)|((\\d) ?(\\d/\\d)?))");
    /** Snow pellets intensity. */
    public static final Pattern SNOW_PELLETS_INTENSITY = Pattern.compile("^GS (LGT|MOD|HVY)");
    /** Virga with direction. */
    public static final Pattern VIRGA_DIRECTION = Pattern.compile("^VIRGA ([A-Z]{2})");
    /** Ceiling height. */
    public static final Pattern CEILING_HEIGHT = Pattern.compile("^CIG (\\d{3})V(\\d{3})");
    /** Obscuration pattern. */
    public static final Pattern OBSCURATION = Pattern.compile("^([A-Z]{2}) ([A-Z]{3})(\\d{3})");
    /** Variable sky condition with height. */
    public static final Pattern VARIABLE_SKY_HEIGHT = Pattern.compile("^([A-Z]{3})(\\d{3}) V ([A-Z]{3})");
    /** Variable sky condition. */
    public static final Pattern VARIABLE_SKY = Pattern.compile("^([A-Z]{3}) V ([A-Z]{3})");
    /** Ceiling height second location. */
    public static final Pattern CEILING_SECOND_LOCATION = Pattern.compile("^CIG (\\d{3}) (\\w+)");
    /** Sea level pressure. */
    public static final Pattern SEAL_LEVEL_PRESSURE = Pattern.compile("^SLP(\\d{2})(\\d)");
    /** Snow increasing rapidly. */
    public static final Pattern SNOW_INCR_RAPIDLY = Pattern.compile("^SNINCR (\\d+)/(\\d+)");
    /** Constant for cloud quantity. */
    public static final String CLOUD_QUANTITY = "CloudQuantity.";
    /** Constant for Remark. */
    public static final String REMARK = "Remark.";
    /** Constant for converter. */
    public static final String CONVERTER = "Converter.";
    /** The instance of the parser. */
    private static final RemarkParser INSTANCE = new RemarkParser();
    /** The logger instance. */
    private static final Logger LOGGER = Logger.getLogger(RemarkParser.class.getName());

    /** Message instance. */
    private final Messages fMessages;

    /** List of patterns. */
    private List<Pattern> patterns;

    /** Map of commands. */
    private Map<Pattern, Command> commandMap;

    /***
     * Private constructor.
     */
    private RemarkParser() {
        fMessages = Messages.getInstance();
        patterns = buildPatternList();
        commandMap = buildCommandMap();
    }

    /**
     * @param pRemark the remark to parse.
     * @return the remark string
     */
    public String parse(final String pRemark) {
        String rmk = pRemark;
        StringBuilder sb = new StringBuilder();
        while (!rmk.equals("")) {
            boolean found = false;
            for (Pattern p : patterns) {
                if (Regex.find(p, rmk)) {
                    found = true;
                    Command command = commandMap.get(p);
                    try {
                        rmk = command.execute(rmk, sb);
                    } catch (MissingResourceException e) {
                        rmk = defaultRemark(rmk, sb);
                    }
                    break;
                }
            }
            if (!found) {
                rmk = defaultRemark(rmk, sb);
            }
        }
        return sb.toString();
    }

    /**
     * Handle the default behavior when a remark token is not recognized by regex.
     *
     * @param pRmk the remark string
     * @param pSb  The string builder containing the parsed message of the remark
     * @return the remark string.
     */
    private String defaultRemark(final String pRmk, final StringBuilder pSb) {
        String rmk = pRmk;
        String[] strSlit = rmk.split(" ", 2);
        String token = strSlit[0];
        try {
            token = fMessages.getString(REMARK + token);
        } catch (MissingResourceException e) {
            LOGGER.info("Remark token \"" + token + "\" is unknown.");
        }
        pSb.append(token).append(" ");
        rmk = strSlit.length == 1 ? "" : strSlit[1];
        return rmk;
    }

    /**
     * Build the command map.
     * @return the map
     */
    protected Map<Pattern, Command> buildCommandMap() {
        Map<Pattern, Command> commandsPatternMap = new HashMap<>();
        commandsPatternMap.put(WIND_PEAK, new WindPeakCommand());
        commandsPatternMap.put(WIND_SHIFT, new WindShiftCommand());
        commandsPatternMap.put(WIND_SHIFT_FROPA, new WindShiftFropaCommand());
        commandsPatternMap.put(TOWER_VISIBILITY, new TowerVisibilityCommand());
        commandsPatternMap.put(SURFACE_VISIBILITY, new SurfaceVisibilityCommand());
        commandsPatternMap.put(VARIABLE_PREVAILING_VISIBILITY, new PrevailingVisibilityCommand());
        commandsPatternMap.put(SECTOR_VISIBILITY, new SectorVisibilityCommand());
        commandsPatternMap.put(SECOND_LOCATION_VISIBILITY, new SecondLocationVisibilityCommand());
        commandsPatternMap.put(TORNADIC_ACTIVITY_BEGINNING, new TornadicActivityBegCommand());
        commandsPatternMap.put(TORNADIC_ACTIVITY_ENDING, new TornadicActivityEndCommand());
        commandsPatternMap.put(TORNADIC_ACTIVITY_BEG_END, new TornadicActivityBegEndCommand());
        commandsPatternMap.put(PRECIPITATION_BEG_END, new PrecipitationBegEndCommand());
        commandsPatternMap.put(THUNDERSTORM_LOCATION_MOVING, new ThunderStormLocationMovingCommand());
        commandsPatternMap.put(THUNDERSTORM_LOCATION, new ThunderStormLocationCommand());
        commandsPatternMap.put(HAIL_SIZE_LESS_THAN, new SmallHailSizeCommand());
        commandsPatternMap.put(HAIL_SIZE, new HailSizeCommand());
        commandsPatternMap.put(SNOW_PELLETS_INTENSITY, new SnowPelletsCommand());
        commandsPatternMap.put(VIRGA_DIRECTION, new VirgaDirectionCommand());
        commandsPatternMap.put(CEILING_HEIGHT, new CeilingHeightCommand());
        commandsPatternMap.put(OBSCURATION, new ObscurationCommand());
        commandsPatternMap.put(VARIABLE_SKY_HEIGHT, new VariableSkyHeightCommand());
        commandsPatternMap.put(VARIABLE_SKY, new VariableSkyCommand());
        commandsPatternMap.put(CEILING_SECOND_LOCATION, new CeilingSecondLocationCommand());
        commandsPatternMap.put(SEAL_LEVEL_PRESSURE, new SeaLevelPressureCommand());
        commandsPatternMap.put(SNOW_INCR_RAPIDLY, new SnowIncreaseCommand());
        return commandsPatternMap;
    }

    /**
     * @return the list of patterns.
     */
    protected List<Pattern> buildPatternList() {
        List<Pattern> patternsList = new ArrayList<>();
        patternsList.add(WIND_PEAK);
        patternsList.add(WIND_SHIFT_FROPA);
        patternsList.add(WIND_SHIFT);
        patternsList.add(TOWER_VISIBILITY);
        patternsList.add(SURFACE_VISIBILITY);
        patternsList.add(VARIABLE_PREVAILING_VISIBILITY);
        patternsList.add(SECOND_LOCATION_VISIBILITY);
        patternsList.add(SECTOR_VISIBILITY);
        patternsList.add(TORNADIC_ACTIVITY_BEG_END);
        patternsList.add(TORNADIC_ACTIVITY_BEGINNING);
        patternsList.add(TORNADIC_ACTIVITY_ENDING);
        patternsList.add(PRECIPITATION_BEG_END);
        patternsList.add(THUNDERSTORM_LOCATION_MOVING);
        patternsList.add(THUNDERSTORM_LOCATION);
        patternsList.add(HAIL_SIZE_LESS_THAN);
        patternsList.add(HAIL_SIZE);
        patternsList.add(SNOW_PELLETS_INTENSITY);
        patternsList.add(VIRGA_DIRECTION);
        patternsList.add(CEILING_HEIGHT);
        patternsList.add(OBSCURATION);
        patternsList.add(VARIABLE_SKY_HEIGHT);
        patternsList.add(VARIABLE_SKY);
        patternsList.add(CEILING_SECOND_LOCATION);
        patternsList.add(SEAL_LEVEL_PRESSURE);
        patternsList.add(SNOW_INCR_RAPIDLY);
        return patternsList;
    }

    /**
     * @return the instance of the parser.
     */
    public static RemarkParser getInstance() {
        return INSTANCE;
    }
}
