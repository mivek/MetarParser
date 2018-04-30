package com.mivek.model;

import com.mivek.enums.CloudQuantity;
import com.mivek.enums.CloudType;

/**
 * Class representing a cloud element.
 * Clouds are composed of :
 * 		a quantity
 * 		a type (optional)
 * 		an height (optional)
 * @author mivek
 *
 */
public class Cloud {
	/**
	 * The height of the cloud (unit: feet).
	 */
	private int height;
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
		return height*30/100;
	}

	/**
	 * Getter of the height (unit: feet).
	 *
	 * @return int of height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Setter of the height (unit: feet).
	 *
	 * @param pHeight
	 *            The height to set.
	 */
	public void setHeight(final int pHeight) {
		this.height = pHeight;
	}

	/**
	 * Setter of the altitude (unit: meters).
	 *
	 * @param pAltitude
	 *            The altitude to set.
	 */
	public void setAltitude(final int pAltitude) {
		this.height = pAltitude*100/30;
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
		if (this.height != 0) {
			res += " " + height + "ft (approx " + getAltitude() + "m)";
		}

		return res;
	}
}
