package io.github.mivek.model;

/**
 * Class representing the wind shear.
 * @author mivek
 */
public class WindShear extends Wind {
    /** The fHeight of the wind shear in feet. */
    private int fHeight;

    /**
     * @return the height
     */
    public int getHeight() {
        return fHeight;
    }

    /**
     * @param pHeight the height to set
     */
    public void setHeight(final int pHeight) {
        fHeight = pHeight;
    }
}
