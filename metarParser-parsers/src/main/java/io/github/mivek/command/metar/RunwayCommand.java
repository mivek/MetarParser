package io.github.mivek.command.metar;

import io.github.mivek.enums.DepositBrakingCapacity;
import io.github.mivek.enums.DepositCoverage;
import io.github.mivek.enums.DepositThickness;
import io.github.mivek.enums.DepositType;
import io.github.mivek.enums.RunwayInfoTrend;
import io.github.mivek.model.Metar;
import io.github.mivek.model.RunwayInfo;
import io.github.mivek.utils.Converter;
import io.github.mivek.utils.Regex;

import java.util.AbstractMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mivek
 */
public final class RunwayCommand implements Command {
    /** Pattern to recognize a runway. */
    private static final Pattern GENERIC_RUNWAY_REGEX = Pattern.compile("^(R\\d{2}\\w?/)");
    /** Pattern regex for runway with min and max range visibility. */
    private static final Pattern RUNWAY_MAX_RANGE_REGEX = Pattern.compile("^R(\\d{2}\\w?)/(\\d{4})V(\\d{3,4})([UDN])?(FT)?");
    /** Pattern regex for runway visibility. */
    private static final Pattern RUNWAY_REGEX = Pattern.compile("^R(\\d{2}\\w?)/([MP])?(\\d{4})([UDN])?(FT)?$");
    /** Pattern regex for runway deposit. */
    private static final Pattern RUNWAY_DEPOSIT_REGEX = Pattern.compile("^R(\\d{2}\\w?)/([/\\d])([/\\d])(//|\\d{2})(//|\\d{2})$");
    /** Immutable map of type of deposit. */
    private static final Map<String, DepositType> DEPOSIT_TYPE_MAP = Stream.of(
            new AbstractMap.SimpleImmutableEntry<>("/", DepositType.NOT_REPORTED),
            new AbstractMap.SimpleImmutableEntry<>("0", DepositType.CLEAR_DRY),
            new AbstractMap.SimpleImmutableEntry<>("1", DepositType.DAMP),
            new AbstractMap.SimpleImmutableEntry<>("2", DepositType.WET_WATER_PATCHES),
            new AbstractMap.SimpleImmutableEntry<>("3", DepositType.RIME_FROST_COVERED),
            new AbstractMap.SimpleImmutableEntry<>("4", DepositType.DRY_SNOW),
            new AbstractMap.SimpleImmutableEntry<>("5", DepositType.WET_SNOW),
            new AbstractMap.SimpleImmutableEntry<>("6", DepositType.SLUSH),
            new AbstractMap.SimpleImmutableEntry<>("7", DepositType.ICE),
            new AbstractMap.SimpleImmutableEntry<>("8", DepositType.COMPACTED_SNOW),
            new AbstractMap.SimpleImmutableEntry<>("9", DepositType.FROZEN_RIDGES))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    /** Immutable map of deposit coverage. */
    private static final Map<String, DepositCoverage> DEPOSIT_COVERAGE_MAP = Stream.of(
            new AbstractMap.SimpleImmutableEntry<>("/", DepositCoverage.NOT_REPORTED),
            new AbstractMap.SimpleImmutableEntry<>("1", DepositCoverage.LESS_10),
            new AbstractMap.SimpleImmutableEntry<>("2", DepositCoverage.FROM_11_TO_25),
            new AbstractMap.SimpleImmutableEntry<>("5", DepositCoverage.FROM_26_TO_50),
            new AbstractMap.SimpleImmutableEntry<>("9", DepositCoverage.FROM_51_TO_100))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    /** Immutable map deposit thickness. */
    private static final Map<String, DepositThickness> DEPOSIT_THICKNESS_MAP = Stream.of(
            new AbstractMap.SimpleImmutableEntry<>("//", DepositThickness.NOT_REPORTED),
            new AbstractMap.SimpleImmutableEntry<>("00", DepositThickness.LESS_1_MM),
            new AbstractMap.SimpleImmutableEntry<>("92", DepositThickness.THICKNESS_10),
            new AbstractMap.SimpleImmutableEntry<>("93", DepositThickness.THICKNESS_15),
            new AbstractMap.SimpleImmutableEntry<>("94", DepositThickness.THICKNESS_20),
            new AbstractMap.SimpleImmutableEntry<>("95", DepositThickness.THICKNESS_25),
            new AbstractMap.SimpleImmutableEntry<>("96", DepositThickness.THICKNESS_30),
            new AbstractMap.SimpleImmutableEntry<>("97", DepositThickness.THICKNESS_35),
            new AbstractMap.SimpleImmutableEntry<>("98", DepositThickness.THICKNESS_40),
            new AbstractMap.SimpleImmutableEntry<>("99", DepositThickness.CLOSED))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    /** Immutable map of braking capacity. */
    private static final Map<String, DepositBrakingCapacity> DEPOSIT_BRAKING_CAPACITY_MAP = Stream.of(
            new AbstractMap.SimpleImmutableEntry<>("//", DepositBrakingCapacity.NOT_REPORTED),
            new AbstractMap.SimpleImmutableEntry<>("91", DepositBrakingCapacity.POOR),
            new AbstractMap.SimpleImmutableEntry<>("92", DepositBrakingCapacity.MEDIUM_POOR),
            new AbstractMap.SimpleImmutableEntry<>("93", DepositBrakingCapacity.MEDIUM),
            new AbstractMap.SimpleImmutableEntry<>("94", DepositBrakingCapacity.MEDIUM_GOOD),
            new AbstractMap.SimpleImmutableEntry<>("95", DepositBrakingCapacity.GOOD),
            new AbstractMap.SimpleImmutableEntry<>("99", DepositBrakingCapacity.UNRELIABLE))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


    /**
     * Package private constructor.
     */
    RunwayCommand() {
    }

    @Override
    public void execute(final Metar metar, final String part) {
        RunwayInfo ri = new RunwayInfo();
        String[] matches;

        if (Regex.find(RUNWAY_DEPOSIT_REGEX, part)) {
            matches = Regex.pregMatch(RUNWAY_DEPOSIT_REGEX, part);
            ri.setName(matches[1]);
            ri.setDepositType(DEPOSIT_TYPE_MAP.get(matches[2]));
            ri.setCoverage(DEPOSIT_COVERAGE_MAP.get(matches[3]));
            ri.setThickness(DEPOSIT_THICKNESS_MAP.get(matches[4]));
            ri.setBrakingCapacity(DEPOSIT_BRAKING_CAPACITY_MAP.get(matches[5]));
            metar.addRunwayInfo(ri);
        } else if (Regex.find(RUNWAY_REGEX, part)) {
            matches = Regex.pregMatch(RUNWAY_REGEX, part);
            ri.setName(matches[1]);
            ri.setIndicator(Converter.convertIndicator(matches[2]));
            ri.setMinRange(Integer.parseInt(matches[3]));
            ri.setTrend(RunwayInfoTrend.get(matches[4]));
            metar.addRunwayInfo(ri);
        } else if (Regex.find(RUNWAY_MAX_RANGE_REGEX, part)) {
            matches = Regex.pregMatch(RUNWAY_MAX_RANGE_REGEX, part);
            ri.setName(matches[1]);
            ri.setMinRange(Integer.parseInt(matches[2]));
            ri.setMaxRange(Integer.parseInt(matches[3]));
            ri.setTrend(RunwayInfoTrend.get(matches[4]));
            metar.addRunwayInfo(ri);
        }
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(GENERIC_RUNWAY_REGEX, input);
    }
}

