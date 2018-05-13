package com.mivek.model;

import com.mivek.enums.CloudQuantity;
import com.mivek.enums.CloudType;

/**
 * Class representing a cloud element. Clouds are composed of : a quantity a
 * type (optional) an height (optional)
 * @author mivek
 */
public class Cloud {
	/**
	 * The height of the cloud (unit: feet).
	 */
	private int fHeight;
	/**
	 * The quantity of the cloud.
	 */
	private CloudQuantity fQuantity;
	/**
	 * The type of the cloud.
	 */
	private CloudType fType;

	/**
	 * Getter of the altitude (unit: meters, approximation).
	 * @return int of altitude.
	 * @deprecated Use {@link #getHeight()}
	 */
	@Deprecated
	public int getAltitude() {
		return fHeight * 30 / 100;
	}

	/**
	 * Getter of the height (unit: feet).
	 * @return int of height.
	 */
	public int getHeight() {
		return fHeight;
	}

	/**
	 * Setter of the height (unit: feet).
	 * @param pHeight The height to set.
	 */
	public void setHeight(final int pHeight) {
		fHeight = pHeight;
	}

	/**
	 * Setter of the altitude (unit: meters).
	 * @param pAltitude The altitude to set.
	 * @deprecated Use {@link #setHeight(int)}
	 */
	@Deprecated
	public void setAltitude(final int pAltitude) {
		fHeight = pAltitude * 100 / 30;
	}

	/**
	 * Getter of the quantity.
	 * @return a CloudQuantity.
	 */
	public CloudQuantity getQuantity() {
		return fQuantity;
	}

	/**
	 * Setter of CloudQuantity.
	 * @param pQuantity The CloudQuantity to set.
	 */
	public void setQuantity(final CloudQuantity pQuantity) {
		fQuantity = pQuantity;
	}

	/**
	 * Getter of type.
	 * @return a CloudType.
	 */
	public CloudType getType() {
		return fType;
	}

	/**
	 * Setter of cloud type.
	 * @param pType The CloudType to set.
	 */
	public void setType(final CloudType pType) {
		fType = pType;
	}

	@Override
	public final String toString() {
		String res = fQuantity.toString();
		if (fType != null) {
			res += " " + fType.toString();
		}
		if (fHeight != 0) {
			res += " " + fHeight + "ft (approx " + getAltitude() + "m)";
		}

		return res;
	}
}
