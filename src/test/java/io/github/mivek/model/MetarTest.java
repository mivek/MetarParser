package io.github.mivek.model;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.FieldPredicate.exclude;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class MetarTest {

    @Test public void testPojo() {
        // given
        final Class<?> classUnderTest = Metar.class;
        // then
        assertPojoMethodsFor(classUnderTest, exclude("runways", "trends")).testing(Method.GETTER, Method.SETTER).areWellImplemented();
    }
}
