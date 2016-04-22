package io.github.pbremer.icecreammanager.batch.item.file.transform;

import java.util.Map;

import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * A {@link LineTokenizer} implementation that stores a mapping of regular
 * expression patterns to delegate {@link LineTokenizer}s. Each line tokenized
 * will be checked to see if it matches a pattern. If the line matches a key in
 * the map of delegates, then the corresponding delegate {@link LineTokenizer}
 * will be used. If no pattern matches, a default {@line LineTokenizer} will be
 * returned.
 * 
 * @author Patrick Bremer
 */
public class RegexMatchingCompositeLineTokenizer
        implements LineTokenizer, InitializingBean {

    private Map<String, LineTokenizer> tokenizers = null;
    private LineTokenizer defaultTokenizer;

    public RegexMatchingCompositeLineTokenizer() {
	FixedLengthTokenizer fixedLengthTokenizer = new FixedLengthTokenizer();
	Range[] columns = new Range[1];
	columns[0] = new Range(1, 1);
	fixedLengthTokenizer.setColumns(columns);
	fixedLengthTokenizer.setStrict(false);
	defaultTokenizer = fixedLengthTokenizer;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	Assert.isTrue(this.tokenizers != null,
	        "The 'tokenizers' property must be non-empty");
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.batch.item.file.transform.LineTokenizer#tokenize(java
     * .lang.String)
     */
    @Override
    public FieldSet tokenize(String line) {
	return match(line).tokenize(line);
    }

    public void setTokenizers(Map<String, LineTokenizer> tokenizers) {
	Assert.isTrue(!tokenizers.isEmpty(),
	        "The 'tokenizers' property must be non-empty");
	this.tokenizers = tokenizers;
    }

    /**
     * Matches a line with the {@link LineTokenizer} mapped by the regular
     * expression
     * 
     * @param line
     *            the line to be tokenized
     * @return the {@link LineTokenizer} that is mapped by the regular
     *         expression
     */
    private LineTokenizer match(String line) {
	for (String regex : tokenizers.keySet()) {
	    if (line.matches(regex)) {
		return tokenizers.get(regex);
	    }
	}
	return defaultTokenizer;
    }

}
