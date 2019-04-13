package io.github.mivek.model;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class TemperatureDatedTest {
    @Test
    public void testToString() {
        TemperatureDated temp = new TemperatureDated();
        temp.setDay(10);
        temp.setHour(15);
        temp.setTemperature(20);

        String des = temp.toString();

        assertThat(des, containsString("day of the month=10"));
        assertThat(des, containsString("hour of the day=15"));
        assertThat(des, containsString("temperature (°C)=20"));
    }

    @Test public void Should_Pass_All_Pojo_Tests() {
        // given
        final Class<?> classUnderTest = TemperatureDated.class;
        // then
        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER).areWellImplemented();
    }
}
