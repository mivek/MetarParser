package io.github.mivek.internationalization;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * @author mivek
 */
@AnalyzeClasses(packages = "io.github.mivek.internationalization", importOptions = { ImportOption.DoNotIncludeTests.class })
class ArchitectureTest {

    @ArchTest
    static final ArchRule dependencyRule = classes().should().onlyHaveDependentClassesThat().resideInAnyPackage("java.util..", "java.text..");

}

