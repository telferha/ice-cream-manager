/**
 * 
 */
package io.github.pbremer.icecreammanager.flatfilecontents;

import org.apache.commons.lang3.StringUtils;

/**
 * Abstract class that all
 * {@link io.github.pbremer.icecreammanager.flatfilecontents} classes extend
 * 
 * @author Patrick Bremer
 */
public abstract class AbstractFlatFileContainer {

    private String errorColumnValue;

    /**
     * @return the errorColumnValue
     */
    public String getErrorColumnValue() {
	return errorColumnValue;
    }

    /**
     * @param errorColumnValue
     *            the errorColumnValue to set
     */
    public void setErrorColumnValue(String errorColumnValue) {
	this.errorColumnValue = errorColumnValue;
    }

    /**
     * Checks to see if there is any information that is out of range of the
     * input file
     * 
     * @return true if there is a value in the errorColumnValue, false if
     *         otherwise
     */
    public boolean containsValueInErrorColumn() {
	return "".equalsIgnoreCase(StringUtils.trimToEmpty(errorColumnValue));
    }

}
