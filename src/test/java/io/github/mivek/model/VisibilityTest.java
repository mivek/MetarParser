package io.github.mivek.model;

import org.hamcrest.Matchers;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.junit.Assert.assertThat;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class VisibilityTest {

    @Test
    public void testToString(){
        Visibility sut = new Visibility();
        sut.setMainVisibility(">10km");
        sut.setMinDirection("SE");
        sut.setMinVisibility(200);

        String des = sut.toString();

        assertThat(des, Matchers.containsString("main visibility=>10km"));
        assertThat(des, Matchers.containsString("minimum visibility direction=SE"));
        assertThat(des, Matchers.containsString("minimum visibility=200"));
    }

    @Test public void Should_Pass_All_Pojo_Tests() {
        // given
        final Class<?> classUnderTest = Visibility.class;
        // then
        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER).areWellImplemented();
    }
}
