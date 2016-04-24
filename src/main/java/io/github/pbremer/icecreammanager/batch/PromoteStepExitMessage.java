/**
 * 
 */
package io.github.pbremer.icecreammanager.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;

/**
 * @author Patrick Bremer
 */
public class PromoteStepExitMessage implements JobExecutionListener {

    private static final Logger logger =
            LoggerFactory.getLogger(PromoteStepExitMessage.class);

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.core.JobExecutionListener#beforeJob(org.
     * springframework.batch.core.JobExecution)
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.core.JobExecutionListener#afterJob(org.
     * springframework.batch.core.JobExecution)
     */
    @Override
    public void afterJob(JobExecution jobExecution) {
	for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
	    logger.debug("Adding exit message: {}", stepExecution
	            .getExecutionContext().getString("error.msg", ""));
	    jobExecution.setExitStatus(jobExecution.getExitStatus()
	            .and(new ExitStatus(
	                    jobExecution.getExitStatus().getExitCode(),
	                    stepExecution.getExecutionContext()
	                            .getString("error.msg", ""))));
	}
	logger.debug("Job Exit Status: {}",
	        jobExecution.getExitStatus().toString());
    }

}
