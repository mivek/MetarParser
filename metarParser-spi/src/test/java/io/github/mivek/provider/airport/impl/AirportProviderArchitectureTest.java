package io.github.mivek.provider.airport.impl;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import io.github.mivek.provider.airport.AirportProvider;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * @author mivek
 */
@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = {"io.github.mivek.provider.airport.impl"}, importOptions = { ImportOption.DoNotIncludeTests.class })
public class AirportProviderArchitectureTest {

    @ArchTest
    public static final ArchRule implRule = classes().should().implement(AirportProvider.class);
}

