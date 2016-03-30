package io.github.pbremer.icecreammanager.batch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.job.JobStep;
import org.springframework.batch.core.step.skip.CompositeSkipPolicy;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * Contains and wires all job objects for Spring to manage
 * 
 * @author Patrick Bremer
 */
// @Component
// @ImportResource("classpath:batch/batch.xml")
public class BatchJobs {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processInputFileJob() {
	return jobBuilderFactory.get("processInputFileJob").start(new JobStep())
	        .on("").to(new JobStep()).on("").to(new JobStep()).on("")
	        .to(new JobStep()).on("").end(ExitStatus.NOOP.toString()).end()
	        .build();
    }

    @SuppressWarnings("unchecked")
    @Bean
    public Step truckRouteStep() {
	return stepBuilderFactory.get("truckRouteStep")
	        .<Object, Object> chunk(10).faultTolerant()
	        .skipPolicy(new CompositeSkipPolicy())
	        .skipLimit(Integer.MAX_VALUE).listener(new Object())
	        .reader(new FlatFileItemReader<Object>())
	        .processor(new CompositeItemProcessor<Object, Object>())
	        .writer(new CompositeItemWriter<Object>()).build();
    }
}
