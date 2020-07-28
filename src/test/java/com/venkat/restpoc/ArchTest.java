package com.venkat.restpoc;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.venkat.restpoc");

        noClasses()
            .that()
            .resideInAnyPackage("com.venkat.restpoc.service..")
            .or()
            .resideInAnyPackage("com.venkat.restpoc.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.venkat.restpoc.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
