package io.github.mivek.model;

import io.github.mivek.enums.LengthUnit;
import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WindShearTest {

    @Test
    void testToString() {
        WindShear sut = new WindShear();
        sut.setHeight(500);
        sut.setHeightUnit(LengthUnit.FEET);

        assertThat(sut.toString(), Matchers.containsString(Messages.getInstance().getString("ToString.height.feet") + "=500"));
        assertThat(sut.toString(), Matchers.containsString(Messages.getInstance().getString("ToString.height.unit") + "=FT"));
    }

    @Test
    void testHeightUnitIsNullByDefault() {
        WindShear sut = new WindShear();
        assertNull(sut.getHeightUnit());
    }

    @Test
    void testSetGetHeightUnit() {
        WindShear sut = new WindShear();
        sut.setHeightUnit(LengthUnit.FEET);
        assertEquals(LengthUnit.FEET, sut.getHeightUnit());
    }
}
