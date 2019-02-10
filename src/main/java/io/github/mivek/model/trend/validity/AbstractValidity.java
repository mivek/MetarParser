package io.github.mivek.model.trend.validity;

/**
 * Abstract class for the validity of a TAF.
 * @author mivek
 */
public abstract class AbstractValidity implements IValidity {
    /**
     * Beginning day of the taf's validity.
     */
    private Integer fStartDay;
    /**
     * Beginning hour of the taf's validity.
     */
    private Integer fStartHour;

    /*
     * (non-Javadoc)
     * @see io.github.mivek.model.IValidity#getStartDay()
     */
    @Override
    public Integer getStartDay() {
        return fStartDay;
    }

    /*
     * (non-Javadoc)
     * @see io.github.mivek.model.IValidity#setStartDay(java.lang.Integer)
     */
    @Override
    public void setStartDay(final Integer pStartDay) {
        fStartDay = pStartDay;
    }

    /*
     * (non-Javadoc)
     * @see io.github.mivek.model.IValidity#getStartHour()
     */
    @Override
    public Integer getStartHour() {
        return fStartHour;
    }

    /*
     * (non-Javadoc)
     * @see io.github.mivek.model.IValidity#setStartHour(java.lang.Integer)
     */
    @Override
    public void setStartHour(final Integer pStartHour) {
        fStartHour = pStartHour;
    }

}
