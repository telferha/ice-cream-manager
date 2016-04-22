/**
 * 
 */
package io.github.pbremer.icecreammanager.batch;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

import io.github.pbremer.icecreammanager.flatfilecontents.AbstractFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class FileNameMatchingCompositeItemProcessor
        implements ItemProcessor<AbstractFlatFileContainer, Serializable>,
        InitializingBean {

    private static final Logger log = LoggerFactory
            .getLogger(FileNameMatchingCompositeItemProcessor.class);

    private Map<String, ItemProcessor<AbstractFlatFileContainer, Serializable>> delegates;
    @Value("#{jobParameters['input.file.name']}")
    private String filePath;

    private ItemProcessor<AbstractFlatFileContainer, Serializable> processor =
            null;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
     */
    @Override
    public Serializable process(AbstractFlatFileContainer item)
            throws Exception {

	if (null == processor) {
	    for (String fileName : delegates.keySet()) {
		if (fileName.equalsIgnoreCase(
		        FileUtils.getFile(filePath).getName())) {
		    log.debug("Found delegate for: {}", fileName);
		    processor = delegates.get(fileName);
		    break;
		}
	    }
	}

	Assert.notNull(processor, "Could not find delegate to process "
	        + FileUtils.getFile(filePath).getName());

	return processor.process(item);
    }

    /**
     * @return the delegates
     */
    public Map<String, ItemProcessor<AbstractFlatFileContainer, Serializable>>
            getDelegates() {
	return delegates;
    }

    /**
     * @param delegates
     *            the delegates to set
     */
    public void setDelegates(
            Map<String, ItemProcessor<AbstractFlatFileContainer, Serializable>> delegates) {
	this.delegates = delegates;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	Assert.notEmpty(delegates, "Delegates must not be empty");
	Assert.notNull(filePath, "File path must be set");
    }

}
