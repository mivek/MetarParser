package io.github.mivek.model;

/**
 * Country class.
 * @author mivek
 */
public class Country {
    /**
     * Name of the country.
     */
    private String name;

    /**
     * Getter of name.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name.
     * @param pName the name to set.
     */
    public void setName(final String pName) {
        name = pName;
    }

    @Override
    public final String toString() {
        return name;
    }
}
