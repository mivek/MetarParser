package io.github.mivek.model;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.CloudType;
import io.github.mivek.enums.LengthUnit;
import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CloudTest {

    @Test
    void testSetHeightGetHeight() {
        Cloud c = new Cloud();
        c.setHeight(300);
        assertEquals(300, c.getHeight());
    }

    @Test
    void testUnitIsNullByDefault() {
        Cloud c = new Cloud();
        assertNull(c.getUnit());
    }

    @Test
    void testSetGetUnit() {
        Cloud c = new Cloud();
        c.setUnit(LengthUnit.FEET);
        assertEquals(LengthUnit.FEET, c.getUnit());
    }

    @Test
    void testToString() {
        Cloud c = new Cloud();
        c.setQuantity(CloudQuantity.BKN);
        c.setHeight(300);
        c.setType(CloudType.CB);
        c.setUnit(LengthUnit.FEET);
        assertThat(c.toString(Locale.ENGLISH), Matchers.containsString(Messages.getInstance().getString(Locale.ENGLISH, "ToString.type") + "=" + CloudType.CB));
        assertThat(c.toString(Locale.ENGLISH), Matchers.containsString(Messages.getInstance().getString(Locale.ENGLISH, "ToString.quantity") + "=" + CloudQuantity.BKN));
        assertThat(c.toString(Locale.ENGLISH), Matchers.containsString(Messages.getInstance().getString(Locale.ENGLISH, "ToString.height.feet") + "=300"));
        assertThat(c.toString(Locale.ENGLISH), Matchers.containsString(Messages.getInstance().getString(Locale.ENGLISH, "ToString.cloud.unit") + "=FT"));
    }

    @Test
    void testToStringWithNullType() {
        Cloud c = new Cloud();
        c.setQuantity(CloudQuantity.SCT);
        c.setHeight(150);
        c.setUnit(LengthUnit.FEET);
        assertThat(c.toString(Locale.ENGLISH), Matchers.containsString(Messages.getInstance().getString(Locale.ENGLISH, "ToString.quantity") + "=" + CloudQuantity.SCT));
    }

}
