package io.github.pbremer.icecreammanager.testconfig;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(
        basePackages = { "io.github.pbremer.icecreammanager.repository" })
@EnableTransactionManagement
@EntityScan(basePackages = { "io.github.pbremer.icecreammanager.entity" })
public class TestEntityConfiguration {

}
