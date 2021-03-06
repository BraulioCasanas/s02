package com.store;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;

@MicronautTest
class StoreTest {

    @Inject
    EmbeddedApplication<?> application;

    @Ignore
    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

}
