package io.github.pbremer.icecreammanager.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

// @Configuration
// @EnableBatchProcessing
public class BatchJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public Job processFileJob(@Qualifier("processFileStep") Step step) {
	return jobBuilderFactory.get("processFileJob")
	        .incrementer(new RunIdIncrementer()).flow(step).end().build();
    }

    @Bean
    public Step processFileStep(ItemReader<Object> reader,
            ItemProcessor<Object, Object> processor,
            ItemWriter<Object> writer) {
	return stepBuilderFactory.get("processFileStep")
	        .<Object, Object> chunk(10).reader(reader).processor(processor)
	        .writer(writer).build();
    }

}
