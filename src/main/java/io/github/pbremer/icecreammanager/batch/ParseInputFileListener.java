/**
 * 
 */
package io.github.pbremer.icecreammanager.batch;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepListenerSupport;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

import io.github.pbremer.icecreammanager.entity.EntitySupport;
import io.github.pbremer.icecreammanager.flatfilecontents.AbstractFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class ParseInputFileListener
        extends StepListenerSupport<AbstractFlatFileContainer, EntitySupport> {

    private static final Logger logger =
            LoggerFactory.getLogger(ParseInputFileListener.class);

    private ExecutionContext executionContext;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.core.listener.StepListenerSupport#beforeStep(
     * org.springframework.batch.core.StepExecution)
     */
    @Override
    public void beforeStep(StepExecution stepExecution) {
	super.beforeStep(stepExecution);
	this.executionContext = stepExecution.getExecutionContext();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.core.listener.StepListenerSupport#
     * onSkipInProcess(java.lang.Object, java.lang.Throwable)
     */
    @Override
    public void onSkipInProcess(AbstractFlatFileContainer item, Throwable t) {
	super.onSkipInProcess(item, t);
	logger.warn("{} skipped \n{}", item.toString(),
	        ExceptionUtils.getStackTrace(t));

	StringBuffer buffer = new StringBuffer();
	BindException ex = (BindException) t.getCause();
	for (ObjectError err : ex.getBindingResult().getAllErrors()) {
	    buffer.append(err.getDefaultMessage()).append("\n");
	}
	executionContext
	        .put("error.msg",
	                String.format("%s\n%s",
	                        executionContext.getString("error.msg",
	                                "***** ERROR LOG *****"),
	                        buffer.toString()));

    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.core.listener.StepListenerSupport#onSkipInRead(
     * java.lang.Throwable)
     */
    @Override
    public void onSkipInRead(Throwable t) {
	super.onSkipInRead(t);
	logger.warn("Skipped reading item \n{}",
	        ExceptionUtils.getStackTrace(t));
	executionContext.put("error.msg",
	        String.format("%s\n%s",
	                executionContext.getString("error.msg",
	                        "***** ERROR LOG *****"),
	                ExceptionUtils.getRootCauseMessage(t)));
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.core.listener.StepListenerSupport#onReadError(
     * java.lang.Exception)
     */
    @Override
    public void onReadError(Exception ex) {
	super.onReadError(ex);
	logger.error("Error reading input file", ex);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.core.listener.StepListenerSupport#
     * onProcessError(java.lang.Object, java.lang.Exception)
     */
    @Override
    public void onProcessError(AbstractFlatFileContainer item, Exception e) {
	super.onProcessError(item, e);
	logger.error("Error processing {}\n{}", item.toString(),
	        ExceptionUtils.getStackTrace(e));
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.core.listener.StepListenerSupport#onWriteError(
     * java.lang.Exception, java.util.List)
     */
    @Override
    public void onWriteError(Exception exception,
            List<? extends EntitySupport> items) {
	super.onWriteError(exception, items);
	logger.error("Error writing {}\n{}", items.toString(),
	        ExceptionUtils.getStackTrace(exception));
    }

}
