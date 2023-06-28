package org.contextmapper.sample.tlas;

import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

class OnionArchitectureTest {

    private static final String BASE_PACKAGE = "org.contextmapper.sample.tlas";

    @Test
    void codeShouldAdhereToOnionArchitecture() {
        onionArchitecture()
                .domainModels(BASE_PACKAGE + ".domain..")
                .applicationServices(BASE_PACKAGE + ".application..")
                .adapter("infra-main", BASE_PACKAGE + ".infrastructure.main");
    }

}
