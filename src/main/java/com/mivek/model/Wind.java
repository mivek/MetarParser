package com.mivek.model;

public class Wind {
	private int speed;
	private String direction;
	private int gust;
	private int extreme1;
	private int extreme2;
	private String unit;
	
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getGust() {
		return gust;
	}
	public void setGust(int gust) {
		this.gust = gust;
	}
	public int getExtreme1() {
		return extreme1;
	}
	public void setExtreme1(int extreme1) {
		this.extreme1 = extreme1;
	}
	public int getExtreme2() {
		return extreme2;
	}
	public void setExtreme2(int extreme2) {
		this.extreme2 = extreme2;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

}
