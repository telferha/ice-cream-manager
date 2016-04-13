package io.github.pbremer.icecreammanager;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import io.github.pbremer.icecreammanager.configuration.BatchJobConfiguration;
import io.github.pbremer.icecreammanager.configuration.ControllerConfiguration;
import io.github.pbremer.icecreammanager.configuration.EntityConfiguration;
import io.github.pbremer.icecreammanager.configuration.ServiceConfiguration;

@Configuration
@PropertySource("classpath:ice-cream-manager.properties")
@Import(value = { EntityConfiguration.class, ControllerConfiguration.class,
        ServiceConfiguration.class, BatchJobConfiguration.class })
public class ApplicationConfiguration {
    // Intentionally left blank
}
