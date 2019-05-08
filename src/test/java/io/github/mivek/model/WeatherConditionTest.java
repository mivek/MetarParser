package io.github.mivek.model;

import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.internationalization.Messages;
import org.hamcrest.Matchers;
import org.junit.Test;
import pl.pojo.tester.api.FieldPredicate;
import pl.pojo.tester.api.assertion.Method;

import static org.junit.Assert.assertThat;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class WeatherConditionTest {

    @Test
    public void testToString(){
        WeatherCondition sut = new WeatherCondition();
        sut.setIntensity(Intensity.LIGHT);
        sut.setDescriptive(Descriptive.SHOWERS);
        sut.addPhenomenon(Phenomenon.RAIN);

        String desc = sut.toString();

        assertThat(desc, Matchers.containsString(Messages.getInstance().getString("ToString.intensity") + "=" + Intensity.LIGHT.toString()));
        assertThat(desc, Matchers.containsString(Messages.getInstance().getString("ToString.descriptive") + "=" + Descriptive.SHOWERS.toString()));
        assertThat(desc, Matchers.containsString(Phenomenon.RAIN.toString()));
    }

    @Test public void testPojo() {
        // given
        final Class<?> classUnderTest = WeatherCondition.class;
        // then
        assertPojoMethodsFor(classUnderTest, FieldPredicate.exclude("phenomenons")).testing(Method.GETTER, Method.SETTER).areWellImplemented();
    }
}
