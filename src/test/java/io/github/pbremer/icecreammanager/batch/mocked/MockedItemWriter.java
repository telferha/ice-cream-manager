/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.mocked;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

/**
 * @author Patrick Bremer
 */
public class MockedItemWriter implements ItemWriter<Object> {

    private static final Logger log =
            LoggerFactory.getLogger(MockedItemWriter.class);

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
     */
    @Override
    public void write(List<? extends Object> items) throws Exception {
	log.info(items.toString());
    }

}
