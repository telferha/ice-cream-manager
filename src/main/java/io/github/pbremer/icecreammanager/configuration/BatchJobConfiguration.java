package io.github.pbremer.icecreammanager.configuration;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = { "io.github.pbremer.icecreammanager.batch" })
@ImportResource("classpath:batch/batch.xml")
public class BatchJobConfiguration {

}
