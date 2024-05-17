package io.github.mivek.command.metar;

import io.github.mivek.enums.DepositCoverage;
import io.github.mivek.enums.DepositType;
import io.github.mivek.enums.RunwayInfoIndicator;
import io.github.mivek.enums.RunwayInfoTrend;
import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.Metar;
import io.github.mivek.model.RunwayInfo;
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
    private static final Map<String, String> DEPOSIT_THICKNESS_MAP = Stream.of(
            new AbstractMap.SimpleImmutableEntry<>("//", "DepositThickness.//"),
            new AbstractMap.SimpleImmutableEntry<>("00", "DepositThickness.00"),
            new AbstractMap.SimpleImmutableEntry<>("92", "DepositThickness.92"),
            new AbstractMap.SimpleImmutableEntry<>("93", "DepositThickness.93"),
            new AbstractMap.SimpleImmutableEntry<>("94", "DepositThickness.94"),
            new AbstractMap.SimpleImmutableEntry<>("95", "DepositThickness.95"),
            new AbstractMap.SimpleImmutableEntry<>("96", "DepositThickness.96"),
            new AbstractMap.SimpleImmutableEntry<>("97", "DepositThickness.97"),
            new AbstractMap.SimpleImmutableEntry<>("98", "DepositThickness.98"),
            new AbstractMap.SimpleImmutableEntry<>("99", "DepositThickness.99"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    /** Immutable map of braking capacity. */
    private static final Map<String, String> DEPOSIT_BRAKING_CAPACITY_MAP = Stream.of(
            new AbstractMap.SimpleImmutableEntry<>("//", "DepositBrakingCapacity.//"),
            new AbstractMap.SimpleImmutableEntry<>("91", "DepositBrakingCapacity.91"),
            new AbstractMap.SimpleImmutableEntry<>("92", "DepositBrakingCapacity.92"),
            new AbstractMap.SimpleImmutableEntry<>("93", "DepositBrakingCapacity.93"),
            new AbstractMap.SimpleImmutableEntry<>("94", "DepositBrakingCapacity.94"),
            new AbstractMap.SimpleImmutableEntry<>("95", "DepositBrakingCapacity.95"),
            new AbstractMap.SimpleImmutableEntry<>("99", "DepositBrakingCapacity.99"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    /** Message instance. */
    private final Messages messages;
    /**
     * Package private constructor.
     */
    RunwayCommand() {
        this.messages = Messages.getInstance();
    }

    @Override
    public void execute(final Metar metar, final String part) throws ParseException {
        try {
            if (Regex.find(RUNWAY_DEPOSIT_REGEX, part)) {
                parseRunwayDeposit(metar, part);
            } else if (Regex.find(RUNWAY_REGEX, part)) {
                parseRunway(metar, part);
            } else if (Regex.find(RUNWAY_MAX_RANGE_REGEX, part)) {
                parseRunwayMaxRange(metar, part);
            }
        } catch (NumberFormatException nfe) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INCOMPLETE_RUNWAY_INFORMATION);
        }
    }

    /**
     * Parses a runway token with its maximum visual range provided.
     * @param metar The METAR object to update
     * @param part The part of the message to parse
     */
    private void parseRunwayMaxRange(final Metar metar, final String part) {
        String[] matches;
        RunwayInfo ri = new RunwayInfo();
        matches = Regex.pregMatch(RUNWAY_MAX_RANGE_REGEX, part);
        ri.setName(matches[1]);
        ri.setMinRange(Integer.parseInt(matches[2]));
        ri.setMaxRange(Integer.parseInt(matches[3]));
        ri.setTrend(RunwayInfoTrend.get(matches[4]));
        metar.addRunwayInfo(ri);
    }

    /**
     * Parses a runway token.
     * @param metar The METAR object to update
     * @param part The part of the message to parse
     */
    private void parseRunway(final Metar metar, final String part) {
        String[] matches;
        RunwayInfo ri = new RunwayInfo();
        matches = Regex.pregMatch(RUNWAY_REGEX, part);
        ri.setName(matches[1]);
        ri.setIndicator(RunwayInfoIndicator.get(matches[2]));
        ri.setMinRange(Integer.parseInt(matches[3]));
        ri.setTrend(RunwayInfoTrend.get(matches[4]));
        metar.addRunwayInfo(ri);
    }

    /**
     * Parses a runway token with its deposit provided.
     * @param metar The METAR object to update
     * @param part The part of the message to parse
     */
    private void parseRunwayDeposit(final Metar metar, final String part) {
        String[] matches;
        RunwayInfo ri = new RunwayInfo();
        matches = Regex.pregMatch(RUNWAY_DEPOSIT_REGEX, part);
        ri.setName(matches[1]);
        ri.setDepositType(DEPOSIT_TYPE_MAP.get(matches[2]));
        ri.setCoverage(DEPOSIT_COVERAGE_MAP.get(matches[3]));
        ri.setThickness(parseDepositThickness(matches[4]));
        ri.setBrakingCapacity(parseDepositBrakingCapacity(matches[5]));
        metar.addRunwayInfo(ri);
    }

    /**
     * Parses the deposit thickness according to the input.
     * @param input The deposit thickness to parse.
     * @return Translated sentence representing the deposit thickness.
     */
    private String parseDepositThickness(final String input) {
        return messages.getString(DEPOSIT_THICKNESS_MAP.getOrDefault(input, "DepositThickness.default"), input);
    }

    /**
     * Parses the braking capacity according to the input.
     * If the input is not in the map then the default translation is used.
     * @param input The braking capacity.
     * @return Translated sentence representing the braking capacity.
     */
    private String parseDepositBrakingCapacity(final String input) {
        return messages.getString(DEPOSIT_BRAKING_CAPACITY_MAP.getOrDefault(input, "DepositBrakingCapacity.default"), Double.parseDouble(input) / 100);
    }
    @Override
    public boolean canParse(final String input) {
        return Regex.find(GENERIC_RUNWAY_REGEX, input);
    }
}

