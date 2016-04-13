/**
 * 
 */
package io.github.pbremer.icecreammanager.batch;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Patrick Bremer
 */
public class FileNameMatchingCompositeItemWriter
        implements ItemWriter<Serializable> {

    private Map<String, ItemWriter<Serializable>> delegates;
    @Value("#{jobParameters['input.file.name']}")
    private String filePath;

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
     */
    @Override
    public void write(List<? extends Serializable> items) throws Exception {
	delegates.get(FileUtils.getFile(filePath).getName()).write(items);
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

}
