package io.github.mivek.model;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.CloudType;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing a cloud element. Clouds are composed of : a quantity a
 * type (optional) an height (optional)
 * @author mivek
 */
public class Cloud {
    /** The height of the cloud (unit: feet). */
    private int height;
    /** The quantity of the cloud. */
    private CloudQuantity quantity;
    /** The type of the cloud. */
    private CloudType type;

    /**
     * Getter of the altitude (unit: meters, approximation).
     * @return int of altitude.
     * @deprecated Use {@link #getHeight()}
     */
    @Deprecated
    public int getAltitude() {
        return height * 30 / 100;
    }

    /**
     * Setter of the altitude (unit: meters).
     *
     * @param pAltitude The altitude to set.
     * @deprecated Use {@link #setHeight(int)}
     */
    @Deprecated public void setAltitude(final int pAltitude) {
        height = pAltitude * 100 / 30;
    }

    /**
     * Getter of the height (unit: feet).
     * @return int of height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter of the height (unit: feet).
     * @param pHeight The height to set.
     */
    public void setHeight(final int pHeight) {
        height = pHeight;
    }

    /**
     * Getter of the quantity.
     * @return a CloudQuantity.
     */
    public CloudQuantity getQuantity() {
        return quantity;
    }

    /**
     * Setter of CloudQuantity.
     * @param pQuantity The CloudQuantity to set.
     */
    public void setQuantity(final CloudQuantity pQuantity) {
        quantity = pQuantity;
    }

    /**
     * Getter of type.
     * @return a CloudType.
     */
    public CloudType getType() {
        return type;
    }

    /**
     * Setter of cloud type.
     * @param pType The CloudType to set.
     */
    public void setType(final CloudType pType) {
        type = pType;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                append("quantity", quantity).
                append("type", type).
                append("height (ft)", height).
                append("height (m)", getAltitude()).
                toString();
    }
}
