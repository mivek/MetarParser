package io.github.mivek.command.common;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.CloudType;
import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Cloud;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class CloudCommand implements Command {
    /** Pattern to recognize clouds. */
    private static final Pattern CLOUD_REGEX = Pattern.compile("^(\\p{Upper}{3})((\\d{3}|/{3})(\\p{Upper}{2,3}|/{3})?)?$");
    /** String to identify the unknown part in a cloud. */
    private static final String UNDEFINED = "///";
    /**
     * constructor.
     */
    CloudCommand() {
    }

    @Override
    public boolean execute(final AbstractWeatherContainer container, final String part) {
        Cloud c = parseCloud(part);
        return container.addCloud(c);
    }

    /**
     * This method parses the cloud part of the metar.
     *
     * @param cloudString string with cloud elements.
     * @return a decoded cloud with its quantity, its altitude and its type.
     */
    Cloud parseCloud(final String cloudString) {
        Cloud cloud = new Cloud();
        String[] cloudPart = Regex.pregMatch(CLOUD_REGEX, cloudString);
        try {
            CloudQuantity cq = CloudQuantity.valueOf(cloudPart[1]);
            cloud.setQuantity(cq);
            if (cloudPart[3] != null && !UNDEFINED.equals(cloudPart[3])) {
                cloud.setHeight(100 * Integer.parseInt(cloudPart[3]));
            }
            if (cloudPart[4] != null && !UNDEFINED.equals(cloudPart[4])) {
                CloudType ct = CloudType.valueOf(cloudPart[4]);
                cloud.setType(ct);
            }
            return cloud;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public boolean canParse(final String input) {
        return Regex.find(CLOUD_REGEX, input);
    }
}

