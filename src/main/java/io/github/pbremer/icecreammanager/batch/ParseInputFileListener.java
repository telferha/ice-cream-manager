/**
 * 
 */
package io.github.pbremer.icecreammanager.batch;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepListenerSupport;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.validation.BindException;

/**
 * @author Patrick Bremer
 */
public class ParseInputFileListener
        extends StepListenerSupport<Object, Object> {

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
    public void onSkipInProcess(Object item, Throwable t) {

	logger.warn("Item {} skipped \n{}", item.toString(),
	        ExceptionUtils.getRootCauseMessage(t));

	executionContext.put("error.msg",
	        new StringBuffer(
	                executionContext.getString("error.msg", ""))
	                        .append("\n")
	                        .append(((BindException) ExceptionUtils
	                                .getRootCause(t)).getAllErrors().get(0)
	                                        .getDefaultMessage())
	                        .toString());

    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.core.listener.StepListenerSupport#onSkipInRead(
     * java.lang.Throwable)
     */
    @Override
    public void onSkipInRead(Throwable t) {
	logger.warn("Skipped reading item \n{}",
	        ExceptionUtils.getStackTrace(t));
	executionContext
	        .put("error.msg",
	                String.format("%s\n%s",
	                        executionContext.getString("error.msg", ""),
	                        ((BindException) ExceptionUtils.getRootCause(t))
	                                .getAllErrors().get(0)
	                                .getDefaultMessage()));
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.core.listener.StepListenerSupport#onReadError(
     * java.lang.Exception)
     */
    @Override
    public void onReadError(Exception ex) {
	logger.error("Error reading input file", ex);
	executionContext.put("error.msg",
	        "A fatal server exception has occured. Please contact support");
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.core.listener.StepListenerSupport#
     * onProcessError(java.lang.Object, java.lang.Exception)
     */
    @Override
    public void onProcessError(Object item, Exception e) {
	if (ExceptionUtils.getRootCause(e) instanceof BindException) {
	    executionContext.put("error.msg",
	            ((BindException) ExceptionUtils.getRootCause(e))
	                    .getAllErrors().get(0).getDefaultMessage());
	} else {
	    executionContext.put("error.msg",
	            "A fatal server exception has occured. Please contact support");
	}
	logger.error("Error processing {}\n{}", item.toString(),
	        ExceptionUtils.getRootCauseMessage(e));
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.core.listener.StepListenerSupport#onWriteError(
     * java.lang.Exception, java.util.List)
     */
    @Override
    public void onWriteError(Exception exception,
            List<? extends Object> items) {
	logger.error("Error writing {}\n{}", items.toString(),
	        ExceptionUtils.getStackTrace(exception));
	executionContext.put("error.msg",
	        "A fatal server exception has occured. Please contact support");
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.core.listener.StepListenerSupport#afterStep(org
     * .springframework.batch.core.StepExecution)
     */
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
	logger.debug("After Step {} with exit description:\n{}",
	        stepExecution.getStepName(),
	        executionContext.getString("error.msg", ""));
	return new ExitStatus(stepExecution.getExitStatus().getExitCode())
	        .addExitDescription(
	                executionContext.getString("error.msg", ""));
    }

}
