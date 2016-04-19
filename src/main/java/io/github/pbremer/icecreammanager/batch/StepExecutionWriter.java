/**
 * 
 */
package io.github.pbremer.icecreammanager.batch;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

import io.github.pbremer.icecreammanager.flatfilecontents.HeaderTrailerContainer;

/**
 * @author Patrick Bremer
 */
public class StepExecutionWriter implements ItemWriter<HeaderTrailerContainer> {

    private StepExecution stepExecution;

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
     */
    @Override
    public void write(List<? extends HeaderTrailerContainer> items)
            throws Exception {
	for (HeaderTrailerContainer container : items) {
	    stepExecution.getExecutionContext().putLong("day",
	            container.getDay().getTime());
	}
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
	this.stepExecution = stepExecution;
    }

}
