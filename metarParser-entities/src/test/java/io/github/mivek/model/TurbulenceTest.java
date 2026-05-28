package io.github.mivek.model;

import io.github.mivek.enums.LengthUnit;
import io.github.mivek.enums.TurbulenceIntensity;
import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TurbulenceTest {

    @Test
    void testUnitIsNullByDefault() {
        Turbulence turbulence = new Turbulence();
        assertNull(turbulence.getUnit());
    }

    @Test
    void testSetGetUnit() {
        Turbulence turbulence = new Turbulence();
        turbulence.setUnit(LengthUnit.FEET);
        assertEquals(LengthUnit.FEET, turbulence.getUnit());
    }

    @Test
    void testToString() {
        Turbulence turbulence = new Turbulence();
        turbulence.setIntensity(TurbulenceIntensity.MODERATE_CLEAR_AIR_OCCASIONAL);
        turbulence.setBaseHeight(100);
        turbulence.setDepth(4000);
        turbulence.setUnit(LengthUnit.FEET);

        String des = turbulence.toString(Locale.ENGLISH);

        assertThat(des, Matchers.containsString(Messages.getInstance().getString(Locale.ENGLISH, "ToString.intensity")));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString(Locale.ENGLISH, "ToString.baseHeight") + "=100"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString(Locale.ENGLISH, "ToString.depth") + "=4000"));
        assertThat(des, Matchers.containsString(Messages.getInstance().getString(Locale.ENGLISH, "ToString.height.unit") + "=FT"));
    }
}
