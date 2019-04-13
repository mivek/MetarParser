package io.github.mivek.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing the wind shear.
 * @author mivek
 */
public class WindShear extends Wind {
    /** The height of the wind shear in feet. */
    private int height;

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param pHeight the height to set
     */
    public void setHeight(final int pHeight) {
        height = pHeight;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                appendSuper(super.toString()).
                append("height (feet)", height).
                toString();
    }
}
