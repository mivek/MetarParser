package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Jean-Kevin KPADEY
 */
public final class HourlyPressureCommand implements Command {
    /** Pattern for the hourly pressure. */
    private static final Pattern PRESSURE_PATTERN = Pattern.compile("^5(\\d)(\\d{3})\\b");
    /** Map for the barometer tendency. */
    private final Map<Integer, String> BAROMETER_TENDENCY;
    /** The message instance. */
    private final Messages messages;


    HourlyPressureCommand() {
        this.messages = Messages.getInstance();
        BAROMETER_TENDENCY = new HashMap<>();
        BAROMETER_TENDENCY.put(0, "Remark.Barometer.0");
        BAROMETER_TENDENCY.put(1, "Remark.Barometer.1");
        BAROMETER_TENDENCY.put(2, "Remark.Barometer.2");
        BAROMETER_TENDENCY.put(3, "Remark.Barometer.3");
        BAROMETER_TENDENCY.put(4, "Remark.Barometer.4");
        BAROMETER_TENDENCY.put(5, "Remark.Barometer.5");
        BAROMETER_TENDENCY.put(6, "Remark.Barometer.6");
        BAROMETER_TENDENCY.put(7, "Remark.Barometer.7");
        BAROMETER_TENDENCY.put(8, "Remark.Barometer.8");
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] matches = Regex.pregMatch(PRESSURE_PATTERN, remark);
        stringBuilder.append(messages.getString(BAROMETER_TENDENCY.get(Integer.valueOf(matches[1])))).append(" ")
                .append(messages.getString("Remark.Pressure.Tendency", Float.parseFloat(matches[2]) / 10))
                .append(" ");
        return remark.replaceFirst(PRESSURE_PATTERN.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(PRESSURE_PATTERN, input);
    }
}
