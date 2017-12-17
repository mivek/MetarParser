package com.mivek.model;

import com.mivek.enums.CloudQuantity;
import com.mivek.enums.CloudType;

public class Cloud {
	private int altitude;
	private CloudQuantity quantity;
	private CloudType type;
	public int getAltitude() {
		return altitude;
	}
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}
	public CloudQuantity getQuantity() {
		return quantity;
	}
	public void setQuantity(CloudQuantity quantity) {
		this.quantity = quantity;
	}
	public CloudType getType() {
		return type;
	}
	public void setType(CloudType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		String res = this.quantity.toString();
		if (this.type != null) {
			res += " " + this.type.toString();
		}
		if (this.altitude != 0) {
			res += " " + this.altitude + "m";
		}

		return res;
	}
}
