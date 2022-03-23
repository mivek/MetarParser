package io.github.mivek.provider.airport.impl;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.github.mivek.provider.airport.AirportProvider;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * @author mivek
 */
@AnalyzeClasses(packages = { "io.github.mivek.provider.airport.impl" }, importOptions = { ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludePackageInfos.class })
class AirportProviderArchitectureTest {

    @ArchTest
    static final ArchRule implRule = classes().should().implement(AirportProvider.class);
}

