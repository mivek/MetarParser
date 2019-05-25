package io.github.mivek.parser.command.metar;

import io.github.mivek.model.Metar;
import io.github.mivek.model.RunwayInfo;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;
import org.apache.commons.lang3.ArrayUtils;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public class RunwayCommand implements Command {
    /** Pattern regex for runway with min and max range visibility. */
    private static final Pattern RUNWAY_MAX_RANGE_REGEX = Pattern.compile("^R(\\d{2}\\w?)/(\\d{4})V(\\d{3})(\\w{0,2})");
    /** Pattern regex for runway visibility. */
    private static final Pattern RUNWAY_REGEX = Pattern.compile("^R(\\d{2}\\w?)/(\\w)?(\\d{4})(\\w{0,2})$");

    @Override public final void execute(final Metar pMetar, final String pPart) {
        RunwayInfo ri = new RunwayInfo();
        String[] matches = Regex.pregMatch(RUNWAY_REGEX, pPart);
        if (ArrayUtils.isNotEmpty(matches)) {
            ri.setName(matches[1]);
            ri.setMinRange(Integer.parseInt(matches[3]));
            ri.setTrend(Converter.convertTrend(matches[4]));
            pMetar.addRunwayInfo(ri);
        }
        matches = Regex.pregMatch(RUNWAY_MAX_RANGE_REGEX, pPart);
        if (ArrayUtils.isNotEmpty(matches)) {
            ri.setName(matches[1]);
            ri.setMinRange(Integer.parseInt(matches[2]));
            ri.setMaxRange(Integer.parseInt(matches[3]));
            ri.setTrend(Converter.convertTrend(matches[4]));
            pMetar.addRunwayInfo(ri);
        }
    }
}
