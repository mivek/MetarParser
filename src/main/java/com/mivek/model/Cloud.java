package com.mivek.model;

import com.mivek.enums.CloudQuantity;
import com.mivek.enums.CloudType;

/**
 * Class representing a cloud element.
 * Clouds are composed of :
 * 		a quantity
 * 		a type (optional)
 * 		an altitude (optional)
 * @author mivek
 *
 */
public class Cloud {
	/**
	 * The altitude of the cloud (unit: meters).
	 */
	private int altitude;
	/**
	 * The quantity of the cloud.
	 */
	private CloudQuantity quantity;
	/**
	 * The type of the cloud.
	 */
	private CloudType type;

	/**
	 * Getter of the altitude (unit: meters).
	 *
	 * @return int of altitude.
	 */
	public int getAltitude() {
		return altitude;
	}

	/**
	 * Getter of the height (unit: feet).
	 *
	 * @return int of altitude.
	 */
	public int getHeight() {
		return altitude*100/30;
	}

	/**
	 * Setter of the altitude (unit: meters).
	 *
	 * @param pAltitude
	 *            The altitude to set.
	 */
	public void setAltitude(final int pAltitude) {
		this.altitude = pAltitude;
	}
	/**
	 * Getter of the quantity.
	 * @return a CloudQuantity.
	 */
	public CloudQuantity getQuantity() {
		return quantity;
	}

	/**
	 * Setter of CloudQuantity.
	 *
	 * @param pQuantity
	 *            The CloudQuantity to set.
	 */
	public void setQuantity(final CloudQuantity pQuantity) {
		this.quantity = pQuantity;
	}

	/**
	 * Getter of type.
	 *
	 * @return a CloudType.
	 */
	public CloudType getType() {
		return type;
	}

	/**
	 * Setter of cloud type.
	 *
	 * @param pType
	 *            The CloudType to set.
	 */
	public void setType(final CloudType pType) {
		this.type = pType;
	}

	@Override
	public final String toString() {
		String res = this.quantity.toString();
		if (this.type != null) {
			res += " " + this.type.toString();
		}
		if (this.altitude != 0) {
			res += " " + getHeight() + "ft (approx " + this.altitude + "m)";
		}

		return res;
	}
}
