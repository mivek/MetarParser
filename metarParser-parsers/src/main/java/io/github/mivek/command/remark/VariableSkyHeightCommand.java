package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class VariableSkyHeightCommand implements Command {
    /** Variable sky condition with height. */
    private static final Pattern VARIABLE_SKY_HEIGHT = Pattern.compile("^([A-Z]{3})(\\d{3}) V ([A-Z]{3})");

    /** The message instance. */
    private final Messages messages;

    /**
     * Default constructor.
     */
    VariableSkyHeightCommand() {
        messages = Messages.getInstance();
    }

    @Override
    public String execute(final String remark, final StringBuilder stringBuilder) {
        String[] variableSky = Regex.pregMatch(VARIABLE_SKY_HEIGHT, remark);
        String layer1 = messages.getString("CloudQuantity." + variableSky[1]);
        int height = Integer.parseInt(variableSky[2]) * 100;
        String layer2 = messages.getString("CloudQuantity." + variableSky[3]);
        stringBuilder.append(messages.getString("Remark.Variable.Sky.Condition.Height", height, layer1, layer2)).append(" ");
        return remark.replaceFirst(VARIABLE_SKY_HEIGHT.pattern(), "").trim();
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(VARIABLE_SKY_HEIGHT, input);
    }
}
