package io.github.mivek.command.metar;

import io.github.mivek.model.Metar;
import io.github.mivek.model.RunwayInfo;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;
import org.apache.commons.lang3.ArrayUtils;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class RunwayCommand implements Command {
    /** Pattern to recognize a runway. */
    private static final Pattern GENERIC_RUNWAY_REGEX = Pattern.compile("^(R\\d{2}\\w?/)");
    /** Pattern regex for runway with min and max range visibility. */
    private static final Pattern RUNWAY_MAX_RANGE_REGEX = Pattern.compile("^R(\\d{2}\\w?)/(\\d{4})V(\\d{3})(\\w{0,2})");
    /** Pattern regex for runway visibility. */
    private static final Pattern RUNWAY_REGEX = Pattern.compile("^R(\\d{2}\\w?)/(\\w)?(\\d{4})(\\w{0,2})$");

    /**
     * Package private constructor.
     */
    RunwayCommand() {
    }

    @Override
    public void execute(final Metar metar, final String part) {
        RunwayInfo ri = new RunwayInfo();
        String[] matches = Regex.pregMatch(RUNWAY_REGEX, part);
        if (ArrayUtils.isNotEmpty(matches)) {
            ri.setName(matches[1]);
            ri.setMinRange(Integer.parseInt(matches[3]));
            ri.setTrend(Converter.convertTrend(matches[4]));
            metar.addRunwayInfo(ri);
        }
        matches = Regex.pregMatch(RUNWAY_MAX_RANGE_REGEX, part);
        if (ArrayUtils.isNotEmpty(matches)) {
            ri.setName(matches[1]);
            ri.setMinRange(Integer.parseInt(matches[2]));
            ri.setMaxRange(Integer.parseInt(matches[3]));
            ri.setTrend(Converter.convertTrend(matches[4]));
            metar.addRunwayInfo(ri);
        }
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(GENERIC_RUNWAY_REGEX, input);
    }
}

