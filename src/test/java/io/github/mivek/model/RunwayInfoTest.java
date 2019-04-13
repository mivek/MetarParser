package io.github.mivek.model;

import org.hamcrest.Matchers;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.junit.Assert.assertThat;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class RunwayInfoTest {

    @Test public void testToString() {
        RunwayInfo ri = new RunwayInfo();
        ri.setMinRange(300);
        ri.setName("14R");
        ri.setTrend("rising");
        ri.setMaxRange(500);

        String des = ri.toString();

        assertThat(des, Matchers.containsString("name=14R"));
        assertThat(des, Matchers.containsString("min visibility=300"));
        assertThat(des, Matchers.containsString("max visibility=500"));
        assertThat(des, Matchers.containsString("trend=rising"));
    }

    @Test public void testPojo() {
        // given
        final Class<?> classUnderTest = RunwayInfo.class;
        // then
        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER).areWellImplemented();
    }
}
