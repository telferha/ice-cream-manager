package io.github.pbremer.icecreammanager;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.github.pbremer.icecreammanager.controller.ControllerConfiguration;

@Configuration
@Import(value = { ControllerConfiguration.class })
public class ApplicationConfiguration {
    // Intentionally left blank
}
