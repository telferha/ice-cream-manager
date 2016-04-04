/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.FieldSet;

/**
 * @author Patrick Bremer
 */
public abstract class MultilineFlatFileItemReader<T>
        implements ItemReader<T>, ItemStream {

    protected FlatFileItemReader<FieldSet> delegate;

    public void setDelegate(FlatFileItemReader<FieldSet> delegate) {
	this.delegate = delegate;
    }

    @Override
    public void close() throws ItemStreamException {
	this.delegate.close();
    }

    @Override
    public void open(ExecutionContext executionContext)
            throws ItemStreamException {
	this.delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext)
            throws ItemStreamException {
	this.delegate.update(executionContext);
    }
}
