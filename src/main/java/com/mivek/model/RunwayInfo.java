package com.mivek.model;

public class RunwayInfo {
	private String name;
	private int minRange;
	private int maxRange;
	private String trend;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMinRange() {
		return minRange;
	}
	public void setMinRange(int minRange) {
		this.minRange = minRange;
	}
	public int getMaxRange() {
		return maxRange;
	}
	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}
	public String getTrend() {
		return trend;
	}
	public void setTrend(String trend) {
		this.trend = trend;
	}
}
