package com.mivek.model;

/**
 * Country class.
 * @author mivek
 */
public class Country {
    /**
     * Name of the country.
     */
    private String fName;

    /**
     * Getter of name.
     * @return the name.
     */
    public String getName() {
        return fName;
    }

    /**
     * Setter of name.
     * @param pName the name to set.
     */
    public void setName(final String pName) {
        fName = pName;
    }

    @Override
    public final String toString() {
        return fName;
    }
}
