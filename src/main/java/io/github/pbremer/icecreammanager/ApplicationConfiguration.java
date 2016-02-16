package io.github.pbremer.icecreammanager;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.github.pbremer.icecreammanager.configuration.ControllerConfiguration;
import io.github.pbremer.icecreammanager.configuration.EntityConfiguration;

@Configuration
@Import(value = { EntityConfiguration.class, ControllerConfiguration.class })
public class ApplicationConfiguration {
    // Intentionally left blank
}
