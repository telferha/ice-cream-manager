package io.github.pbremer.icecreammanager.configuration;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = { "io.github.pbremer.icecreammanager.repository" })
@EntityScan(basePackages = { "io.github.pbremer.icecreammanager.entity" })
public class EntityConfiguration {

}
