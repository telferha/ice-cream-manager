package io.github.pbremer.icecreammanager.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to add {@link io.github.pbremer.icecreammanager.service}
 * classes in the context
 * 
 * @author Patrick Bremer
 */
@Configuration
@ComponentScan({ "io.github.pbremer.icecreammanager.service" })
public class ServiceConfiguration {

}
