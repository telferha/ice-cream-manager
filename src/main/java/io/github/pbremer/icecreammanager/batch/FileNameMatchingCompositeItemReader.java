/**
 * 
 */
package io.github.pbremer.icecreammanager.batch;

import java.util.Map;

import org.springframework.batch.item.ItemReader;
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
        implements ItemReader<AbstractFlatFileContainer>, InitializingBean {

    private Map<String, ItemReader<AbstractFlatFileContainer>> delegates;
    private Resource resource;

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public AbstractFlatFileContainer read()
            throws Exception, UnexpectedInputException, ParseException,
            NonTransientResourceException {

	Assert.isTrue(resource.isReadable(),
	        resource.getDescription() + " must be readable.");

	for (String fileName : delegates.keySet()) {
	    if (fileName == resource.getFilename()) {
		delegates.get(fileName).read();
	    }
	}

	throw new IllegalArgumentException("Could not find delegate to handle "
	        + resource.getDescription());
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

}
