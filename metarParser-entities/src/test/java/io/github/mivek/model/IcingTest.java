package io.github.mivek.model;

import io.github.mivek.enums.IcingIntensity;
import io.github.mivek.enums.LengthUnit;
import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IcingTest {

    @Test
    void testUnitIsNullByDefault() {
        Icing icing = new Icing();
        assertNull(icing.getUnit());
    }

    @Test
    void testSetGetUnit() {
        Icing icing = new Icing();
        icing.setUnit(LengthUnit.FEET);
        assertEquals(LengthUnit.FEET, icing.getUnit());
    }

    @Test
    void testToString() {
        Icing icing = new Icing();
        icing.setIntensity(IcingIntensity.LIGHT_RIME_ICING_CLOUD);
        icing.setBaseHeight(3000);
        icing.setDepth(4000);
        icing.setUnit(LengthUnit.FEET);

        String des = icing.toString();

        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.intensity")));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.baseHeight") + "=3000"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.depth") + "=4000"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString("ToString.height.unit") + "=FT"));
    }
}
