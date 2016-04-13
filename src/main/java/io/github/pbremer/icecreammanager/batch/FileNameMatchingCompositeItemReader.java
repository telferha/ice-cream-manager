/**
 * 
 */
package io.github.pbremer.icecreammanager.batch;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import io.github.pbremer.icecreammanager.flatfilecontents.AbstractFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class FileNameMatchingCompositeItemReader
        extends StepExecutionListenerSupport
        implements ItemReader<AbstractFlatFileContainer>, InitializingBean {

    private static final Logger log =
            LoggerFactory.getLogger(FileNameMatchingCompositeItemReader.class);

    private Map<String, ItemReader<AbstractFlatFileContainer>> delegates;
    private Resource resource;
    private ExecutionContext executionContext;

    private ItemReader<AbstractFlatFileContainer> reader = null;

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public AbstractFlatFileContainer read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {

	Assert.notNull(executionContext,
	        "Execution context must be set before read");

	Assert.isTrue(resource.isReadable(),
	        resource.getDescription() + " must be readable.");

	if (reader == null) {
	    for (String fileName : delegates.keySet()) {
		if (fileName.equalsIgnoreCase(resource.getFilename())) {
		    log.debug("Found delegate for: {}", fileName);
		    reader = delegates.get(fileName);
		    break;
		}
	    }
	}

	Assert.notNull(reader, "Could not find delegate to read "
	        + resource.getFile().getName());

	((ItemStream) reader).open(executionContext);
	return reader.read();

    }

    /**
     * @return the delegates
     */
    public Map<String, ItemReader<AbstractFlatFileContainer>> getDelegates() {
	return delegates;
    }

    /**
     * @param delegates
     *            the delegates to set
     */
    public void setDelegates(
            Map<String, ItemReader<AbstractFlatFileContainer>> delegates) {
	this.delegates = delegates;
    }

    /**
     * @return the resource
     */
    public Resource getResource() {
	return resource;
    }

    /**
     * @param resource
     *            the resource to set
     */
    public void setResource(Resource resource) {
	this.resource = resource;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	Assert.notNull(delegates, "ItemReader delegates must be set");
	Assert.notNull(resource, "Resource must be set");
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.core.listener.StepExecutionListenerSupport#
     * beforeStep(org.springframework.batch.core.StepExecution)
     */
    @Override
    public void beforeStep(StepExecution stepExecution) {
	this.executionContext = stepExecution.getExecutionContext();
	super.beforeStep(stepExecution);
    }

}
