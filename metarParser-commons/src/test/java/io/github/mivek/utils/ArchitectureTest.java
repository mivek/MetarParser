package io.github.mivek.utils;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/** @author mivek */
@AnalyzeClasses(
    packages = "io.github.mivek.utils",
    importOptions = {ImportOption.DoNotIncludeTests.class})
public class ArchitectureTest {

  @ArchTest
  public static final ArchRule dependencyRule =
      classes()
          .should()
          .onlyHaveDependentClassesThat()
          .resideInAnyPackage("io.github.mivek.internationalization", "java.time", "java.util..");
}
