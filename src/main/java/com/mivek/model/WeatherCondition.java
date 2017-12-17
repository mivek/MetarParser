package com.mivek.model;

import java.util.ArrayList;
import java.util.List;

import com.mivek.enums.Descriptive;
import com.mivek.enums.Intensity;
import com.mivek.enums.Phenomenon;

public class WeatherCondition {
	private Intensity intensity;
	private Descriptive descriptive;
	private List<Phenomenon> phenomenons;
	
	
	public WeatherCondition() {
		phenomenons = new ArrayList<>();
	}
	
	
	public Intensity getIntensity() {
		return intensity;
	}
	public void setIntensity(Intensity intensity) {
		this.intensity = intensity;
	}
	public Descriptive getDescriptive() {
		return descriptive;
	}
	public void setDescriptive(Descriptive descriptive) {
		this.descriptive = descriptive;
	}
	public List<Phenomenon> getPhenomenons() {
		return phenomenons;
	}
	public void setPhenomenons(List<Phenomenon> phenomenons) {
		this.phenomenons = phenomenons;
	}
	
	public void addPhenomenon(Phenomenon p) {
		this.phenomenons.add(p);
	}

	public boolean isValid() {
		return phenomenons.size() >= 1;
	}
}
