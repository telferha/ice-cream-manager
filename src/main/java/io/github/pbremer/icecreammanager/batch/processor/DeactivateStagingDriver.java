/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.pbremer.icecreammanager.flatfilecontents.AbstractFlatFileContainer;
import io.github.pbremer.icecreammanager.service.DriverService;

/**
 * @author Patrick Bremer
 */
public class DeactivateStagingDriver implements
        ItemProcessor<AbstractFlatFileContainer, AbstractFlatFileContainer> {

    @Autowired
    private DriverService service;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
     */
    @Override
    public AbstractFlatFileContainer process(AbstractFlatFileContainer item)
            throws Exception {
	service.setAllIsActiveFromTo(true, false);
	return item;
    }
}
