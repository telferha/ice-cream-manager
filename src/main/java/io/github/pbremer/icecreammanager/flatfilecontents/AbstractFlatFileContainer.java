/**
 * 
 */
package io.github.pbremer.icecreammanager.flatfilecontents;

/**
 * Abstract class that all
 * {@link io.github.pbremer.icecreammanager.flatfilecontents} classes extend
 * 
 * @author Patrick Bremer
 */
public abstract class AbstractFlatFileContainer {

    private boolean isValid;

    public AbstractFlatFileContainer() {
	isValid = true;
    }

    /**
     * @return the isValid
     */
    public boolean isValid() {
	return isValid;
    }

    /**
     * @param isValid
     *            the isValid to set
     */
    public void setValid(boolean isValid) {
	this.isValid = isValid;
    }

}
