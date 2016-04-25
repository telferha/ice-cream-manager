/**
 * 
 */
package io.github.pbremer.icecreammanager.batch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * @author Patrick Bremer
 */
public class ChangeExitStatusOnSkipListener implements StepExecutionListener {

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.core.StepExecutionListener#beforeStep(org.
     * springframework.batch.core.StepExecution)
     */
    @Override
    public void beforeStep(StepExecution stepExecution) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.core.StepExecutionListener#afterStep(org.
     * springframework.batch.core.StepExecution)
     */
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
	System.out.println(stepExecution.getProcessSkipCount()
	        + +stepExecution.getReadSkipCount());
	if (stepExecution.getProcessSkipCount()
	        + stepExecution.getReadSkipCount() > 0) {
	    System.out.println("Skips happened, so fail");
	    return stepExecution.getExitStatus().and(ExitStatus.FAILED);
	}
	return null;
    }

}
