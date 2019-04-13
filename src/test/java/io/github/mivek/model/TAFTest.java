package io.github.mivek.model;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class TAFTest {
    @Test public void Should_Pass_All_Pojo_Tests() {
        // given
        final Class<?> classUnderTest = Cloud.class;
        // then
        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER).areWellImplemented();
    }
}
