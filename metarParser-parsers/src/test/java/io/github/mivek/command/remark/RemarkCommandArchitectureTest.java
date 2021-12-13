package io.github.mivek.command.remark;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/** @author mivek */
@AnalyzeClasses(
    packages = {"io.github.mivek.command.remark"},
    importOptions = {ImportOption.DoNotIncludeTests.class})
public class RemarkCommandArchitectureTest {
  @ArchTest
  public static final ArchRule dependancyRule =
      noClasses()
          .should()
          .dependOnClassesThat()
          .resideInAnyPackage("io.github.mivek.parser")
          .andShould()
          .onlyBeAccessed()
          .byClassesThat()
          .haveSimpleName("RemarkParser");

  @ArchTest
  public static final ArchRule implementationRule =
      classes()
          .that()
          .areNotInterfaces()
          .and()
          .haveNameMatching(".*Command")
          .should()
          .implement(Command.class);
}
