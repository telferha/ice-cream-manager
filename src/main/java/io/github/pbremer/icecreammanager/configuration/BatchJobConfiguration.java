package io.github.pbremer.icecreammanager.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;

// @Configuration
// @EnableBatchProcessing
// @ComponentScan(basePackages = { "io.github.pbremer.icecreammanager.batch" })
public class BatchJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    // @Bean
    // public Job processFileJob(@Qualifier("processFileStep") Step step) {
    // return jobBuilderFactory.get("processFileJob")
    // .incrementer(new RunIdIncrementer()).flow(step).end().build();
    // }

    /*
     * @Bean public Step processFileStep(ItemReader<Object> reader,
     * ItemProcessor<Object, Object> processor, ItemWriter<Object> writer) {
     * return stepBuilderFactory.get("processFileStep") .<Object, Object>
     * chunk(10).reader(reader).processor(processor) .writer(writer).build(); }
     */

}
