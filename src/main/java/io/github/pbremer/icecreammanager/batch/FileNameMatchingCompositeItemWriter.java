/**
 * 
 */
package io.github.pbremer.icecreammanager.batch;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

/**
 * @author Patrick Bremer
 */
public class FileNameMatchingCompositeItemWriter
        implements ItemWriter<Serializable>, InitializingBean {

    private static final Logger log =
            LoggerFactory.getLogger(FileNameMatchingCompositeItemWriter.class);

    private Map<String, ItemWriter<Serializable>> delegates;
    @Value("#{jobParameters['input.file.name']}")
    private String filePath;

    private ItemWriter<Serializable> writer = null;

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
     */
    @Override
    public void write(List<? extends Serializable> items) throws Exception {

	if (null == writer) {
	    for (String fileName : delegates.keySet()) {

		if (fileName.equalsIgnoreCase(
		        FileUtils.getFile(filePath).getName())) {
		    log.debug("Found delegate for: {}", fileName);
		    writer = delegates.get(fileName);
		    break;
		}
	    }
	}

	Assert.notNull(writer, "Could not find delegate to write "
	        + FileUtils.getFile(filePath).getName());
	writer.write(items);
    }

    /**
     * @return the delegates
     */
    public Map<String, ItemWriter<Serializable>> getDelegates() {
	return delegates;
    }

    /**
     * @param delegates
     *            the delegates to set
     */
    public void setDelegates(Map<String, ItemWriter<Serializable>> delegates) {
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
	Assert.notNull(filePath, "Filepath must be set");
    }

}
