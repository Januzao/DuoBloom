package org.example.duobloom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Spring Boot application entry point. */
@SpringBootApplication
public class DuoBloomApplication {

    /** Private constructor to prevent instantiation. */
    private DuoBloomApplication() {
    }

    /**
     * Application entry point.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(DuoBloomApplication.class, args);
    }

}
