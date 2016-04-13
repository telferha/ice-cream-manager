/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Patrick Bremer
 */
public class TransformingItemProcessor<I, O> implements ItemProcessor<I, O> {

    private Converter<I, O> converter;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
     */
    @Override
    public O process(I item) throws Exception {
	return converter.convert(item);
    }

    /**
     * @return the converter
     */
    public Converter<I, O> getConverter() {
	return converter;
    }

    /**
     * @param converter
     *            the converter to set
     */
    public void setConverter(Converter<I, O> converter) {
	this.converter = converter;
    }

}
