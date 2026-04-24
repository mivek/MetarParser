package io.github.mivek.model;

import io.github.mivek.enums.LengthUnit;
import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing the wind shear.
 *
 * @author mivek
 */
public class WindShear extends Wind {
    /** The height of the wind shear. */
    private int height;
    /** The unit of the wind shear height. */
    private LengthUnit heightUnit;

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * @return the unit of the wind shear height.
     */
    public LengthUnit getHeightUnit() {
        return heightUnit;
    }

    /**
     * @param heightUnit the unit of the wind shear height to set.
     */
    public void setHeightUnit(final LengthUnit heightUnit) {
        this.heightUnit = heightUnit;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                appendSuper(super.toString()).
                append(Messages.getInstance().getString("ToString.height.feet"), height).
                append(Messages.getInstance().getString("ToString.height.unit"), heightUnit).
                toString();
    }
}
