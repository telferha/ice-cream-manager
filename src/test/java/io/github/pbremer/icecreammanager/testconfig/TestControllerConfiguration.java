package io.github.pbremer.icecreammanager.testconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import io.github.pbremer.icecreammanager.controller.ControllerConfiguration;

@Configuration
@ComponentScan(basePackages = { "io.github.pbremer.icecreammanager.controller" }, excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ControllerConfiguration.class) })
public class TestControllerConfiguration {

}
