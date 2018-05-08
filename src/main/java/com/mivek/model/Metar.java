package com.mivek.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.mivek.model.trend.AbstractMetarTrend;

/**
 * Metar class.
 *
 * @author mivek
 *
 */
public class Metar extends WeatherCode {
	/**
	 * Temperature.
	 */
	private Integer temperature;
	/**
	 * Dew point.
	 */
	private Integer dewPoint;
	/**
	 * Altimeter.
	 */
	private Integer altimeter;
	/**
	 * Nosig value.
	 */
	private boolean nosig;
	/**
	 * Auto Value.
	 */
	private boolean auto;
	/**
	 * List of runways information.
	 */
	private List<RunwayInfo> runways;
	/**
	 * List of trends.
	 */
	private List<AbstractMetarTrend> trends;

	/**
	 * Constructor.
	 */
	public Metar() {
		super();
		runways = new ArrayList<>();
		trends = new ArrayList<>();
	}

	/**
	 * @return the temperature
	 */
	public Integer getTemperature() {
		return temperature;
	}

	/**
	 * @param pTemperature
	 *            the temperature to set
	 */
	public void setTemperature(final Integer pTemperature) {
		this.temperature = pTemperature;
	}

	/**
	 * @return the dewPoint
	 */
	public Integer getDewPoint() {
		return dewPoint;
	}

	/**
	 * @param pDewPoint
	 *            the dewPoint to set
	 */
	public void setDewPoint(final Integer pDewPoint) {
		this.dewPoint = pDewPoint;
	}

	/**
	 * @return the altimeter
	 */
	public Integer getAltimeter() {
		return altimeter;
	}

	/**
	 * @param pAltimeter
	 *            the altimeter to set
	 */
	public void setAltimeter(final Integer pAltimeter) {
		this.altimeter = pAltimeter;
	}

	/**
	 * @return the runways
	 */
	public List<RunwayInfo> getRunways() {
		return runways;
	}

	/**
	 * Adds a runway to the list.
	 *
	 * @param ri
	 *            the runway to add.
	 */
	public void addRunwayInfo(final RunwayInfo ri) {
		this.runways.add(ri);
	}

	/**
	 * @return the nosig
	 */
	public boolean isNosig() {
		return nosig;
	}

	/**
	 * @param pNosig
	 *            the nosig to set
	 */
	public void setNosig(final boolean pNosig) {
		this.nosig = pNosig;
	}

	/**
	 * @return the auto
	 */
	public boolean isAuto() {
		return auto;
	}

	/**
	 * @param pAuto
	 *            the auto to set
	 */
	public void setAuto(final boolean pAuto) {
		this.auto = pAuto;
	}

	/**
	 * Adds a trend to the list.
	 * @param pTrend the trend to add.
	 */
	public void addTrend(final AbstractMetarTrend pTrend) {
		trends.add(Validate.notNull(pTrend));
	}

	/**
	 * @return the list of trends.
	 */
	public List<AbstractMetarTrend> getTrends() {
		return trends;
	}

}
